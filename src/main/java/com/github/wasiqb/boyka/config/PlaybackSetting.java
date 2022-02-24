package com.github.wasiqb.boyka.config;

import lombok.Data;

@Data
public class PlaybackSetting {
    private int explicitWait    = 10;
    private int implicitWait    = 10;
    private int pageLoadTimeout = 30;
    private int scriptTimeout   = 10;
}
