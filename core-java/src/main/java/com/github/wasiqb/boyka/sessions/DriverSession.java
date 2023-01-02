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

package com.github.wasiqb.boyka.sessions;

import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.HashMap;
import java.util.Map;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.config.api.ApiSetting;
import com.github.wasiqb.boyka.config.ui.mobile.MobileSetting;
import com.github.wasiqb.boyka.config.ui.web.WebSetting;
import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.manager.ServiceManager;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Driver session class containing everything needed to handle current session.
 *
 * @param <D> {@link WebDriver}
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
@SuppressWarnings ("unchecked")
@Data
public class DriverSession<D extends WebDriver> {
    private static final Logger LOGGER = getLogger ();

    private       String              configKey;
    private       D                   driver;
    private       PlatformType        platformType;
    private       ServiceManager      serviceManager;
    private final FrameworkSetting    setting;
    private       Map<String, Object> sharedData;
    private       WebDriverWait       wait;

    /**
     * Driver session constructor.
     */
    DriverSession () {
        this.setting = loadSetting ();
        this.sharedData = new HashMap<> ();
        LOGGER.traceExit ();
    }

    /**
     * Clears all the shared data for the session
     */
    public void clearSharedData () {
        this.sharedData.clear ();
    }

    /**
     * Gets API specific settings
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting () {
        return this.setting.getApiSetting (this.configKey);
    }

    /**
     * Gets Current Mobile settings
     *
     * @return Mobile setting
     */
    public MobileSetting getMobileSetting () {
        return this.setting.getUi ()
            .getMobileSetting (this.configKey);
    }

    /**
     * Gets the shared data.
     *
     * @param key Key of data to be retrieved
     * @param <T> Type of data
     *
     * @return Saved data
     */
    public <T> T getSharedData (final String key) {
        return (T) this.sharedData.get (key);
    }

    /**
     * Gets current Web settings
     *
     * @return Web Setting
     */
    public WebSetting getWebSetting () {
        return this.setting.getUi ()
            .getWebSetting (this.configKey);
    }

    /**
     * Removes the shared data.
     *
     * @param key Key to shared data
     * @param <T> Type of shared data
     *
     * @return Data which is now removed
     */
    public <T> T removeSharedData (final String key) {
        return (T) this.sharedData.remove (key);
    }

    /**
     * Save shared data.
     *
     * @param key Key of data
     * @param data Data to be saved
     * @param <T> Type of data
     */
    public <T> void setSharedData (final String key, final T data) {
        this.sharedData.put (key, data);
    }
}
