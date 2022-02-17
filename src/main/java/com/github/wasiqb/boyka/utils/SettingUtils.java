package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.constants.Messages.NO_SETTING_FILE_FOUND;
import static com.github.wasiqb.boyka.utils.JsonParser.fromFile;
import static java.util.Objects.requireNonNull;

import com.github.wasiqb.boyka.config.FrameworkSetting;

public final class SettingUtils {
    private static FrameworkSetting frameworkSetting;

    public static FrameworkSetting loadSetting () {
        if (frameworkSetting == null) {
            final var filePath = SettingUtils.class.getClassLoader ()
                .getResource ("test-config.json");
            frameworkSetting = fromFile (requireNonNull (filePath.getPath (), NO_SETTING_FILE_FOUND.getMessage ()));
        }
        return frameworkSetting;
    }

    private SettingUtils () {
        // Util class
    }
}
