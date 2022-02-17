package com.github.wasiqb.boyka.config.api;

import lombok.Data;

@Data
public class LoggingSetting {
    private boolean request  = true;
    private boolean response = true;
}