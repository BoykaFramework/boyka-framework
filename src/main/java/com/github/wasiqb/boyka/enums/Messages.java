package com.github.wasiqb.boyka.enums;

import lombok.Getter;

@Getter
public enum Messages {
    INVALID_PLATFORM_FOR_OPERATION ("Platform [{0}] is not supported for this setting..."),
    NO_API_SETTINGS_FOUND ("No settings found for API for key {0}..."),
    NO_JSON_FILE_FOUND ("JSON file [{0}] not found..."),
    ERROR_READING_FILE ("Error occurred reading file [{0}]..."),
    ERROR_WRITING_FILE ("Error occurred writing file [{0}]..."),
    APP_TYPE_NOT_SUPPORT_DRIVERS ("API application type does not support drivers..."),
    INVALID_BROWSER ("NONE Browser type is not allowed for Web platform...");

    private final String message;

    Messages (final String message) {
        this.message = message;
    }
}