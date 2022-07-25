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

import static java.text.MessageFormat.format;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.ArrayList;

import com.github.wasiqb.boyka.enums.Message;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.apache.logging.log4j.Logger;

/**
 * Error handler utility class.
 *
 * @author Wasiq Bhamla
 * @since 24-Jul-2022
 */
public final class ErrorHandler {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles the exceptions, prints the stack trace and throws wrapped Error.
     *
     * @param message Error message.
     * @param cause Error cause.
     * @param args Error message arguments.
     */
    public static void handleAndThrow (final Message message, final Throwable cause, final Object... args) {
        var throwable = cause;
        final var stack = new ArrayList<> ();
        stack.add (format ("Error occurred: ({0})", throwable.getClass ()
            .getName ()));
        final var stackTrace = "\tat {0}: {1} Line Number: {2}";
        do {
            if (stack.size () > 1) {
                stack.add (format ("Caused by: ({0})", throwable.getClass ()));
            }
            stack.add (format ("Message: {0}", throwable.getMessage ()));
            for (final var trace : cause.getStackTrace ()) {
                if (trace.getClassName ()
                    .startsWith ("com.github.wasiqb.boyka")) {
                    stack.add (
                        format (stackTrace, trace.getClassName (), trace.getMethodName (), trace.getLineNumber ()));
                }
            }
            throwable = throwable.getCause ();
        } while (throwable != null);
        stack.forEach (LOGGER::error);
        throw new FrameworkError (format (message.getMessageText (), args), cause);
    }

    private ErrorHandler () {
        // Utility class.
    }
}
