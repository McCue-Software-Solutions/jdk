package com.sun.tools.javac.util;

import com.sun.tools.javac.util.JCDiagnostic.RangeDiagnosticPosition;

public record SuggestedChange(
        DiagnosticSource source,
        RangeDiagnosticPosition position,
        String replacement,
        Applicability applicability
) {}
