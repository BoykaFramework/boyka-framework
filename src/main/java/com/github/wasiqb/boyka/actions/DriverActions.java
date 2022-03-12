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

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Device / Browser specific actions.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class DriverActions {
    private static final Logger LOGGER = getLogger ();

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
     * Title of the browser.
     *
     * @return title of the browser
     */
    public static String title () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting title of the browser");
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getTitle));
    }

    private DriverActions () {
        // Utility class.
    }
}
