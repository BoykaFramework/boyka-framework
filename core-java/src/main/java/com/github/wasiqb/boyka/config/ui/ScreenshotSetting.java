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

package com.github.wasiqb.boyka.config.ui;

import lombok.Data;

/**
 * Screenshot setting.
 *
 * @author Wasiq Bhamla
 * @since 13-Jul-2022
 */
@Data
public class ScreenshotSetting {
    private boolean enabled   = false;
    private String  extension = "png";
    private String  path      = "./screenshots";
    private String  prefix    = "SCR";
}
