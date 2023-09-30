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

package com.github.wasiqb.boyka.actions.drivers;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.enums.ListenerType.COOKIE_ACTION;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import com.github.wasiqb.boyka.actions.interfaces.drivers.ICookieActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.ICookieActionsListener;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;

/**
 * Class with all methods to handle cookies
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public class CookieActions implements ICookieActions {
    private static final ICookieActions COOKIE_ACTIONS = new CookieActions ();
    private static final Logger         LOGGER         = getLogger ();

    /**
     * Handles all cookies related actions
     *
     * @return {@link ICookieActions} instance object
     */
    public static ICookieActions withCookies () {
        return COOKIE_ACTIONS;
    }

    private final ICookieActionsListener listener;

    private CookieActions () {
        this.listener = getSession ().getListener (COOKIE_ACTION);
    }

    @Override
    public Cookie cookie (final String name) {
        LOGGER.traceEntry ();
        LOGGER.info ("Get Cookie named [{}]...", name);
        ofNullable (this.listener).ifPresent (l -> l.onCookie (name));
        return getDriverAttribute (driver -> driver.manage ()
            .getCookieNamed (name), null);
    }

    @Override
    public List<String> cookies () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting all the cookies...");
        ofNullable (this.listener).ifPresent (ICookieActionsListener::onCookies);
        return getDriverAttribute (driver -> driver.manage ()
            .getCookies ()
            .stream ()
            .map (Cookie::getName)
            .toList (), emptyList ());
    }

    @Override
    public void delete (final String name) {
        LOGGER.traceEntry ();
        LOGGER.info ("Delete Cookie named [{}]...", name);
        ofNullable (this.listener).ifPresent (l -> l.onDelete (name));
        performDriverAction (driver -> driver.manage ()
            .deleteCookieNamed (name));
        LOGGER.traceExit ();
    }

    @Override
    public void deleteAll () {
        LOGGER.traceEntry ();
        LOGGER.info ("Deleting all the cookies...");
        ofNullable (this.listener).ifPresent (ICookieActionsListener::onDeleteAll);
        performDriverAction (driver -> driver.manage ()
            .deleteAllCookies ());
        LOGGER.traceExit ();
    }
}
