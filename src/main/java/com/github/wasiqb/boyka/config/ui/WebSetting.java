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

import static com.github.wasiqb.boyka.enums.Protocol.HTTP;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;

import java.util.Map;

import com.github.wasiqb.boyka.enums.Browser;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.enums.Protocol;
import lombok.Data;

@Data
public class WebSetting {
    private Browser             browser  = Browser.NONE;
    private Map<String, Object> capabilities;
    private CloudProviders      cloud    = CloudProviders.NONE;
    private boolean             headless = true;
    private String              host     = "localhost";
    private String              password;
    private int                 port;
    private Protocol            protocol = HTTP;
    private String              userName;

    public String getPassword () {
        return interpolate (this.password);
    }

    public String getUserName () {
        return interpolate (this.userName);
    }
}
