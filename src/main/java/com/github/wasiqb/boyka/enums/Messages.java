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

@Getter
public enum Messages {
    INVALID_PLATFORM_FOR_OPERATION ("Platform [{0}] is not supported for this setting..."),
    NO_API_SETTINGS_FOUND ("No settings found for API for key {0}..."),
    NO_JSON_FILE_FOUND ("JSON file [{0}] not found..."),
    ERROR_READING_FILE ("Error occurred reading file [{0}]..."),
    ERROR_WRITING_FILE ("Error occurred writing file [{0}]..."),
    CAPABILITIES_REQUIRED_FOR_REMOTE ("Capabilities required for remote execution..."),
    PROTOCOL_REQUIRED_FOR_HOST ("Protocol is required for host [{0}]..."),
    USER_NAME_REQUIRED_FOR_CLOUD ("User name is required for cloud execution..."),
    PASSWORD_REQUIRED_FOR_CLOUD ("Password is required for cloud execution..."),
    HOSTNAME_REQUIRED_FOR_REMOTE ("Host name is required for remote driver execution..."),
    APP_TYPE_NOT_SUPPORT_DRIVERS ("API application type does not support drivers..."),
    INVALID_BROWSER ("NONE Browser type is not allowed for Web platform...");

    private final String message;

    Messages (final String message) {
        this.message = message;
    }
}