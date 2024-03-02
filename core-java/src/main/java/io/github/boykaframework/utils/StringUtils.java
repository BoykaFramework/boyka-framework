/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.utils;

import static org.apache.commons.text.StringSubstitutor.createInterpolator;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import org.apache.commons.text.StringSubstitutor;
import org.apache.logging.log4j.Logger;

/**
 * String utils.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class StringUtils {
    private static final Logger LOGGER = getLogger ();

    /**
     * Replace all the variables in the given string with the values from the specified map.
     *
     * @param value String to be interpolated
     * @param valuesMap Map of values to lookup for variables
     *
     * @return Interpolated string
     */
    public static String interpolate (final String value, final Map<String, String> valuesMap) {
        LOGGER.traceEntry ("Interpolating string: {}", value);
        var result = value;
        if (value.contains ("${")) {
            final var substitute = new StringSubstitutor (valuesMap);
            substitute.setEnableSubstitutionInVariables (true);
            result = substitute.replace (value);
        }
        LOGGER.traceExit ();
        return result;
    }

    /**
     * Replace all variables in the given string with the values from system environment variables, system properties,
     * etc.
     *
     * @param value String to be interpolated
     *
     * @return Interpolated string
     */
    public static String interpolate (final String value) {
        LOGGER.traceEntry ("Interpolating string: {}", value);
        var result = value;
        if (value != null && value.startsWith ("${")) {
            final var substitute = createInterpolator ();
            substitute.setEnableSubstitutionInVariables (true);
            result = substitute.replace (value);
        }
        LOGGER.traceExit ();
        return result;
    }

    private StringUtils () {
        // Utility class
    }
}
