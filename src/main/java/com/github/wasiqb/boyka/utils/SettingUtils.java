/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.utils.JsonParser.fromFile;

import com.github.wasiqb.boyka.config.FrameworkSetting;

/**
 * Utility class to ready config JSON file.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class SettingUtils {
    private static FrameworkSetting frameworkSetting;

    /**
     * Loads the config JSON file only once.
     *
     * @return {@link FrameworkSetting}
     */
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
