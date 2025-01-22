/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

import static java.nio.file.Path.of;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.commons.text.StringSubstitutor.createInterpolator;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.nio.file.InvalidPathException;
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
        if (isNotEmpty (value) && value.startsWith ("${")) {
            final var substitute = createInterpolator ();
            substitute.setEnableSubstitutionInVariables (true);
            result = substitute.replace (value);
        }
        LOGGER.traceExit ();
        return result;
    }

    /**
     * Checks if the string is a valid file path.
     *
     * @param path String to test
     *
     * @return True if the path is a valid file path, else False.
     */
    public static boolean isValidPath (final String path) {
        try {
            return of (path).toFile ()
                .exists ();
        } catch (final InvalidPathException | NullPointerException ex) {
            return false;
        }
    }

    private StringUtils () {
        // Utility class
    }
}
