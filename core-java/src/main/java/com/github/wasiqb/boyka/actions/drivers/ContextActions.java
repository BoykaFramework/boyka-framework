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
import static com.github.wasiqb.boyka.actions.drivers.DriverActions.withDriver;
import static com.github.wasiqb.boyka.enums.ApplicationType.HYBRID;
import static com.github.wasiqb.boyka.enums.ListenerType.CONTEXT_ACTION;
import static com.github.wasiqb.boyka.enums.Message.CONTEXT_SWITCHING_NOT_ALLOWED;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.ArrayList;
import java.util.List;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IContextActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IContextActionsListener;
import io.appium.java_client.remote.SupportsContextSwitching;
import org.apache.logging.log4j.Logger;

/**
 * Handles Contexts
 *
 * @author Wasiq Bhamla
 * @since 09-Mar-2023
 */
public class ContextActions implements IContextActions {
    private static final IContextActions CONTEXT_ACTIONS = new ContextActions ();
    private static final Logger          LOGGER          = getLogger ();

    /**
     * Handles all context related actions
     *
     * @return {@link IContextActions} instance object
     */
    public static IContextActions withContext () {
        return CONTEXT_ACTIONS;
    }

    private final IContextActionsListener listener;

    private ContextActions () {
        this.listener = getSession ().getSetting ()
            .getListener (CONTEXT_ACTION);
    }

    @Override
    public List<String> contexts () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting All available context names...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onContexts);
        return getDriverAttribute ((final SupportsContextSwitching d) -> new ArrayList<> (d.getContextHandles ()),
            null);
    }

    @Override
    public String currentContext () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting current context name...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onCurrentContext);
        return getDriverAttribute (SupportsContextSwitching::getContext, null);
    }

    @Override
    public void switchToNative () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to NATIVE_APP...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onSwitchToNative);
        switchToWebView ("NATIVE_APP");
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView (final String contextName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to [{}]...", contextName);
        ofNullable (this.listener).ifPresent (l -> l.onSwitchToWebView (contextName));
        final var applicationType = getSession ().getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType ();
        if (applicationType != HYBRID) {
            throwError (CONTEXT_SWITCHING_NOT_ALLOWED, applicationType);
        }
        performDriverAction ((SupportsContextSwitching d) -> d.context (contextName));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to first Web view...");
        ofNullable (this.listener).ifPresent (IContextActionsListener::onSwitchToWebView);
        withDriver ().waitUntil (d -> contexts ().size () > 1);
        final var webContext = contexts ().stream ()
            .filter (c -> !c.equals ("NATIVE_APP"))
            .findFirst ();
        webContext.ifPresent (this::switchToWebView);
        LOGGER.traceExit ();
    }
}
