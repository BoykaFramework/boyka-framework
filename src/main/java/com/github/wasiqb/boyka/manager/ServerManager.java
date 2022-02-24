package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;

import com.github.wasiqb.boyka.config.ui.MobileSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ServerManager {
    private       AppiumServiceBuilder builder;
    private final MobileSetting        setting;

    public ServerManager (final ApplicationType applicationType, final String appiumKey) {
        this.setting = loadSetting ().getUi ()
            .getMobileSetting (applicationType, appiumKey);
    }
}
