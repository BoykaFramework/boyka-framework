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

import com.github.wasiqb.boyka.config.FrameworkSetting;
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
@Data
public class DriverSession<D extends WebDriver> {
    private static final Logger LOGGER = getLogger ();

    private       String           configKey;
    private       D                driver;
    private       PlatformType     platformType;
    private       ServiceManager   serviceManager;
    private final FrameworkSetting setting;
    private       WebDriverWait    wait;

    /**
     * Driver session constructor.
     */
    DriverSession () {
        this.setting = loadSetting ();
        LOGGER.traceExit ();
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
     * Gets current Web settings
     *
     * @return Web Setting
     */
    public WebSetting getWebSetting () {
        return this.setting.getUi ()
            .getWebSetting (this.configKey);
    }
}
