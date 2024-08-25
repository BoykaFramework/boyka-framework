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

package io.github.boykaframework.config.ui.mobile.device;

import static io.github.boykaframework.enums.DeviceType.VIRTUAL;

import java.util.Map;

import io.github.boykaframework.enums.DeviceType;
import lombok.Data;

@Data
public class CommonDeviceSetting {
    private CommonAndroidSetting android       = new CommonAndroidSetting ();
    private ApplicationSetting   application   = new ApplicationSetting ();
    private Map<String, Object>  capabilities;
    private boolean              clearFiles    = true;
    private boolean              fullReset     = false;
    private CommonIOSSetting     ios           = new CommonIOSSetting ();
    private boolean              noReset       = true;
    private SwipeSetting         swipe         = new SwipeSetting ();
    private DeviceType           type          = VIRTUAL;
    private VideoSetting         video         = new VideoSetting ();
    private VirtualDeviceSetting virtualDevice = new VirtualDeviceSetting ();
}