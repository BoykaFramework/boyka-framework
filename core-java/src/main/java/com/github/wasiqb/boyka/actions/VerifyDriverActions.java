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

package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.DriverActions.title;
import static com.github.wasiqb.boyka.actions.DriverActions.url;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;

/**
 * Verify driver actions.
 *
 * @author Wasiq Bhamla
 * @since 05-Mar-2022
 */
public class VerifyDriverActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Verify browser title.
     *
     * @return {@link StringSubject} to verify browser title
     */
    public static StringSubject verifyBrowserTitle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser title");
        LOGGER.traceExit ();
        return assertThat (title ());
    }

    /**
     * Verify browser url.
     *
     * @return {@link StringSubject} to verify browser url
     */
    public static StringSubject verifyBrowserUrl () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser url");
        LOGGER.traceExit ();
        return assertThat (url ());
    }

    private VerifyDriverActions () {
        // Utility class
    }
}
