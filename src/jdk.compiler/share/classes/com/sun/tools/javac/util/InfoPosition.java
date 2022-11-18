package com.sun.tools.javac.util;

import com.sun.tools.javac.util.JCDiagnostic.DiagnosticPosition;

public record InfoPosition(DiagnosticSource source, DiagnosticPosition position) {
}
