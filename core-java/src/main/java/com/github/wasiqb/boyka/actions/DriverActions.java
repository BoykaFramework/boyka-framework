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
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.OutputType.FILE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.github.wasiqb.boyka.enums.Messages;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.TakesScreenshot;
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
     * Accepts browser alert.
     *
     * @return the alert message
     */
    public static String acceptAlert () {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            final var message = alert.getText ();
            alert.accept ();
            return message;
        });
    }

    /**
     * Enters text in browser alert and accept it.
     *
     * @param text Text to enter in alert
     */
    public static void acceptAlert (final String text) {
        LOGGER.traceEntry ();
        performDriverAction (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            alert.sendKeys (text);
            alert.accept ();
        });
        LOGGER.traceExit ();
    }

    /**
     * Gets all the browser cookies.
     *
     * @return List of cookie names.
     */
    public static List<String> cookies () {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> driver.manage ()
            .getCookies ()
            .stream ()
            .map (Cookie::getName)
            .collect (Collectors.toList ()));
    }

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
     * Deletes all browser cookies.
     */
    public static void deleteAllCookies () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .deleteAllCookies ());
        LOGGER.traceExit ();
    }

    /**
     * Deletes browser cookie.
     *
     * @param name cookie name
     */
    public static void deleteCookie (final String name) {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .deleteCookieNamed (name));
        LOGGER.traceExit ();
    }

    /**
     * Dismisses browser alert.
     *
     * @return the alert message
     */
    public static String dismissAlert () {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            final var message = alert.getText ();
            alert.dismiss ();
            return message;
        });
    }

    /**
     * Switch browser window to full screen.
     */
    public static void fullScreen () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .fullscreen ());
        LOGGER.traceExit ();
    }

    /**
     * Navigate back to previous page on browser.
     */
    public static void goBack () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .back ());
        LOGGER.traceExit ();
    }

    /**
     * Navigate forward to next page on browser.
     */
    public static void goForward () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .forward ());
        LOGGER.traceExit ();
    }

    /**
     * Makes browser window maximized.
     */
    public static void maximize () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .maximize ());
        LOGGER.traceExit ();
    }

    /**
     * Makes browser window minimized.
     */
    public static void minimize () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .minimize ());
        LOGGER.traceExit ();
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
     * Refreshes browser page.
     */
    public static void refresh () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .refresh ());
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

    /**
     * Switch to window for specific name / handle.
     *
     * @param nameOrHandle name or handle of the window
     */
    public static void switchToWindow (final String nameOrHandle) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to window: {}", nameOrHandle);
        performDriverAction (driver -> driver.switchTo ()
            .window (nameOrHandle));
        LOGGER.traceExit ();
    }

    /**
     * Take screenshot of browser.
     *
     * @param fileName file name
     */
    public static void takeScreenshot (final String fileName) {
        LOGGER.traceEntry ();
        performDriverAction (driver -> {
            final var file = ((TakesScreenshot) driver).getScreenshotAs (FILE);
            try {
                copyFile (file, new File (fileName));
            } catch (final IOException e) {
                LOGGER.error (e.getMessage (), e);
                throw new FrameworkError (Messages.ERROR_SAVING_SCREENSHOT.getMessage (), e);
            }
        });
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
