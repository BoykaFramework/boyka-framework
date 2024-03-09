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

import static io.github.boykaframework.enums.ApplicationType.WEB;
import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.enums.DeviceType.VIRTUAL;
import static io.github.boykaframework.enums.Message.SESSION_NOT_STARTED;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.manager.ParallelSession.setDriver;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.Validator.setOptionIfPresent;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.wda.XcodeCertificate;
import io.github.boykaframework.config.ui.mobile.MobileSetting;
import io.github.boykaframework.config.ui.mobile.device.ApplicationSetting;
import io.github.boykaframework.config.ui.mobile.device.DeviceSetting;
import io.github.boykaframework.config.ui.mobile.device.VirtualDeviceSetting;
import io.github.boykaframework.config.ui.mobile.device.WDASetting;
import io.github.boykaframework.enums.DeviceType;
import org.openqa.selenium.SessionNotCreatedException;

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
            setupCloudDriverOptions (options, this.settings.getCapabilities (), this.mobileSetting.getServer ()
                .getTarget ());
        } else {
            setupLocalSimulatorOptions (options);
        }
        try {
            setDriver (new IOSDriver (getSession ().getServiceManager ()
                .getServiceUrl (), options));
        } catch (final SessionNotCreatedException e) {
            handleAndThrow (SESSION_NOT_STARTED, e);
        }
        navigateToBaseUrl (this.settings.getApplication ());
    }

    private void setApplicationCapabilities (final XCUITestOptions options, final ApplicationSetting application) {
        if (application.getType () == WEB) {
            options.withBrowserName (application.getBrowser ()
                .name ());
        } else {
            setupApplicationOptions (application, options);
            options.setBundleId (application.getBundleId ());
        }
    }

    private void setCommonCapabilities (final XCUITestOptions options) {
        options.setAutomationName (this.mobileSetting.getServer ()
            .getDriver ()
            .getName ());
        options.setPlatformName (this.settings.getOs ()
            .name ());
        options.setPlatformVersion (this.settings.getVersion ());
        options.setDeviceName (this.settings.getName ());
        options.setUdid (this.settings.getUniqueId ());
        setApplicationCapabilities (options, this.settings.getApplication ());
    }

    private void setWdaOptions (final WDASetting wda, final XCUITestOptions options) {
        if (!isNull (wda)) {
            setOptionIfPresent (wda.getLocalPort (), options::setWdaLocalPort);
            options.setUseNewWDA (wda.isUseNew ());
            setOptionIfPresent (wda.getLaunchTimeout (), v -> options.setWdaLaunchTimeout (ofSeconds (v)));
            setOptionIfPresent (wda.getStartupRetries (), options::setWdaStartupRetries);
            setOptionIfPresent (wda.getConnectionTimeout (), i -> options.setWdaConnectionTimeout (ofSeconds (i)));
            setOptionIfPresent (wda.getStartupRetryInterval (),
                i -> options.setWdaStartupRetryInterval (ofSeconds (i)));
            options.setUsePrebuiltWda (wda.isUsePrebuilt ());
            setOptionIfPresent (wda.getUpdateBundleId (), options::setUpdatedWdaBundleId);

            if (isNotEmpty (wda.getTeamId ())) {
                final var certificate = new XcodeCertificate (wda.getTeamId (), wda.getSigningId ());
                options.setXcodeCertificate (certificate);
            }
        }
    }

    private void setupLocalSimulatorOptions (final XCUITestOptions options) {
        options.setAutoAcceptAlerts (this.settings.isAcceptAlerts ());
        options.setAutoDismissAlerts (!this.settings.isAcceptAlerts ());
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
