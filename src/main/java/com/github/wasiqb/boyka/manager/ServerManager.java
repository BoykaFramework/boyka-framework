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

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;

import com.github.wasiqb.boyka.config.ui.MobileSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ServerManager {
    private       AppiumServiceBuilder builder;
    private final MobileSetting        setting;

    public ServerManager (final ApplicationType applicationType, final String appiumKey) {
        this.setting = loadSetting ().getUi ()
            .getMobileSetting (applicationType, appiumKey);
    }
}
