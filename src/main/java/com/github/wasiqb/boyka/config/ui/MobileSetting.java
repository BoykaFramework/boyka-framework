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

import java.util.Map;

import lombok.Data;

/**
 * Mobile settings.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
@Data
public class MobileSetting {
    private Map<String, Object> capabilities;
    private String              password;
    private int                 port;
    private String              remoteUrl;
    private Map<String, Object> settings;
    private String              userName;
}
