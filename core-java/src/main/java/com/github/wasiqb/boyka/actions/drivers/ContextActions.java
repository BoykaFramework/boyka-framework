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
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.ArrayList;
import java.util.List;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IContextActions;
import com.github.wasiqb.boyka.enums.PlatformType;
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

    private PlatformType platformType;

    @Override
    public List<String> contexts () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting All available context names...");
        return getDriverAttribute ((SupportsContextSwitching d) -> new ArrayList<> (d.getContextHandles ()), null);
    }

    @Override
    public String currentContext () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting current context name...");
        return getDriverAttribute (SupportsContextSwitching::getContext, null);
    }

    @Override
    public void switchToNative () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to NATIVE_APP...");
        switchToWebView ("NATIVE_APP");
        getSession ().setPlatformType (this.platformType);
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView (final String contextName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to [{}]...", contextName);
        performDriverAction ((SupportsContextSwitching d) -> d.context (contextName));
        this.platformType = getSession ().getPlatformType ();
        getSession ().setPlatformType (WEB);
        LOGGER.traceExit ();
    }

    @Override
    public void switchToWebView () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching context to first Web view...");
        withDriver ().waitUntil (d -> contexts ().size () > 1);
        final var webContext = contexts ().stream ()
            .filter (c -> !c.equals ("NATIVE_APP"))
            .findFirst ();
        webContext.ifPresent (this::switchToWebView);
        LOGGER.traceExit ();
    }
}
