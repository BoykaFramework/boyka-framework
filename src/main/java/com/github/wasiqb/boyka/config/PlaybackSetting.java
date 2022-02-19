package com.github.wasiqb.boyka.config;

import lombok.Data;

@Data
public class PlaybackSetting {
    private int explicitWait;
    private int implicitWait;
    private int pageLoadTimeout;
    private int scriptTimeout;
}
