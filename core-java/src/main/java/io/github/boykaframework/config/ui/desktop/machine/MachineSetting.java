/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

package io.github.boykaframework.config.ui.desktop.machine;

import static io.github.boykaframework.enums.DeviceType.REAL;
import static io.github.boykaframework.enums.OS.MAC;

import io.github.boykaframework.config.LanguageSetting;
import io.github.boykaframework.config.ui.VideoSetting;
import io.github.boykaframework.enums.DeviceType;
import io.github.boykaframework.enums.OS;
import lombok.Data;

/**
 * Machine relayed setting
 *
 * @author Wasiq Bhamla
 * @since 23-May-2025
 */
@Data
public class MachineSetting {
    private ApplicationSetting application          = new ApplicationSetting ();
    private int                commandTimeout       = 60;
    private boolean            eventTimings;
    private boolean            experimental;
    private boolean            fullReset;
    private LanguageSetting    language             = new LanguageSetting ();
    private boolean            noReset;
    private OS                 os                   = MAC;
    private int                serverStartupTimeout = 60;
    private boolean            showServerLogs;
    private int                systemPort           = 4724;
    private DeviceType         type                 = REAL;
    private String             version;
    private VideoSetting       video                = new VideoSetting ();
}
