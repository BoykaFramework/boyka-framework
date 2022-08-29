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

package com.github.wasiqb.boyka.config.ui;

import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import com.github.wasiqb.boyka.enums.Message;
import lombok.Data;
import org.apache.logging.log4j.Logger;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
@Data
public class UISetting {
    private static final Logger LOGGER = getLogger ();

    private ScreenshotSetting       screenshot = new ScreenshotSetting ();
    private TimeoutSetting          timeout    = new TimeoutSetting ();
    private Map<String, WebSetting> web;

    /**
     * Gets the web setting.
     *
     * @param key the config key for Web
     *
     * @return the {@link WebSetting}
     */
    public WebSetting getWebSetting (final String key) {
        LOGGER.traceEntry ("Key: {}", key);
        if (!this.web.containsKey (key)) {
            final var keys = String.join (", ", this.web.keySet ());
            throwError (Message.CONFIG_KEY_NOT_FOUND, key, keys);
        }
        return LOGGER.traceExit (this.web.get (key));
    }
}
