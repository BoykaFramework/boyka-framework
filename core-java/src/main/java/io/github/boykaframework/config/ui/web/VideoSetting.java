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

package io.github.boykaframework.config.ui.web;

import lombok.Data;

/**
 * Video recording settings for Web and Mobile.
 *
 * @author Wasiq Bhamla
 * @since 18-Jan-2025
 */
@Data
public class VideoSetting {
    private boolean enabled = false;
    private String  path    = "./videos";
    private String  prefix  = "VID";
}
