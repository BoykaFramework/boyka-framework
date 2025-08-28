/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.enums.Message.CLOUD_NOT_SUPPORTED;
import static io.github.boykaframework.enums.Message.SESSION_NOT_STARTED;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.manager.ParallelSession.setDriver;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static io.github.boykaframework.utils.Validator.setOptionIfPresent;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNullElse;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.options.WindowsOptions;
import io.github.boykaframework.config.ui.desktop.machine.MachineSetting;
import org.openqa.selenium.SessionNotCreatedException;

class WindowsManager implements IDriverManager {
    private final MachineSetting settings;

    public WindowsManager () {
        final var desktopSetting = getSession ().getDesktopSetting ();
        this.settings = desktopSetting.getMachine ();
    }

    @Override
    public void setupDriver () {
        final var options = new WindowsOptions ();
        setCommonOptions (options);
        setApplicationOptions (options);
        setTimeoutOptions (options);

        createDriver (options);
    }

    private void createDriver (final WindowsOptions options) {
        try {
            setDriver (new WindowsDriver (getSession ().getServiceManager ()
                .getServiceUrl (), options));
        } catch (final SessionNotCreatedException e) {
            handleAndThrow (SESSION_NOT_STARTED, e);
        }
    }

    private void setApplicationOptions (final WindowsOptions options) {
        final var appSetting = this.settings.getApplication ();
        options.setApp (requireNonNullElse (appSetting.getPath (), appSetting.getBundleId ()));
        setOptionIfPresent (appSetting.getWorkingDir (), options::setAppWorkingDir);
        setOptionIfPresent (appSetting.getArguments (), options::setAppArguments);
        setOptionIfPresent (appSetting.getTopLevelWindow (), options::setAppTopLevelWindow);
        options.setWaitForAppLaunch (ofSeconds (appSetting.getLaunchTimeout ()));
    }

    private void setCommonOptions (final WindowsOptions options) {
        if (this.settings.getType () == CLOUD) {
            throwError (CLOUD_NOT_SUPPORTED, getSession ().getPlatformType ());
        }

        setOptionIfPresent (this.settings.getVersion (), options::setPlatformVersion);
        setOptionIfPresent (this.settings.isEventTimings (), options::setEventTimings);
        setOptionIfPresent (this.settings.isFullReset (), options::setFullReset);
        setOptionIfPresent (this.settings.isNoReset (), options::setNoReset);
        options.setExperimentalWebDriver (this.settings.isExperimental ());
        options.setSystemPort (this.settings.getSystemPort ());
    }

    private void setTimeoutOptions (final WindowsOptions options) {
        setOptionIfPresent (ofSeconds (this.settings.getServerStartupTimeout ()), options::setCreateSessionTimeout);
        setOptionIfPresent (ofSeconds (this.settings.getCommandTimeout ()), options::setNewCommandTimeout);
    }
}

