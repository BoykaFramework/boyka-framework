/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.Message.SESSION_PERSONA_CANNOT_BE_NULL;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.utils.Validator.requireNonEmpty;
import static java.lang.ThreadLocal.withInitial;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashMap;
import java.util.Map;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Session class that supports parallel execution.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class ParallelSession {
    private static final ThreadLocal<String>                                          CURRENT_PERSONA = withInitial (
        () -> EMPTY);
    private static final Logger                                                       LOGGER          = getLogger ();
    private static final ThreadLocal<Map<String, DriverSession<? extends WebDriver>>> SESSION         = withInitial (
        HashMap::new);

    /**
     * Clears all the sessions.
     */
    public static void clearAllSessions () {
        LOGGER.info ("Clearing all the sessions...");
        final var sessions = SESSION.get ();
        sessions.forEach ((persona, session) -> {
            switchPersona (persona);
            clearSession ();
        });
        sessions.clear ();
        SESSION.remove ();
    }

    /**
     * Clear session for current persona
     */
    public static void clearSession () {
        LOGGER.info ("Clearing session for persona [{}]...", getCurrentPersona ());
        getSession ().getDriver ()
            .quit ();
        if (getSession ().getPlatformType () != WEB) {
            getSession ().getServiceManager ()
                .stopServer ();
        }
        CURRENT_PERSONA.remove ();
    }

    /**
     * Create a new Session.
     *
     * @param persona User Persona
     * @param platformType Target Platform Type
     * @param configKey Configuration key for the session
     */
    public static void createSession (final String persona, final PlatformType platformType, final String configKey) {
        switchPersona (persona);
        final var currentSession = getSession ();
        currentSession.setPlatformType (platformType);
        currentSession.setConfigKey (configKey);
        final var instance = new DriverManager (configKey);
        instance.setupDriver ();
    }

    /**
     * Gets current persona name.
     *
     * @return Persona name
     */
    public static String getCurrentPersona () {
        return CURRENT_PERSONA.get ();
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
        final var currentPersona = getCurrentPersona ();
        final var session = SESSION.get ()
            .containsKey (currentPersona);
        if (!session) {
            SESSION.get ()
                .put (currentPersona, new DriverSession<> ());
        }
        return LOGGER.traceExit ((DriverSession<D>) SESSION.get ()
            .get (currentPersona));
    }

    /**
     * Switch current session persona.
     *
     * @param persona New Persona name
     */
    public static void switchPersona (final String persona) {
        final var currentPersona = getCurrentPersona ();
        if (currentPersona != null && currentPersona.equals (
            requireNonEmpty (persona, SESSION_PERSONA_CANNOT_BE_NULL))) {
            return;
        }
        LOGGER.info ("Switching current session persona from [{}] to [{}]...", currentPersona, persona);
        CURRENT_PERSONA.set (persona);
    }

    /**
     * Sets current session in thread.
     *
     * @param driver the driver instance
     * @param <D> the generic WebDriver type
     */
    static <D extends WebDriver> void setDriver (final D driver) {
        final var session = getSession ();
        LOGGER.traceEntry ("Application Type: {}, Driver: {}", session.getPlatformType (), driver);
        session.setDriver (driver);
        LOGGER.traceExit ();
    }

    private ParallelSession () {
        // Utility class
    }
}
