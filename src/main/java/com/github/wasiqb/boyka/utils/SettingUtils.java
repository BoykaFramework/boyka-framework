package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.utils.JsonParser.fromFile;

import com.github.wasiqb.boyka.config.FrameworkSetting;

public final class SettingUtils {
    private static FrameworkSetting frameworkSetting;

    public static FrameworkSetting loadSetting () {
        if (frameworkSetting == null) {
            frameworkSetting = fromFile ("test-config.json", FrameworkSetting.class);
        }
        return frameworkSetting;
    }

    private SettingUtils () {
        // Util class
    }
}
