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

package io.github.boykaframework.config.ui.mobile.server;

import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import io.github.boykaframework.enums.AutomationType;
import io.github.boykaframework.enums.Protocol;
import io.github.boykaframework.enums.TargetProviders;
import io.github.boykaframework.utils.StringUtils;
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
    private String              appiumPath;
    private String              basePath;
    private String              callbackAddress;
    private int                 callbackPort;
    private String              configPath;
    private List<String>        denyInsecure;
    private AutomationType      driver;
    private boolean             external;
    private boolean             externalConfig;
    private String              host;
    private int                 keepAliveTimeout;
    private LogSetting          logging;
    private String              nodePath;
    private Map<String, Object> otherArgs;
    private String              password;
    private List<String>        plugins;
    private int                 port;
    private Protocol            protocol;
    private boolean             relaxedSecurity;
    private boolean             sessionOverride;
    private boolean             strictCapabilities;
    private TargetProviders     target  = TargetProviders.LOCAL;
    private int                 timeout = 30;
    private String              userName;
    private String              webhook;

    /**
     * Gets the Appium Config path
     *
     * @return Path to Appium Config.
     */
    public String getConfigPath () {
        if (isExternalConfig () || isEmpty (this.configPath)) {
            return this.configPath;
        }
        return Path.of (System.getProperty ("user.dir"), this.configPath)
            .toString ();
    }

    /**
     * Gets cloud password.
     *
     * @return the cloud password
     */
    public String getPassword () {
        return StringUtils.interpolate (this.password);
    }

    /**
     * Gets cloud user name.
     *
     * @return the cloud username.
     */
    public String getUserName () {
        return StringUtils.interpolate (this.userName);
    }
}
