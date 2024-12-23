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

package io.github.boykaframework.manager;

import static io.appium.java_client.Setting.IGNORE_UNIMPORTANT_VIEWS;
import static io.github.boykaframework.enums.ApplicationType.WEB;
import static io.github.boykaframework.enums.AutomationType.UI_AUTOMATOR;
import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.enums.DeviceType.VIRTUAL;
import static io.github.boykaframework.enums.Message.SESSION_NOT_STARTED;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.manager.ParallelSession.setDriver;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.github.boykaframework.config.ui.mobile.MobileSetting;
import io.github.boykaframework.config.ui.mobile.device.ApplicationSetting;
import io.github.boykaframework.config.ui.mobile.device.DeviceSetting;
import io.github.boykaframework.config.ui.mobile.device.VirtualDeviceSetting;
import io.github.boykaframework.enums.DeviceType;
import io.github.boykaframework.enums.TargetProviders;
import io.github.boykaframework.utils.Validator;
import org.openqa.selenium.SessionNotCreatedException;

class AndroidManager implements IDriverManager {
    private final MobileSetting mobileSetting;
    private final DeviceSetting settings;

    public AndroidManager () {
        this.mobileSetting = getSession ().getMobileSetting ();
        this.settings = this.mobileSetting.getDevice ();
    }

    @Override
    public void setupDriver () {
        final var automation = this.mobileSetting.getServer ()
            .getDriver ();
        if (automation == UI_AUTOMATOR) {
            setupUiAutomatorDriver (this.mobileSetting.getServer ()
                .getTarget ());
        }
        setupAndroidSettings ();
    }

    private void setAndroidApplicationOptions (final UiAutomator2Options options,
        final ApplicationSetting application) {
        if (application.getType () == WEB) {
            options.withBrowserName (application.getBrowser ()
                .name ());
            Validator.setOptionIfPresent (application.getChromeDriverPort (), options::setChromedriverPort);
        } else {
            setupApplicationOptions (application, options);
            options.setAppWaitActivity (application.getWaitActivity ());
            options.setAppWaitDuration (ofSeconds (application.getWaitTimeout ()));
            options.setAndroidInstallTimeout (ofSeconds (application.getInstallTimeout ()));
        }
    }

    private void setAvdOptions (final UiAutomator2Options options, final DeviceType type,
        final VirtualDeviceSetting avd) {
        if (type == VIRTUAL && !isNull (avd)) {
            options.setAvd (avd.getName ());
            options.setIsHeadless (avd.isHeadless ());
            options.setAvdLaunchTimeout (ofSeconds (avd.getLaunchTimeout ()));
            options.setAvdReadyTimeout (ofSeconds (avd.getReadyTimeout ()));
        }
    }

    private void setCommonUiAutomatorOptions (final UiAutomator2Options options) {
        setAndroidApplicationOptions (options, this.settings.getApplication ());
        options.setAutomationName (this.mobileSetting.getServer ()
            .getDriver ()
            .getName ());
        options.setPlatformName (this.settings.getOs ()
            .name ());
        options.setPlatformVersion (this.settings.getVersion ());
        options.setDeviceName (this.settings.getName ());
    }

    private void setLocalUiAutomatorOptions (final UiAutomator2Options options) {
        setAvdOptions (options, this.settings.getType (), this.settings.getVirtualDevice ());
        options.setClearSystemFiles (this.settings.isClearFiles ());
        options.setClearDeviceLogsOnStart (this.settings.isClearLogs ());
        options.setNoReset (this.settings.isNoReset ());
        options.setAutoGrantPermissions (settings.isAutoGrantPermissions ());
        options.setFullReset (this.settings.isFullReset ());
        options.setUiautomator2ServerLaunchTimeout (ofSeconds (this.settings.getServerLaunchTimeout ()));
        options.setUiautomator2ServerInstallTimeout (ofSeconds (this.settings.getServerInstallTimeout ()));
        options.setAdbExecTimeout (ofSeconds (this.settings.getAdbTimeout ()));
        options.setSystemPort (this.settings.getSystemPort ());
        options.setUdid (this.settings.getUniqueId ());
    }

    private void setupAndroidSettings () {
        final var driver = (AndroidDriver) getSession ().getDriver ();
        driver.setSetting (IGNORE_UNIMPORTANT_VIEWS, this.settings.isIgnoreUnimportantViews ());
    }

    private void setupUiAutomatorDriver (final TargetProviders targetProviders) {
        final var options = new UiAutomator2Options ();
        setCommonUiAutomatorOptions (options);
        if (this.settings.getType () == CLOUD) {
            setupCloudDriverOptions (options, this.settings.getCapabilities (), targetProviders);
        } else {
            setLocalUiAutomatorOptions (options);
        }
        try {
            setDriver (new AndroidDriver (getSession ().getServiceManager ()
                .getServiceUrl (), options));
        } catch (final SessionNotCreatedException e) {
            handleAndThrow (SESSION_NOT_STARTED, e);
        }
        navigateToBaseUrl (this.settings.getApplication ());
    }
}
