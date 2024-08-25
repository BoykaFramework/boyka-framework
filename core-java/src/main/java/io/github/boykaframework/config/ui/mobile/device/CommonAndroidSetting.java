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

import lombok.Data;

@Data
public class CommonAndroidSetting {
    private int     adbTimeout             = 30;
    private boolean clearLogs              = true;
    private boolean ignoreUnimportantViews = true;
    private int     serverInstallTimeout   = 30;
    private int     serverLaunchTimeout    = 30;
    private int     systemPort             = 8200;
}