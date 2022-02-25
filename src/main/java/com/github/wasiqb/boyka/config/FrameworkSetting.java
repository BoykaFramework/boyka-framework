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

import static com.github.wasiqb.boyka.enums.Messages.NO_API_SETTINGS_FOUND;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Map;

import com.github.wasiqb.boyka.config.api.ApiSetting;
import lombok.Data;

@Data
public class FrameworkSetting {
    private Map<String, ApiSetting> api;
    private UISetting               ui;

    public ApiSetting getApiSetting (final String key) {
        return requireNonNull (this.api.get (key), format (NO_API_SETTINGS_FOUND.getMessage (), key));
    }
}