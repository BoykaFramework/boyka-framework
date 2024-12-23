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

import io.github.boykaframework.enums.DeviceType;
import io.github.boykaframework.enums.OS;
import io.github.boykaframework.enums.PermissionDecision;
import io.github.boykaframework.enums.Permissions;
import lombok.Data;

/**
 * Appium Device settings.
 *
 * @author Wasiq Bhamla
 * @since 08-Sept-2022
 */
@Data
public class DeviceSetting {
    private boolean                              acceptAlerts           = true;
    private int                                  adbTimeout             = 30;
    private ApplicationSetting                   application;
    private boolean                              autoGrantPermissions;
    private Map<String, Object>                  capabilities;
    private boolean                              clearFiles             = true;
    private boolean                              clearLogs              = true;
    private int                                  commandTimeout         = 60;
    private boolean                              fullReset;
    private boolean                              ignoreUnimportantViews = true;
    private String                               name;
    private boolean                              noReset;
    private OS                                   os                     = ANDROID;
    private Map<Permissions, PermissionDecision> permissions;
    private int                                  serverInstallTimeout   = 30;
    private int                                  serverLaunchTimeout    = 30;
    private SwipeSetting                         swipe                  = new SwipeSetting ();
    private int                                  systemPort             = 8200;
    private DeviceType                           type                   = VIRTUAL;
    private int                                  typingSpeed            = 60;
    private String                               uniqueId;
    private String                               version;
    private VideoSetting                         video                  = new VideoSetting ();
    private VirtualDeviceSetting                 virtualDevice          = new VirtualDeviceSetting ();
    private WDASetting                           wda;
    private int                                  webViewConnectRetries  = 3;
    private int                                  webViewConnectTimeout  = 60;
}
