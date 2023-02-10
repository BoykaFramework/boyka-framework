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

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.DeviceType.CLOUD;
import static com.github.wasiqb.boyka.enums.DeviceType.VIRTUAL;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static io.appium.java_client.remote.IOSMobileCapabilityType.XCODE_ORG_ID;
import static io.appium.java_client.remote.IOSMobileCapabilityType.XCODE_SIGNING_ID;
import static java.time.Duration.ofSeconds;

import java.util.Optional;
import java.util.function.Consumer;

import com.github.wasiqb.boyka.config.ui.mobile.MobileSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.ApplicationSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.VirtualDeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.WDASetting;
import com.github.wasiqb.boyka.enums.DeviceType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

class IOSManager implements IDriverManager {
    private final MobileSetting mobileSetting;
    private final DeviceSetting settings;

    IOSManager () {
        this.mobileSetting = getSession ().getMobileSetting ();
        this.settings = this.mobileSetting.getDevice ();
    }

    @Override
    public void setupDriver () {
        final var options = new XCUITestOptions ();
        setCommonCapabilities (options);
        if (this.settings.getType () == CLOUD) {
            setupCloudMobileDriver (options, this.mobileSetting.getServer ()
                .getTarget (), this.settings);
        } else {
            setupLocalSimulatorOptions (options);
        }
        setDriver (new IOSDriver (getSession ().getServiceManager ()
            .getServiceUrl (), options));
    }

    private void setApplicationCapabilities (final XCUITestOptions options, final ApplicationSetting application) {
        setupApplicationOptions (application, options);
        options.setBundleId (application.getBundleId ());
    }

    private void setCommonCapabilities (final XCUITestOptions options) {
        options.setAutomationName (this.mobileSetting.getServer ()
            .getDriver ()
            .getName ());
        options.setPlatformName (this.settings.getOs ()
            .name ());
        options.setPlatformVersion (this.settings.getVersion ());
        options.setDeviceName (this.settings.getName ());
        setApplicationCapabilities (options, this.settings.getApplication ());
    }

    private <T> void setOptionIfPresent (final T value, final Consumer<T> action) {
        if (value != null) {
            if (value instanceof Integer) {
                Optional.of (value)
                    .filter (i -> (Integer) i > 0)
                    .ifPresent (action);
            } else {
                Optional.of (value)
                    .ifPresent (action);
            }
        }
    }

    private void setWdaOptions (final WDASetting wda, final XCUITestOptions options) {
        if (wda != null) {
            setOptionIfPresent (wda.getLocalPort (), options::setWdaLocalPort);
            options.setUseNewWDA (wda.isUseNew ());
            setOptionIfPresent (wda.getLaunchTimeout (), v -> options.setWdaLaunchTimeout (ofSeconds (v)));
            setOptionIfPresent (wda.getStartupRetries (), options::setWdaStartupRetries);
            setOptionIfPresent (wda.getConnectionTimeout (), i -> options.setWdaConnectionTimeout (ofSeconds (i)));
            setOptionIfPresent (wda.getStartupRetryInterval (),
                i -> options.setWdaStartupRetryInterval (ofSeconds (i)));
            options.setUsePrebuiltWda (wda.isUsePrebuilt ());
            setOptionIfPresent (wda.getUpdateBundleId (), options::setUpdatedWdaBundleId);
            setOptionIfPresent (wda.getTeamId (), v -> options.setCapability (XCODE_ORG_ID, v));
            setOptionIfPresent (wda.getSigningId (), v -> options.setCapability (XCODE_SIGNING_ID, v));
        }
    }

    private void setupLocalSimulatorOptions (final XCUITestOptions options) {
        options.setAutoAcceptAlerts (this.settings.isGrantPermission ());
        options.setAutoDismissAlerts (!this.settings.isGrantPermission ());
        setupApplicationOptions (this.settings.getApplication (), options);
        setupVirtualDeviceSetting (this.settings.getType (), this.settings.getVirtualDevice (), options);
        options.setBundleId (this.settings.getApplication ()
            .getBundleId ());
        options.setClearSystemFiles (this.settings.isClearFiles ());
        options.setNoReset (this.settings.isNoReset ());
        options.setFullReset (this.settings.isFullReset ());
        options.setMaxTypingFrequency (this.settings.getTypingSpeed ());
        setWdaOptions (this.settings.getWda (), options);
    }

    private void setupVirtualDeviceSetting (final DeviceType deviceType, final VirtualDeviceSetting virtualDevice,
        final XCUITestOptions options) {
        if (deviceType == VIRTUAL) {
            options.setConnectHardwareKeyboard (virtualDevice.isConnectKeyboard ());
            options.setSimulatorStartupTimeout (ofSeconds (virtualDevice.getLaunchTimeout ()));
            options.setIsHeadless (virtualDevice.isHeadless ());
        }
    }
}
