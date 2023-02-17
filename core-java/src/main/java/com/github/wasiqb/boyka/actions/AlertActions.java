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

package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IAlertActions;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;

public class AlertActions implements IAlertActions {
    private static final IAlertActions ALERT_ACTIONS = new AlertActions ();
    private static final Logger        LOGGER        = getLogger ();

    public static IAlertActions onAlert () {
        return ALERT_ACTIONS;
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
            return message;
        }, EMPTY);
    }

    @Override
    public StringSubject verifyAccept () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying accept alert");
        LOGGER.traceExit ();
        return assertThat (accept ());
    }

    @Override
    public StringSubject verifyAccept (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying accept prompt");
        LOGGER.traceExit ();
        return assertThat (accept (text));
    }

    @Override
    public StringSubject verifyDismiss () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying alert message and dismissing the alert");
        LOGGER.traceExit ();
        return assertThat (dismiss ());
    }
}
