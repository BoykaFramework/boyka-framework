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

package com.github.wasiqb.boyka.enums;

import lombok.Getter;

/**
 * Different validation messages
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
@Getter
public enum Messages {
    /**
     * Driver does not support API application type
     */
    APP_TYPE_NOT_SUPPORT_DRIVERS ("API application type does not support drivers..."),
    /**
     * Remote execution requires capabilities
     */
    CAPABILITIES_REQUIRED_FOR_REMOTE ("Capabilities required for remote execution..."),
    /**
     * Content type must be set before setting request body
     */
    CONTENT_TYPE_MOT_SET ("Content type must be set before setting request body..."),
    /**
     * Error executing request
     */
    ERROR_EXECUTING_REQUEST ("Error occurred while executing request..."),
    /**
     * Error reading file
     */
    ERROR_READING_FILE ("Error occurred reading file [{0}]..."),
    /**
     * Error writing file
     */
    ERROR_WRITING_FILE ("Error occurred writing file [{0}]..."),
    /**
     * Host name is required for Remote execution
     */
    HOSTNAME_REQUIRED_FOR_REMOTE ("Host name is required for remote driver execution..."),
    /**
     * Invalid browser selected
     */
    INVALID_BROWSER ("NONE Browser type is not allowed for Web platform..."),
    /**
     * Invalid platform for operation
     */
    INVALID_PLATFORM_FOR_OPERATION ("Platform [{0}] is not supported for this setting..."),
    /**
     * No API setting found
     */
    NO_API_SETTINGS_FOUND ("No settings found for API for key {0}..."),
    /**
     * No JSON file found
     */
    NO_JSON_FILE_FOUND ("JSON file [{0}] not found..."),
    /**
     * Password required for cloud execution
     */
    PASSWORD_REQUIRED_FOR_CLOUD ("Password is required for cloud execution..."),
    /**
     * Protocol is required for host name
     */
    PROTOCOL_REQUIRED_FOR_HOST ("Protocol is required for host [{0}]..."),
    /**
     * User name required for cloud execution
     */
    USER_NAME_REQUIRED_FOR_CLOUD ("User name is required for cloud execution...");

    private final String message;

    Messages (final String message) {
        this.message = message;
    }
}