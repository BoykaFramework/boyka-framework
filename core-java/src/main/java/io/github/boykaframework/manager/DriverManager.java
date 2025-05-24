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

package io.github.boykaframework.manager;

import static io.github.boykaframework.enums.PlatformType.ANDROID;
import static io.github.boykaframework.enums.PlatformType.IOS;
import static io.github.boykaframework.enums.PlatformType.MAC;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.time.Duration.ofSeconds;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.github.boykaframework.config.ui.TimeoutSetting;
import io.github.boykaframework.config.ui.mobile.server.ServerSetting;
import io.github.boykaframework.enums.ApplicationType;
import io.github.boykaframework.enums.PlatformType;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
final class DriverManager {
    private static final Logger LOGGER = getLogger ();

    private final PlatformType platformType;

    DriverManager () {
        LOGGER.traceEntry ();
        this.platformType = getSession ().getPlatformType ();
        LOGGER.traceExit ();
    }

    void setupDriver () {
        LOGGER.traceEntry ();
        final var settings = getSession ().getSetting ()
            .getUi ();
        if (this.platformType == ANDROID || this.platformType == IOS) {
            final var mobileSetting = getSession ().getMobileSetting ();
            setupAppiumServer (mobileSetting.getServer ());
        } else if (this.platformType == MAC) {
            final var desktopSetting = getSession ().getDesktopSetting ();
            setupAppiumServer (desktopSetting.getServer ());
        }
        final IDriverManager driverManager = switch (this.platformType) {
            case WEB -> new WebDriverManager ();
            case ANDROID -> new AndroidManager ();
            case IOS -> new IOSManager ();
            default -> new MacManager ();
        };
        driverManager.setupDriver ();
        setDriverWaits (settings.getTimeout ());
        LOGGER.traceExit ();
    }

    private void setDriverWaits (final TimeoutSetting timeoutSetting) {
        LOGGER.traceEntry ();
        final var session = getSession ();
        final var driver = session.getDriver ();
        final var timeouts = driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (timeoutSetting.getImplicitWait ()));
        if (this.platformType == PlatformType.WEB || (this.platformType != MAC && session.getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == ApplicationType.WEB)) {
            timeouts.pageLoadTimeout (ofSeconds (timeoutSetting.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (timeoutSetting.getScriptTimeout ()));
        }
        session.setWait (new WebDriverWait (driver, ofSeconds (timeoutSetting.getExplicitWait ())));
        LOGGER.traceExit ();
    }

    private void setupAppiumServer (final ServerSetting serverSetting) {
        getSession ().setServiceManager (new ServiceManager (serverSetting));
        getSession ().getServiceManager ()
            .startServer ();
    }
}
