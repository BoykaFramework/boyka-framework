package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.nio.file.Path;
import java.util.HashMap;

import com.github.wasiqb.boyka.config.ui.mobile.device.ApplicationSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
import com.github.wasiqb.boyka.enums.CloudProviders;
import io.appium.java_client.remote.options.BaseOptions;
import io.appium.java_client.remote.options.SupportsAppOption;

interface IDriverManager {
    default <E extends BaseOptions<E>, T extends SupportsAppOption<E>> void setupApplicationOptions (
        final ApplicationSetting application, final T options) {
        if (isNotEmpty (application.getPath ())) {
            if (!application.isExternal ()) {
                options.setApp (Path.of (getProperty ("user.dir"), "/src/test/resources", application.getPath ())
                    .toString ());
            } else {
                options.setApp (interpolate (application.getPath ()));
            }
        }
    }

    default <E extends BaseOptions<E>> void setupCloudDriverOptions (final E options, final DeviceSetting deviceSetting,
        final String optionPrefix) {
        final var capabilities = deviceSetting.getCapabilities ();
        if (capabilities != null) {
            final var optionCapabilities = new HashMap<String, Object> ();
            capabilities.forEach ((k, v) -> {
                if (v instanceof String) {
                    optionCapabilities.put (k, interpolate (v.toString ()));
                } else {
                    optionCapabilities.put (k, v);
                }
            });
            options.setCapability (format ("{0}:options", optionPrefix), optionCapabilities);
        }
    }

    default <E extends BaseOptions<E>> void setupCloudMobileDriver (final E options,
        final CloudProviders cloudProviders, final DeviceSetting deviceSetting) {
        switch (cloudProviders) {
            case BROWSER_STACK:
                setupCloudDriverOptions (options, deviceSetting, "bstack");
                break;
            case LAMBDA_TEST:
            default:
                setupCloudDriverOptions (options, deviceSetting, "lt");
                break;
        }
    }

    void setupDriver ();
}
