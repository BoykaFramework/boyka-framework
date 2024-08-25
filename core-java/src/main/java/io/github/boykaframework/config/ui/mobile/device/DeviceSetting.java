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
import static io.github.boykaframework.utils.Validator.requireNonNullElse;

import java.util.Map;

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
public class DeviceSetting {
    private static final CommonAndroidSetting ANDROID_SETTING = new CommonAndroidSetting ();
    private static final CommonDeviceSetting  DEVICE_SETTING  = new CommonDeviceSetting ();
    private static final CommonIOSSetting     IOS_SETTING     = new CommonIOSSetting ();

    private boolean              acceptAlerts           = true;
    private int                  adbTimeout             = 30;
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
    private SwipeSetting         swipe;
    private int                  systemPort             = 8200;
    private DeviceType           type                   = VIRTUAL;
    private int                  typingSpeed            = 60;
    private String               uniqueId;
    private String               version;
    private VideoSetting         video;
    private VirtualDeviceSetting virtualDevice;
    private WDASetting           wda;

    public int getAdbTimeout () {
        return requireNonNullElse (this.adbTimeout, ANDROID_SETTING.getAdbTimeout ());
    }

    public ApplicationSetting getApplication () {
        return requireNonNullElse (this.application, DEVICE_SETTING.getApplication ());
    }

    public Map<String, Object> getCapabilities () {
        return requireNonNullElse (this.capabilities, DEVICE_SETTING.getCapabilities ());
    }

    public int getServerInstallTimeout () {
        return requireNonNullElse (this.serverInstallTimeout, ANDROID_SETTING.getServerInstallTimeout ());
    }

    public int getServerLaunchTimeout () {
        return requireNonNullElse (this.serverLaunchTimeout, ANDROID_SETTING.getServerLaunchTimeout ());
    }

    public SwipeSetting getSwipe () {
        return requireNonNullElse (this.swipe, DEVICE_SETTING.getSwipe ());
    }

    public int getSystemPort () {
        return requireNonNullElse (this.systemPort, ANDROID_SETTING.getSystemPort ());
    }

    public DeviceType getType () {
        return requireNonNullElse (this.type, DEVICE_SETTING.getType ());
    }

    public int getTypingSpeed () {
        return requireNonNullElse (this.typingSpeed, IOS_SETTING.getTypingSpeed ());
    }

    public VideoSetting getVideo () {
        return requireNonNullElse (this.video, DEVICE_SETTING.getVideo ());
    }

    public VirtualDeviceSetting getVirtualDevice () {
        return requireNonNullElse (this.virtualDevice, DEVICE_SETTING.getVirtualDevice ());
    }

    public WDASetting getWda () {
        return requireNonNullElse (this.wda, IOS_SETTING.getWda ());
    }

    public boolean isAcceptAlerts () {
        return requireNonNullElse (this.acceptAlerts, IOS_SETTING.isAcceptAlerts ());
    }

    public boolean isClearFiles () {
        return requireNonNullElse (this.clearFiles, DEVICE_SETTING.isClearFiles ());
    }

    public boolean isClearLogs () {
        return requireNonNullElse (this.clearLogs, ANDROID_SETTING.isClearLogs ());
    }

    public boolean isFullReset () {
        return requireNonNullElse (this.fullReset, DEVICE_SETTING.isFullReset ());
    }

    public boolean isIgnoreUnimportantViews () {
        return requireNonNullElse (this.ignoreUnimportantViews, ANDROID_SETTING.isIgnoreUnimportantViews ());
    }

    public boolean isNoReset () {
        return requireNonNullElse (this.noReset, DEVICE_SETTING.isNoReset ());
    }
}
