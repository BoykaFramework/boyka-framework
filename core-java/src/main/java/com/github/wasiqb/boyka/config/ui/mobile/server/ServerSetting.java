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

package com.github.wasiqb.boyka.config.ui.mobile.server;

import static com.github.wasiqb.boyka.enums.Protocol.HTTP;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;

import java.util.List;
import java.util.Map;

import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.enums.Protocol;
import lombok.Data;

/**
 * Appium Server settings.
 *
 * @author Wasiq Bhamla
 * @since 06-Sept-2022
 */
@Data
public class ServerSetting {
    private boolean             allowCors;
    private List<String>        allowInsecure;
    private AndroidSetting      android;
    private String              appiumPath;
    private String              basePath;
    private String              callbackIp;
    private int                 callbackPort;
    private CloudProviders      cloud    = CloudProviders.NONE;
    private Map<String, String> environments;
    private boolean             external;
    private String              host;
    private LogSetting          logs     = new LogSetting ();
    private String              nodeConfig;
    private String              nodePath;
    private String              password;
    private int                 port     = 4723;
    private Protocol            protocol = HTTP;
    private boolean             relaxedSecurity;
    private boolean             sessionOverride;
    private boolean             strictCapabilities;
    private int                 timeout  = 30;
    private String              userName;

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