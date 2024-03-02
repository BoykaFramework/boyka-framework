/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.config.ui;

import static io.github.boykaframework.utils.SettingUtils.getSetting;

import java.util.Map;

import io.github.boykaframework.config.ui.mobile.MobileSetting;
import io.github.boykaframework.config.ui.web.WebSetting;
import lombok.Data;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
@Data
public class UISetting {
    private LogSetting                 logging    = new LogSetting ();
    private Map<String, MobileSetting> mobile;
    private ScreenshotSetting          screenshot = new ScreenshotSetting ();
    private TimeoutSetting          timeout    = new TimeoutSetting ();
    private Map<String, WebSetting> web;

    /**
     * Get Mobile settings.
     *
     * @param key config key for mobile
     *
     * @return the {@link MobileSetting}
     */
    public MobileSetting getMobileSetting (final String key) {
        return getSetting (this.mobile, key);
    }

    /**
     * Gets the web setting.
     *
     * @param key the config key for Web
     *
     * @return the {@link WebSetting}
     */
    public WebSetting getWebSetting (final String key) {
        return getSetting (this.web, key);
    }
}
