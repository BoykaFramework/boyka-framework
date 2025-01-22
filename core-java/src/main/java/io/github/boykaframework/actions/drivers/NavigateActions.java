/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

import static com.google.common.truth.Truth.assertWithMessage;
import static io.github.boykaframework.actions.CommonActions.getDriverAttribute;
import static io.github.boykaframework.actions.CommonActions.performDriverAction;
import static io.github.boykaframework.enums.ListenerType.NAVIGATE_ACTION;
import static io.github.boykaframework.enums.Message.BASE_URL_EMPTY;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.Validator.requireNonEmpty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.google.common.truth.StringSubject;
import io.github.boykaframework.actions.interfaces.drivers.INavigateActions;
import io.github.boykaframework.actions.interfaces.listeners.drivers.INavigateActionsListener;
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

    private final INavigateActionsListener listener;

    private NavigateActions () {
        this.listener = getSession ().getListener (NAVIGATE_ACTION);
    }

    @Override
    public void back () {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (INavigateActionsListener::onBack);
        performDriverAction (driver -> driver.navigate ()
            .back ());
        LOGGER.traceExit ();
    }

    @Override
    public void forward () {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (INavigateActionsListener::onForward);
        performDriverAction (driver -> driver.navigate ()
            .forward ());
        LOGGER.traceExit ();
    }

    @Override
    public String getUrl () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting url of the browser");
        ofNullable (this.listener).ifPresent (INavigateActionsListener::onGetUrl);
        return LOGGER.traceExit (getDriverAttribute (WebDriver::getCurrentUrl, EMPTY));
    }

    @Override
    public void refresh () {
        LOGGER.traceEntry ();
        ofNullable (this.listener).ifPresent (INavigateActionsListener::onRefresh);
        performDriverAction (driver -> driver.navigate ()
            .refresh ());
        LOGGER.traceExit ();
    }

    @Override
    public void to (final String url) {
        LOGGER.traceEntry ();
        LOGGER.info ("Navigating to url: {}", url);
        ofNullable (this.listener).ifPresent (l -> l.onTo (url));
        performDriverAction (driver -> driver.get (url));
        LOGGER.traceExit ();
    }

    @Override
    public void toBaseUrl () {
        LOGGER.traceEntry ();
        final var url = requireNonEmpty (getSession ().getWebSetting ()
            .getBaseUrl (), BASE_URL_EMPTY);
        LOGGER.info ("Navigating to base url: {}", url);
        ofNullable (this.listener).ifPresent (l -> l.onTo (url));
        performDriverAction (driver -> driver.get (url));
        LOGGER.traceExit ();
    }

    @Override
    public StringSubject verifyUrl () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying browser url");
        ofNullable (this.listener).ifPresent (INavigateActionsListener::onVerifyUrl);
        LOGGER.traceExit ();
        return assertWithMessage ("URL").that (getUrl ());
    }
}
