/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.utils;

import static io.github.boykaframework.enums.Message.CONFIG_KEY_NOT_FOUND;
import static io.github.boykaframework.utils.JsonUtil.fromFile;
import static java.lang.String.join;
import static java.lang.System.getProperty;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.nio.file.Path;
import java.util.Map;

import io.github.boykaframework.config.FrameworkSetting;
import org.apache.logging.log4j.Logger;

/**
 * Utility class to ready config JSON file.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class SettingUtils {
    private static final Logger LOGGER = getLogger ();

    private static FrameworkSetting frameworkSetting;

    /**
     * Gets the settings object from Map.
     *
     * @param settings Settings Map
     * @param key Setting key
     * @param <T> Setting object type
     *
     * @return Setting object
     */
    public static <T> T getSetting (final Map<String, T> settings, final String key) {
        LOGGER.traceEntry ("Key: {}", key);
        if (!settings.containsKey (key)) {
            final var keys = join (", ", settings.keySet ());
            ErrorHandler.throwError (CONFIG_KEY_NOT_FOUND, key, keys);
        }
        return LOGGER.traceExit (settings.get (key));
    }

    /**
     * Loads the config JSON file only once.
     *
     * @return {@link FrameworkSetting}
     */
    public static FrameworkSetting loadSetting () {
        LOGGER.traceEntry ();
        if (isNull (frameworkSetting)) {
            final var defaultPath = Path.of (getProperty ("user.dir"), "src/test/resources")
                .toString ();
            final var configDirectory = ofNullable (getenv ("BOYKA_CONFIG_PATH")).orElse (
                ofNullable (getProperty ("boyka.config.path")).orElse (defaultPath));
            final var configPath = Path.of (configDirectory, "boyka-config.json")
                .toString ();
            frameworkSetting = fromFile (configPath, FrameworkSetting.class);
        }
        return LOGGER.traceExit (frameworkSetting);
    }

    private SettingUtils () {
        // Util class
    }
}
