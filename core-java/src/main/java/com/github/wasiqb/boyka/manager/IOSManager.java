package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.DeviceType.CLOUD;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;

import com.github.wasiqb.boyka.config.ui.mobile.MobileSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
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
            options.setAutoAcceptAlerts (this.settings.isGrantPermission ());
            options.setAutoDismissAlerts (!this.settings.isGrantPermission ());
            setupApplicationOptions (this.settings.getApplication (), options);
            options.setBundleId (this.settings.getApplication ()
                .getBundleId ());
        }
        setDriver (new IOSDriver (getSession ().getServiceManager ()
            .getServiceUrl (), options));
    }
}
