package com.github.wasiqb.boyka.config.api;

import lombok.Data;

@Data
public class ApiSetting {
    private String         basePath;
    private String         baseUri;
    private LoggingSetting logging = new LoggingSetting ();
    private int            port;
}