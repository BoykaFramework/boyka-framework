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

package io.github.boykaframework.config.ui.web;

import static io.github.boykaframework.enums.Protocol.HTTP;
import static io.github.boykaframework.utils.StringUtils.interpolate;

import java.util.List;
import java.util.Map;

import io.github.boykaframework.enums.Browser;
import io.github.boykaframework.enums.Protocol;
import io.github.boykaframework.enums.TargetProviders;
import io.github.boykaframework.enums.WindowResizeType;
import lombok.Data;
import org.openqa.selenium.Dimension;

/**
 * Web settings.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
@Data
public class WebSetting {
    private String              baseUrl;
    private Browser             browser    = Browser.NONE;
    private List<String>        browserOptions;
    private Map<String, Object> capabilities;
    private Dimension           customSize = new Dimension (1920, 1080);
    private boolean             headless   = true;
    private boolean             highlight  = false;
    private String              host;
    private String              password;
    private String              platform;
    private int                 port;
    private Protocol            protocol   = HTTP;
    private WindowResizeType    resize     = WindowResizeType.NORMAL;
    private TargetProviders     target;
    private String              userName;
    private String              version    = "stable";

    /**
     * Gets cloud password.
     *
     * @return the cloud password
     */
    public String getPassword () {
        return interpolate (this.password);
    }

    /**
     * Gets cloud user name.
     *
     * @return the cloud username.
     */
    public String getUserName () {
        return interpolate (this.userName);
    }
}
