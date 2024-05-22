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

package io.github.boykaframework.config.api;

import static io.github.boykaframework.enums.Message.CONFIG_KEY_NOT_FOUND;
import static io.github.boykaframework.enums.Message.NO_API_SETTINGS_FOUND;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static io.github.boykaframework.utils.Validator.requireNonNull;
import static java.lang.String.join;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import lombok.Data;
import org.apache.logging.log4j.Logger;

/**
 * Default API settings.
 *
 * @author Wasiq Bhamla
 * @since 21-May-2024
 */
@Data
public class DefaultApiSetting {
    private static final Logger LOGGER = getLogger ();

    private Map<String, ApiSetting> api;
    private String                  basePath       = "";
    private LogSetting              logging        = new LogSetting ();
    private String                  schemaPath     = "";
    private TimeoutSetting          timeout        = new TimeoutSetting ();
    private boolean                 validateSsl    = true;
    private boolean                 verifyHostName = true;

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
