package com.sun.tools.javac.util;

import com.sun.tools.javac.util.JCDiagnostic.HelpFragment;

public record Help(
        HelpFragment message,
        List<SuggestedChange> suggestedChanges
) {

    public Help(final HelpFragment message) {
        this(message, List.nil());
    }

    public Help(final HelpFragment message, final List<SuggestedChange> suggestedChanges) {
        this.message = message;
        this.suggestedChanges = suggestedChanges;
    }
}
