package com.sun.tools.javac.util;

import com.sun.tools.javac.util.JCDiagnostic.InfoFragment;

public record Info(
        InfoFragment message,
        List<InfoPosition> positions
) {
    public Info(InfoFragment message) {
        this(message, List.nil());
    }
}
