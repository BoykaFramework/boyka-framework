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

package io.github.boykaframework.config.api;

import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.Validator.requireNonNullElse;

import lombok.Data;

/**
 * API setting class.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
@Data
public class ApiSetting {
    private static final CommonApiSetting COMMON_SETTING = getSession ().getCommonApiSetting ();

    private String         basePath;
    private String         baseUri;
    private LogSetting     logging;
    private int            port;
    private String         schemaPath;
    private TimeoutSetting timeout;
    private boolean        validateSsl;
    private boolean        verifyHostName;

    public String getBasePath () {
        return requireNonNullElse (this.basePath, COMMON_SETTING.getBasePath ());
    }

    public LogSetting getLogging () {
        return requireNonNullElse (this.logging, COMMON_SETTING.getLogging ());
    }

    public String getSchemaPath () {
        return requireNonNullElse (this.schemaPath, COMMON_SETTING.getSchemaPath ());
    }

    public TimeoutSetting getTimeout () {
        return requireNonNullElse (this.timeout, COMMON_SETTING.getTimeout ());
    }

    public boolean isValidateSsl () {
        return requireNonNullElse (this.validateSsl, COMMON_SETTING.isValidateSsl ());
    }

    public boolean isVerifyHostName () {
        return requireNonNullElse (this.verifyHostName, COMMON_SETTING.isVerifyHostName ());
    }
}
