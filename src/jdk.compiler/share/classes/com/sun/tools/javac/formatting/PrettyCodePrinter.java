package com.sun.tools.javac.formatting;

import com.sun.tools.javac.util.DiagnosticSource;

public class PrettyCodePrinter {
    public static String SourceLines(final DiagnosticSource source, final int startPos, final int endPos) {
        final var sourceLines = source.getLines(startPos, endPos);
        var currentLineNum = source.getLineNumber(startPos);
        final var lineCount = sourceLines.size();
        final var lineNumLen = Integer.toString(currentLineNum + lineCount).length();

        final var sb = new StringBuilder();
        for (final var sourceLine : sourceLines) {
            sb.append(currentLineNum);
            sb.append(" ".repeat(lineNumLen - Integer.toString(currentLineNum).length()));
            sb.append("| ");

            sb.append(sourceLine.line());

            currentLineNum += 1;
        }

        return sb.toString();
    }

    public static String SourceLinesWithReplacement(
            final DiagnosticSource source,
            final int replacementStartPos,
            final int replacementEndPos,
            final String replacement
    ) {
        final var sourceLines = source.getLines(replacementStartPos, replacementEndPos);
        var currentLineNum = source.getLineNumber(replacementStartPos);
        final var lineCount = sourceLines.size();
        final var lineNumLen = Integer.toString(currentLineNum + lineCount).length();

        final var sb = new StringBuilder();
        for (final var sourceLine : sourceLines) {
            sb.append(currentLineNum);
            sb.append(" ".repeat(lineNumLen - Integer.toString(currentLineNum).length()));
            sb.append("| ");

            // if the diagnostic range overlaps, process it for replacement and underlining
            if (sourceLine.startPos() <= replacementEndPos && sourceLine.endPos() >= replacementStartPos) {
                // clamp the ranges to this line, taking advantage of the fact that we know the ranges overlap
                final var lineReplaceStart =
                        replacementStartPos < sourceLine.startPos() ? sourceLine.startPos() : replacementStartPos;
                final var lineReplaceEnd =
                        replacementEndPos > sourceLine.endPos() ? sourceLine.endPos() : replacementEndPos;

                // the offsets into the replacement string to use
                final var replacementOffsetStart = lineReplaceStart - replacementStartPos;
                final var replacementOffsetEnd = lineReplaceEnd - replacementStartPos;

                // offsets into the line that will be replaced
                final var lineOffsetStart = lineReplaceStart - sourceLine.startPos();
                final var lineOffsetEnd = lineReplaceEnd - sourceLine.startPos();

                final var replacedLine = new StringBuilder(sourceLine.line());
                replacedLine.replace(lineOffsetStart, lineOffsetEnd,
                                     replacement.substring(replacementOffsetStart, replacementOffsetEnd + 1)
                );
                sb.append(replacedLine);
                sb.append("\n");

                // add in fancy underlines
                sb.append(" ".repeat(lineNumLen + 2));
                sb.append(" ".repeat(lineOffsetStart));
                sb.append("~".repeat(lineOffsetEnd - lineOffsetStart + 1));
            } else {
                // no replacement for this line
                sb.append(sourceLine.line());
            }

            currentLineNum += 1;
        }

        return sb.toString();
    }
}