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

package com.github.wasiqb.boyka.sessions;

import static org.apache.logging.log4j.LogManager.getLogger;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Session class that supports parallel execution.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class ParallelSession {
    private static final Logger                                          LOGGER  = getLogger ();
    private static final ThreadLocal<DriverSession<? extends WebDriver>> SESSION = new ThreadLocal<> ();

    /**
     * Clears current session in thread.
     */
    public static void clearSession () {
        LOGGER.traceEntry ();
        SESSION.get ()
            .clearSharedData ();
        SESSION.remove ();
        LOGGER.traceExit ();
    }

    /**
     * Gets current session in thread.
     *
     * @param <D> the generic WebDriver type
     *
     * @return {@link DriverSession}
     */
    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> DriverSession<D> getSession () {
        LOGGER.traceEntry ();
        final var session = SESSION.get ();
        if (session == null) {
            SESSION.set (new DriverSession<> ());
        }
        return LOGGER.traceExit ((DriverSession<D>) SESSION.get ());
    }

    /**
     * Sets current session in thread.
     *
     * @param driver the driver instance
     * @param <D> the generic WebDriver type
     */
    public static <D extends WebDriver> void setDriver (final D driver) {
        final var session = getSession ();
        LOGGER.traceEntry ("Application Type: {}, Driver: {}", session.getPlatformType (), driver);
        session.setDriver (driver);
        LOGGER.traceExit ();
    }

    private ParallelSession () {
        // Utility class
    }
}
