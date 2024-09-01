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

package io.github.boykaframework.config.ui;

import java.util.List;

import io.github.boykaframework.config.BoykaConfig;
import lombok.Data;

/**
 * Appium server log settings.
 *
 * @author Wasiq Bhamla
 * @since 07-Sept-2022
 */
@Data
public class LogSetting implements BoykaConfig {
    private boolean      enable = true;
    private List<String> excludeLogs;
    private String       path   = "./logs";
}
