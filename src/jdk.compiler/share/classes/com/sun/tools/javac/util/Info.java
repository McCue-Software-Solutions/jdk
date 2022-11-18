package com.sun.tools.javac.util;

import com.sun.tools.javac.util.JCDiagnostic.Fragment;

public record Info(
        Fragment message,
        List<InfoPosition> positions
) {
    public Info(Fragment message) {
        this(message, List.nil());
    }
}
