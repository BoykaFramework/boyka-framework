package com.github.wasiqb.boyka.config.android;

import java.util.Map;

import lombok.Data;

@Data
public class MobileSetting {
    private Map<String, Object> capabilities;
    private String              password;
    private int                 port;
    private String              remoteUrl;
    private Map<String, Object> settings;
    private String              userName;
}
