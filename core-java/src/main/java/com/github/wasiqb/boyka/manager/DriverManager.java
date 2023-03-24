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

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.Message.APP_TYPE_NOT_SUPPORTED;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.time.Duration.ofSeconds;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.config.ui.TimeoutSetting;
import com.github.wasiqb.boyka.config.ui.mobile.server.ServerSetting;
import com.github.wasiqb.boyka.enums.PlatformType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
final class DriverManager {
    private static final Logger LOGGER = getLogger ();

    private final PlatformType platformType;

    DriverManager (final String driverKey) {
        LOGGER.traceEntry ();
        this.platformType = getSession ().getPlatformType ();
        getSession ().setConfigKey (driverKey);
        LOGGER.traceExit ();
    }

    void setupDriver () {
        LOGGER.traceEntry ();
        if (this.platformType == API) {
            throwError (APP_TYPE_NOT_SUPPORTED, this.platformType);
        }
        final var settings = getSession ().getSetting ()
            .getUi ();
        if (this.platformType != WEB) {
            final var mobileSetting = getSession ().getMobileSetting ();
            setupAppiumServer (mobileSetting.getServer ());
        }
        final IDriverManager driverManager;
        switch (this.platformType) {
            case WEB:
                driverManager = new WebDriverManager ();
                break;
            case ANDROID:
                driverManager = new AndroidManager ();
                break;
            case IOS:
            default:
                driverManager = new IOSManager ();
                break;
        }
        driverManager.setupDriver ();
        setDriverWaits (settings.getTimeout ());
        LOGGER.traceExit ();
    }

    private void setDriverWaits (final TimeoutSetting timeoutSetting) {
        LOGGER.traceEntry ();
        final var driver = getSession ().getDriver ();
        final var timeouts = driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (timeoutSetting.getImplicitWait ()));
        if (this.platformType == WEB) {
            timeouts.pageLoadTimeout (ofSeconds (timeoutSetting.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (timeoutSetting.getScriptTimeout ()));
        }
        getSession ().setWait (new WebDriverWait (driver, ofSeconds (timeoutSetting.getExplicitWait ())));
        LOGGER.traceExit ();
    }

    private void setupAppiumServer (final ServerSetting serverSetting) {
        getSession ().setServiceManager (new ServiceManager (serverSetting));
        getSession ().getServiceManager ()
            .startServer ();
    }

}
