package com.github.wasiqb.boyka.utils;

import org.apache.commons.text.StringSubstitutor;

public final class StringUtils {
    public static String interpolate (final String value) {
        if (value.startsWith ("${")) {
            final StringSubstitutor substitute = StringSubstitutor.createInterpolator ();
            substitute.setEnableSubstitutionInVariables (true);
            return substitute.replace (value);
        }
        return value;
    }

    private StringUtils () {
        // Utility class
    }
}
