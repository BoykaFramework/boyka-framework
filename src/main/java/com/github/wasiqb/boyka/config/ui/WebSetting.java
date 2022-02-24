package com.github.wasiqb.boyka.config.ui;

import com.github.wasiqb.boyka.enums.Browser;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.enums.Protocol;
import lombok.Data;

@Data
public class WebSetting {
    private Browser        browser;
    private CloudProviders cloud;
    private String         host;
    private String         password;
    private int            port;
    private Protocol       protocol;
    private String         userName;
}
