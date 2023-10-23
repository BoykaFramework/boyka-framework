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

package com.github.wasiqb.boyka.config.ui.mobile.device;

import static com.github.wasiqb.boyka.enums.DeviceType.VIRTUAL;
import static com.github.wasiqb.boyka.enums.OS.ANDROID;

import java.util.Map;

import com.github.wasiqb.boyka.enums.DeviceType;
import com.github.wasiqb.boyka.enums.OS;
import lombok.Data;

/**
 * Appium Device settings.
 *
 * @author Wasiq Bhamla
 * @since 08-Sept-2022
 */
@Data
public class DeviceSetting {
    private boolean              acceptAlerts           = true;
    private ApplicationSetting   application;
    private Map<String, Object>  capabilities;
    private boolean              clearFiles             = true;
    private boolean              clearLogs              = true;
    private boolean              fullReset;
    private boolean              ignoreUnimportantViews = true;
    private String               name;
    private boolean              noReset;
    private OS                   os                     = ANDROID;
    private int                  serverInstallTimeout   = 30;
    private int                  serverLaunchTimeout    = 30;
    private SwipeSetting         swipe                  = new SwipeSetting ();
    private int                  systemPort             = 8200;
    private DeviceType           type                   = VIRTUAL;
    private int                  typingSpeed            = 60;
    private String               uniqueId;
    private String               version;
    private VideoSetting         video                  = new VideoSetting ();
    private VirtualDeviceSetting virtualDevice          = new VirtualDeviceSetting ();
    private WDASetting           wda;
}
