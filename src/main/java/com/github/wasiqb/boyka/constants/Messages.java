package com.github.wasiqb.boyka.constants;

import lombok.Getter;

@Getter
public enum Messages {
    INVALID_PLATFORM_FOR_OPERATION ("Platform [{0}] is not supported for this setting..."),
    NO_API_SETTINGS_FOUND ("No settings found for API for key {0}..."),
    NO_SETTING_FILE_FOUND ("No settings file found...");

    private final String message;

    Messages (final String message) {
        this.message = message;
    }
}