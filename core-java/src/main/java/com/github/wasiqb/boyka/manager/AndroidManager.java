package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.AutomationType.UI_AUTOMATOR;
import static com.github.wasiqb.boyka.enums.DeviceType.CLOUD;
import static com.github.wasiqb.boyka.enums.DeviceType.VIRTUAL;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static io.appium.java_client.Setting.IGNORE_UNIMPORTANT_VIEWS;
import static java.time.Duration.ofSeconds;

import com.github.wasiqb.boyka.config.ui.mobile.MobileSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.ApplicationSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.VirtualDeviceSetting;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.enums.DeviceType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

class AndroidManager implements IDriverManager {
    private final MobileSetting mobileSetting;
    private final DeviceSetting settings;

    public AndroidManager () {
        this.mobileSetting = getSession ().getMobileSetting ();
        this.settings = this.mobileSetting.getDevice ();
    }

    @Override
    public void setupDriver () {
        final var automation = this.settings.getAutomation ();
        if (automation == UI_AUTOMATOR) {
            setupUiAutomatorDriver (this.mobileSetting.getServer ()
                .getCloud ());
        }
        setupAndroidSettings ();
    }

    private void setAndroidApplicationOptions (final UiAutomator2Options options,
        final ApplicationSetting application) {
        setupApplicationOptions (application, options);
        options.setAppWaitActivity (application.getWaitActivity ());
        options.setAppWaitDuration (ofSeconds (application.getWaitTimeout ()));
        options.setAndroidInstallTimeout (ofSeconds (application.getInstallTimeout ()));
    }

    private void setAvdOptions (final UiAutomator2Options options, final DeviceType type,
        final VirtualDeviceSetting avd) {
        if (type == VIRTUAL && avd != null) {
            options.setAvd (avd.getName ());
            options.setIsHeadless (avd.isHeadless ());
            options.setAvdLaunchTimeout (ofSeconds (avd.getLaunchTimeout ()));
            options.setAvdReadyTimeout (ofSeconds (avd.getReadyTimeout ()));
        }
    }

    private void setCommonUiAutomatorOptions (final UiAutomator2Options options) {
        setAndroidApplicationOptions (options, this.settings.getApplication ());
        options.setAutomationName (this.settings.getAutomation ()
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
        options.setFullReset (this.settings.isFullReset ());
        options.setUiautomator2ServerLaunchTimeout (ofSeconds (this.settings.getServerLaunchTimeout ()));
        options.setUiautomator2ServerInstallTimeout (ofSeconds (this.settings.getServerInstallTimeout ()));
    }

    private void setupAndroidSettings () {
        final AndroidDriver driver = (AndroidDriver) getSession ().getDriver ();
        driver.setSetting (IGNORE_UNIMPORTANT_VIEWS, this.settings.isIgnoreUnimportantViews ());
    }

    private void setupUiAutomatorDriver (final CloudProviders cloudProviders) {
        final UiAutomator2Options options = new UiAutomator2Options ();
        setCommonUiAutomatorOptions (options);
        if (this.settings.getType () == CLOUD) {
            setupCloudMobileDriver (options, cloudProviders, this.settings);
        } else {
            setLocalUiAutomatorOptions (options);
        }
        setDriver (new AndroidDriver (getSession ().getServiceManager ()
            .getServiceUrl (), options));
    }
}
