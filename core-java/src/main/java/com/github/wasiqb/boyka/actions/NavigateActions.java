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
import static com.google.common.truth.Truth.assertThat;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.drivers.INavigateActions;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Browser specific actions class,
 *
 * @author Wasiq Bhamla
 * @since 29-Nov-2022
 */
public class NavigateActions implements INavigateActions {
    private static final Logger           LOGGER           = getLogger ();
    private static final INavigateActions NAVIGATE_ACTIONS = new NavigateActions ();

    /**
     * Navigation related actions.
     *
     * @return Instance of {@link NavigateActions}
     */
    public static INavigateActions navigate () {
        return NAVIGATE_ACTIONS;
    }

    @Override
    public void back () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .back ());
        LOGGER.traceExit ();
    }

    @Override
    public void forward () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .forward ());
        LOGGER.traceExit ();
    }

    @Override
    public void refresh () {
        LOGGER.traceEntry ();
        performDriverAction (driver -> driver.navigate ()
            .refresh ());
        LOGGER.traceExit ();
    }

    @Override
    public void to (final String url) {
        LOGGER.traceEntry ();
        LOGGER.info ("Navigating to url: {}", url);
        performDriverAction (driver -> driver.get (url));
        LOGGER.traceExit ();
    }

    @Override
    public String url () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting url of the browser");
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getCurrentUrl, EMPTY));
    }

    @Override
    public StringSubject verifyUrl () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser url");
        LOGGER.traceExit ();
        return assertThat (url ());
    }
}
