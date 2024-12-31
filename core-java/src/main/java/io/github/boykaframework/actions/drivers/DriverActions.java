/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.actions.drivers;

import static io.github.boykaframework.actions.CommonActions.getDriverAttribute;
import static io.github.boykaframework.actions.CommonActions.performDriverAction;
import static io.github.boykaframework.enums.ListenerType.DRIVER_ACTION;
import static io.github.boykaframework.enums.Message.ERROR_CREATING_LOGS;
import static io.github.boykaframework.enums.Message.ERROR_WHILE_SLEEPING;
import static io.github.boykaframework.enums.Message.ERROR_WRITING_LOGS;
import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static java.lang.System.getProperty;
import static java.lang.System.lineSeparator;
import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.function.Function;

import io.github.boykaframework.actions.interfaces.drivers.IDriverActions;
import io.github.boykaframework.actions.interfaces.listeners.drivers.IDriverActionsListener;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Device / Browser specific actions.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class DriverActions implements IDriverActions {
    private static final DriverActions DRIVER_ACTIONS = new DriverActions ();
    private static final Logger        LOGGER         = getLogger ();

    /**
     * Handles all other driver related actions
     *
     * @return {@link IDriverActions} instance object
     */
    public static IDriverActions withDriver () {
        return DRIVER_ACTIONS;
    }

    private final IDriverActionsListener listener;

    private DriverActions () {
        this.listener = getSession ().getListener (DRIVER_ACTION);
    }

    @Override
    @SuppressWarnings ("unchecked")
    public <T> T executeScript (final String script, final Object... args) {
        LOGGER.traceEntry ();
        LOGGER.info ("Executing script");
        ofNullable (this.listener).ifPresent (l -> l.onExecuteScript (script, args));
        return (T) getDriverAttribute (driver -> ((JavascriptExecutor) driver).executeScript (script, args), null);
    }

    @Override
    public void pause (final Duration time) {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (l -> l.onPause (time));
        performDriverAction (driver -> {
            if (getSession ().getPlatformType () == WEB) {
                final var action = new Actions (driver);
                action.pause (time)
                    .build ()
                    .perform ();
            } else {
                try {
                    sleep (time.toMillis ());
                } catch (final InterruptedException e) {
                    currentThread ().interrupt ();
                    throwError (ERROR_WHILE_SLEEPING);
                }
            }
        });
        LOGGER.traceExit ();
    }

    @Override
    public void saveLogs () {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (IDriverActionsListener::onSaveLogs);
        performDriverAction (d -> {
            final var logSetting = getSession ().getSetting ()
                .getUi ()
                .getLogging ();
            if (!logSetting.isEnable ()) {
                LOGGER.warn ("Cannot save different logs to file, logging is disabled...");
                return;
            }
            try {
                final var logTypes = d.manage ()
                    .logs ()
                    .getAvailableLogTypes ();
                logTypes.forEach (logType -> {
                    LOGGER.info ("Saving [{}] logs to a file...", logType);
                    if (isNull (logSetting.getExcludeLogs ()) || !logSetting.getExcludeLogs ()
                        .contains (logType)) {
                        saveLogType (d, logType, logSetting.getPath ());
                    } else {
                        LOGGER.info ("Skipped saving [{}] logs to a file...", logType);
                    }
                });
            } catch (final WebDriverException e) {
                LOGGER.catching (e);
                LOGGER.warn ("Error while saving different logs: {}", e.getMessage ());
            }
        });
        LOGGER.traceExit ();
    }

    @Override
    public <T> void waitUntil (final Function<WebDriver, T> condition) {
        LOGGER.traceEntry ();
        LOGGER.info ("Waiting for condition...");
        ofNullable (this.listener).ifPresent (IDriverActionsListener::onWaitUntil);
        performDriverAction (driver -> {
            final var wait = getSession ().getWait ();
            wait.until (condition);
        });
    }

    @Override
    public <T> void waitUntil (final Function<WebDriver, T> condition, final Duration timeout) {
        LOGGER.traceEntry ();
        LOGGER.info ("Waiting for the expected condition for {} duration...", timeout);
        ofNullable (this.listener).ifPresent (l -> l.onWaitUntil (timeout));
        performDriverAction (driver -> {
            final var wait = new WebDriverWait (driver, timeout);
            wait.until (condition);
        });
    }

    private <D extends WebDriver> void saveLogType (final D driver, final String logType, final String logPath) {
        final var logEntries = driver.manage ()
            .logs ()
            .get (logType);
        final var fileName = format ("{0}/{1}/{2}-{3}.log", getProperty ("user.dir"), logPath, logType,
            currentThread ().getId ());
        try (final var writer = new BufferedWriter (new FileWriter (fileName))) {
            logEntries.forEach (logEntry -> {
                try {
                    writer.write (logEntry.getMessage ());
                    writer.write (lineSeparator ());
                } catch (final IOException e) {
                    handleAndThrow (ERROR_WRITING_LOGS, e);
                }
            });
        } catch (final IOException e) {
            handleAndThrow (ERROR_CREATING_LOGS, e);
        }
    }
}
