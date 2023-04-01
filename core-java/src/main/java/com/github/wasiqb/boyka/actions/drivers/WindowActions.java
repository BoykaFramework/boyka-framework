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
import static com.github.wasiqb.boyka.enums.Message.DRIVER_CANNOT_BE_NULL;
import static com.github.wasiqb.boyka.enums.Message.ERROR_SAVING_SCREENSHOT;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;
import static com.github.wasiqb.boyka.utils.ErrorHandler.requireNonNull;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.commons.io.FileUtils.copyFile;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.OutputType.FILE;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IWindowActions;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

/**
 * Handles all windows related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public class WindowActions implements IWindowActions {
    private static final Logger         LOGGER         = getLogger ();
    private static final IWindowActions WINDOW_ACTIONS = new WindowActions ();

    /**
     * Handles windows related actions
     *
     * @return {@link IWindowActions} instance object
     */
    public static IWindowActions onWindow () {
        return WINDOW_ACTIONS;
    }

    @Override
    public void close () {
        LOGGER.traceEntry ();
        LOGGER.info ("Closing window");
        performDriverAction (WebDriver::close);
        switchToDefault ();
        LOGGER.traceExit ();
    }

    @Override
    public String currentHandle () {
        LOGGER.traceEntry ();
        final String handle = getDriverAttribute (WebDriver::getWindowHandle, EMPTY);
        LOGGER.traceExit ();
        return handle;
    }

    @Override
    public void fullScreen () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .fullscreen ());
        LOGGER.traceExit ();
    }

    @Override
    public String getTitle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting title of the browser");
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getTitle, EMPTY));
    }

    @Override
    public List<String> handles () {
        LOGGER.traceEntry ();
        final var handles = getDriverAttribute (WebDriver::getWindowHandles, new ArrayList<String> ());
        LOGGER.traceExit ();
        return new ArrayList<> (handles);
    }

    @Override
    public void maximize () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .maximize ());
        LOGGER.traceExit ();
    }

    @Override
    public void minimize () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.manage ()
            .window ()
            .minimize ());
        LOGGER.traceExit ();
    }

    @Override
    public void switchTo (final String nameOrHandle) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to window: {}", nameOrHandle);
        performDriverAction (driver -> driver.switchTo ()
            .window (nameOrHandle));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToDefault () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to main window");
        performDriverAction (driver -> driver.switchTo ()
            .window (handles ().get (0)));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToNew (final WindowType type) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to new tab");
        performDriverAction (driver -> driver.switchTo ()
            .newWindow (type));
        LOGGER.traceExit ();
    }

    @Override
    public void takeScreenshot () {
        final var setting = getSession ().getSetting ()
            .getUi ()
            .getScreenshot ();
        if (!setting.isEnabled ()) {
            return;
        }
        final var path = setting.getPath ();
        final var prefix = setting.getPrefix ();
        final var extension = setting.getExtension ();
        final var date = new SimpleDateFormat ("yyyyMMdd-HHmmss");
        final var timeStamp = date.format (Calendar.getInstance ()
            .getTime ());
        final var fileName = "%s/%s-%s.%s";
        takeScreenshot (String.format (fileName, path, prefix, timeStamp, extension));
    }

    @Override
    public void takeScreenshot (final String fileName) {
        LOGGER.traceEntry ();
        final var setting = getSession ().getSetting ()
            .getUi ()
            .getScreenshot ();
        if (!setting.isEnabled ()) {
            return;
        }
        performDriverAction (driver -> {
            final var file = ((TakesScreenshot) requireNonNull (driver, DRIVER_CANNOT_BE_NULL)).getScreenshotAs (FILE);
            try {
                copyFile (file, new File (fileName));
            } catch (final IOException e) {
                handleAndThrow (ERROR_SAVING_SCREENSHOT, e);
            }
        });
        LOGGER.traceExit ();
    }

    @Override
    public StringSubject verifyTitle () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser title");
        LOGGER.traceExit ();
        return assertThat (getTitle ());
    }

    @Override
    public Dimension viewportSize () {
        return getDriverAttribute (driver -> driver.manage ()
            .window ()
            .getSize (), new Dimension (0, 0));
    }
}
