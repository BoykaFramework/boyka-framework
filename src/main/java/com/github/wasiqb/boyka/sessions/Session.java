package com.github.wasiqb.boyka.sessions;

import static java.time.Duration.ofSeconds;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class Session<D extends WebDriver> {
    private final D             driver;
    private final WebDriverWait wait;

    // FIXME: Use settings
    public Session (final D driver) {
        this.driver = driver;
        this.wait = new WebDriverWait (driver, ofSeconds (10));
    }
}
