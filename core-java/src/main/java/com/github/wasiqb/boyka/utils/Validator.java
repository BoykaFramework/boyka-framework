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

import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;

import com.github.wasiqb.boyka.enums.Message;
import org.apache.commons.lang3.StringUtils;

/**
 * Validator class to validate different conditions.
 *
 * @author Wasiq Bhamla
 * @since 25-Aug-2022
 */
public final class Validator {
    /**
     * Checks if the String value is not null.
     *
     * @param value Value to check
     * @param message Message to throw when the value is empty.
     * @param args Args for the message.
     *
     * @return Value if it is not empty.
     */
    public static String requireNonEmpty (final String value, final Message message, final Object... args) {
        if (StringUtils.isEmpty (value)) {
            throwError (message, args);
        }
        return value;
    }

    /**
     * Checks if the object is null or not, if null, a framework error will be thrown with provided message
     *
     * @param obj Test object
     * @param message Validation message
     * @param args message args
     * @param <T> Type of object
     *
     * @return Non null object
     */
    public static <T> T requireNonNull (final T obj, final Message message, final Object... args) {
        if (obj == null) {
            throwError (message, args);
        }
        return obj;
    }

    private Validator () {
        // Utility class.
    }
}
