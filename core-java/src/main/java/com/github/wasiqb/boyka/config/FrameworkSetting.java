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

package com.github.wasiqb.boyka.config;

import static com.github.wasiqb.boyka.enums.Message.CONFIG_KEY_NOT_FOUND;
import static com.github.wasiqb.boyka.enums.Message.NO_API_SETTINGS_FOUND;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static com.github.wasiqb.boyka.utils.Validator.requireNonNull;
import static java.lang.String.join;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import com.github.wasiqb.boyka.config.api.ApiSetting;
import com.github.wasiqb.boyka.config.ui.UISetting;
import lombok.Data;
import org.apache.logging.log4j.Logger;

/**
 * Framework setting.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
@Data
public class FrameworkSetting {
    private static final Logger LOGGER = getLogger ();

    private Map<String, ApiSetting> api;
    private UISetting               ui;

    /**
     * Get API setting.
     *
     * @param key API config key
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting (final String key) {
        LOGGER.traceEntry ("Key: {}", key);
        if (!this.api.containsKey (key)) {
            final var keys = join (", ", this.api.keySet ());
            throwError (CONFIG_KEY_NOT_FOUND, key, keys);
        }
        return LOGGER.traceExit (requireNonNull (this.api.get (key), NO_API_SETTINGS_FOUND, key));
    }
}
