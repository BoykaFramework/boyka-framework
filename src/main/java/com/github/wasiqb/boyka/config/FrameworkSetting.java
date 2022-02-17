package com.github.wasiqb.boyka.config;

import static com.github.wasiqb.boyka.constants.Messages.NO_API_SETTINGS_FOUND;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.util.Map;

import com.github.wasiqb.boyka.config.api.ApiSetting;
import lombok.Data;

@Data
public class FrameworkSetting {
    private Map<String, ApiSetting> api;
    private UISetting               ui;

    public ApiSetting getApiSetting (final String key) {
        return requireNonNull (this.api.get (key), format (NO_API_SETTINGS_FOUND.getMessage (), key));
    }
}