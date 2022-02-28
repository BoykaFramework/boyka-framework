/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.utils;

import java.util.Map;

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

    public static String interpolate (final String value, final Map<String, String> valuesMap) {
        if (value.contains ("${")) {
            final StringSubstitutor substitute = new StringSubstitutor (valuesMap);
            substitute.setEnableSubstitutionInVariables (true);
            return substitute.replace (value);
        }
        return value;
    }

    private StringUtils () {
        // Utility class
    }
}
