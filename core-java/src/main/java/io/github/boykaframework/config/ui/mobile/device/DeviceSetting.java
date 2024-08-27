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

package io.github.boykaframework.config.ui.mobile.device;

import static io.github.boykaframework.enums.DeviceType.VIRTUAL;
import static io.github.boykaframework.enums.OS.ANDROID;

import java.util.Map;

import io.github.boykaframework.config.BoykaConfig;
import io.github.boykaframework.enums.DeviceType;
import io.github.boykaframework.enums.OS;
import lombok.Data;

/**
 * Appium Device settings.
 *
 * @author Wasiq Bhamla
 * @since 08-Sept-2022
 */
@Data
public class DeviceSetting implements BoykaConfig {
    private boolean              acceptAlerts           = true;
    private Integer              adbTimeout             = 30;
    private ApplicationSetting   application;
    private Map<String, Object>  capabilities;
    private boolean              clearFiles             = true;
    private boolean              clearLogs              = true;
    private boolean              fullReset              = false;
    private boolean              ignoreUnimportantViews = true;
    private String               name;
    private boolean              noReset                = true;
    private OS                   os                     = ANDROID;
    private Integer              serverInstallTimeout   = 30;
    private Integer              serverLaunchTimeout    = 30;
    private SwipeSetting         swipe                  = new SwipeSetting ();
    private Integer              systemPort             = 8200;
    private DeviceType           type                   = VIRTUAL;
    private Integer              typingSpeed            = 60;
    private String               uniqueId;
    private String               version;
    private VideoSetting         video                  = new VideoSetting ();
    private VirtualDeviceSetting virtualDevice          = new VirtualDeviceSetting ();
    private WDASetting           wda                    = new WDASetting ();
}
