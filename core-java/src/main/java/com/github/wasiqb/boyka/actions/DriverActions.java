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

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

/**
 * Device / Browser specific actions.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class DriverActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets the current window handle.
     *
     * @return the current window handle
     */
    public static String currentWindowHandle () {
        LOGGER.traceEntry ();
        final String handle = getDriverAttribute (WebDriver::getWindowHandle);
        LOGGER.traceExit ();
        return handle;
    }

    /**
     * Navigate to url on browser.
     *
     * @param url url to navigate to
     */
    public static void navigateTo (final String url) {
        LOGGER.traceEntry ();
        LOGGER.info ("Navigating to url: {}", url);
        performDriverAction (driver -> driver.get (url));
        LOGGER.traceExit ();
    }

    /**
     * Switch to new tab window.
     *
     * @param type type of window
     */
    public static void switchToNewWindow (final WindowType type) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to new tab");
        performDriverAction (driver -> driver.switchTo ()
            .newWindow (type));
        LOGGER.traceExit ();
    }

    public static void switchToWindow (final String nameOrHandle) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to window: {}", nameOrHandle);
        performDriverAction (driver -> driver.switchTo ()
            .window (nameOrHandle));
        LOGGER.traceExit ();
    }

    /**
     * Title of the browser.
     *
     * @return title of the browser
     */
    public static String title () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting title of the browser");
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getTitle));
    }

    /**
     * Get current url of the browser.
     *
     * @return current url of the browser
     */
    public static String url () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting url of the browser");
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getCurrentUrl));
    }

    /**
     * Gets all open window handles.
     *
     * @return all open window handles
     */
    public static List<String> windowHandles () {
        LOGGER.traceEntry ();
        final var handles = getDriverAttribute (WebDriver::getWindowHandles);
        LOGGER.traceExit ();
        return new ArrayList<> (handles);
    }

    private DriverActions () {
        // Utility class.
    }
}
