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
import static com.github.wasiqb.boyka.enums.ListenerType.ALERT_ACTION;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IAlertActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IAlertActionsListener;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;

/**
 * All Alert related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public class AlertActions implements IAlertActions {
    private static final IAlertActions ALERT_ACTIONS = new AlertActions ();
    private static final Logger        LOGGER        = getLogger ();

    /**
     * Handle all Alert related actions
     *
     * @return {@link IAlertActions} instance object
     */
    public static IAlertActions onAlert () {
        return ALERT_ACTIONS;
    }

    private final IAlertActionsListener listener;

    private AlertActions () {
        this.listener = getSession ().getSetting ()
            .getListener (ALERT_ACTION);
    }

    @Override
    public String accept (final String text) {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            final var message = alert.getText ();
            alert.sendKeys (text);
            alert.accept ();
            ofNullable (this.listener).ifPresent (l -> l.onAccept (text));
            return message;
        }, EMPTY);
    }

    @Override
    public String accept () {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            final var message = alert.getText ();
            alert.accept ();
            ofNullable (this.listener).ifPresent (IAlertActionsListener::onAccept);
            return message;
        }, EMPTY);
    }

    @Override
    public String dismiss () {
        LOGGER.traceEntry ();
        return getDriverAttribute (driver -> {
            final var alert = driver.switchTo ()
                .alert ();
            final var message = alert.getText ();
            alert.dismiss ();
            ofNullable (this.listener).ifPresent (IAlertActionsListener::onDismiss);
            return message;
        }, EMPTY);
    }

    @Override
    public StringSubject verifyAccept () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying accept alert");
        LOGGER.traceExit ();
        ofNullable (this.listener).ifPresent (IAlertActionsListener::onVerifyAccept);
        return assertThat (accept ());
    }

    @Override
    public StringSubject verifyAccept (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying accept prompt");
        LOGGER.traceExit ();
        ofNullable (this.listener).ifPresent (l -> l.onVerifyAccept (text));
        return assertThat (accept (text));
    }

    @Override
    public StringSubject verifyDismiss () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying alert message and dismissing the alert");
        LOGGER.traceExit ();
        ofNullable (this.listener).ifPresent (IAlertActionsListener::onVerifyDismiss);
        return assertThat (dismiss ());
    }
}
