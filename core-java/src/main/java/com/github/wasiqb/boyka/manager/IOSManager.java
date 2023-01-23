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
import static java.time.Duration.ofSeconds;

import com.github.wasiqb.boyka.config.ui.mobile.MobileSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.VirtualDeviceSetting;
import com.github.wasiqb.boyka.enums.DeviceType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

public class IOSManager implements IDriverManager {
    private final MobileSetting mobileSetting;
    private final DeviceSetting settings;

    public IOSManager () {
        this.mobileSetting = getSession ().getMobileSetting ();
        this.settings = this.mobileSetting.getDevice ();
    }

    @Override
    public void setupDriver () {
        final var options = new XCUITestOptions ();
        if (this.settings.getType () == CLOUD) {
            setupCloudMobileDriver (options, this.mobileSetting.getServer ()
                .getCloud (), this.settings);
        } else {
            options.setAutomationName (this.settings.getAutomation ()
                .getName ());
            options.setPlatformName (this.settings.getOs ()
                .name ());
            options.setPlatformVersion (this.settings.getVersion ());
            options.setDeviceName (this.settings.getName ());
            options.setAutoAcceptAlerts (this.settings.isGrantPermission ());
            options.setAutoDismissAlerts (!this.settings.isGrantPermission ());
            setupApplicationOptions (this.settings.getApplication (), options);
            setupVirtualDeviceSetting (this.settings.getType (), this.settings.getVirtualDevice (), options);
            options.setBundleId (this.settings.getApplication ()
                .getBundleId ());
            options.setClearSystemFiles (this.settings.isClearFiles ());
        }
        setDriver (new IOSDriver (getSession ().getServiceManager ()
            .getServiceUrl (), options));
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
