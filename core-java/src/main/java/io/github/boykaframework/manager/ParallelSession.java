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

package io.github.boykaframework.manager;

import static io.github.boykaframework.enums.Message.SESSION_ALREADY_CLEARED;
import static io.github.boykaframework.enums.Message.SESSION_ALREADY_CREATED;
import static io.github.boykaframework.enums.Message.SESSION_PERSONA_CANNOT_BE_NULL;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static io.github.boykaframework.utils.Validator.requireNonEmpty;
import static java.lang.ThreadLocal.withInitial;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashMap;
import java.util.Map;

import io.github.boykaframework.enums.PlatformType;
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
    public static synchronized void clearAllSessions () {
        LOGGER.info ("Clearing all the sessions...");
        final var sessions = SESSION.get ();
        final var sessionList = sessions.keySet ()
            .stream ()
            .toList ();
        for (final var persona : sessionList) {
            switchPersona (persona);
            clearSession ();
        }
        SESSION.remove ();
    }

    /**
     * Clear session for current persona
     */
    public static void clearSession () {
        LOGGER.info ("Clearing session for persona [{}]...", getCurrentPersona ());
        final var session = SESSION.get ();
        if (!isSessionCreated ()) {
            throwError (SESSION_ALREADY_CLEARED);
        }
        getSession ().clearListeners ();
        getSession ().clearSharedData ();
        ofNullable (getSession ().getDriver ()).ifPresent (WebDriver::quit);
        if (getSession ().getPlatformType () != PlatformType.WEB) {
            ofNullable (getSession ().getServiceManager ()).ifPresent (ServiceManager::stopServer);
        }
        if (isSessionCreated ()) {
            session.remove (getCurrentPersona ());
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
    public static synchronized void createSession (final String persona, final PlatformType platformType,
        final String configKey) {
        final var previousPersona = getCurrentPersona ();
        switchPersona (persona);
        checkSession ();
        overrideEmptySession (previousPersona);
        final var currentSession = getSession ();
        currentSession.setPlatformType (platformType);
        currentSession.setConfigKey (configKey);
        if (platformType != API) {
            final var instance = new DriverManager ();
            instance.setupDriver ();
        }
    }

    /**
     * Create a new Session.
     *
     * @param platformType Target Platform Type
     * @param configKey Configuration key for the session
     */
    public static void createSession (final PlatformType platformType, final String configKey) {
        createSession (configKey, platformType, configKey);
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
    public static synchronized <D extends WebDriver> DriverSession<D> getSession () {
        LOGGER.traceEntry ();
        final var currentPersona = getCurrentPersona ();
        if (!isSessionCreated ()) {
            setSession (new DriverSession<> ());
        }
        return LOGGER.traceExit ((DriverSession<D>) SESSION.get ()
            .get (currentPersona));
    }

    /**
     * Checks if the session has been started.
     *
     * @return true, if session has been created, else, false.
     */
    public static boolean isSessionCreated () {
        return SESSION.get ()
            .containsKey (getCurrentPersona ());
    }

    /**
     * Switch current session persona.
     *
     * @param persona New Persona name
     */
    public static void switchPersona (final String persona) {
        final var currentPersona = getCurrentPersona ();
        if (!isNull (currentPersona) && currentPersona.equals (
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

    private static void checkSession () {
        if (isSessionCreated ()) {
            throwError (SESSION_ALREADY_CREATED, getCurrentPersona ());
        }
    }

    private static void overrideEmptySession (final String previousPersona) {
        final var session = SESSION.get ();
        if (isEmpty (previousPersona) && session.containsKey (previousPersona)) {
            final var previousSession = session.remove (previousPersona);
            session.put (getCurrentPersona (), previousSession);
        }
    }

    private static synchronized <D extends WebDriver> void setSession (final DriverSession<D> session) {
        final var persona = getCurrentPersona ();
        final var currentSession = SESSION.get ();
        currentSession.put (persona, session);
    }

    private ParallelSession () {
        // Utility class
    }
}
