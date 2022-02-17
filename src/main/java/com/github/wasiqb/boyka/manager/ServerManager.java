package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;

import com.github.wasiqb.boyka.config.android.MobileSetting;
import com.github.wasiqb.boyka.enums.Platforms;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class ServerManager {
    private       AppiumServiceBuilder builder;
    private final MobileSetting        setting;

    public ServerManager (final Platforms platforms, final String appiumKey) {
        this.setting = loadSetting ().getUi ()
            .getMobileSetting (platforms, appiumKey);
    }
}
