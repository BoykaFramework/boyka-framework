package com.github.wasiqb.boyka.sessions;

import static java.time.Duration.ofSeconds;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class DriverSession<D extends WebDriver> {
    private final ApplicationType  applicationType;
    private final D                driver;
    private final FrameworkSetting setting;
    private       WebDriverWait    wait;

    public DriverSession (final ApplicationType applicationType, final D driver, final FrameworkSetting setting) {
        this.applicationType = applicationType;
        this.setting = setting;
        this.driver = driver;
        setDriverWaits ();
    }

    private void setDriverWaits () {
        final var playback = this.setting.getUi ()
            .getPlayback ();
        final var timeouts = this.driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (playback.getImplicitWait ()));
        if (this.applicationType == ApplicationType.WEB) {
            timeouts.pageLoadTimeout (ofSeconds (playback.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (playback.getScriptTimeout ()));
        }
        this.wait = new WebDriverWait (getDriver (), ofSeconds (playback.getExplicitWait ()));
    }
}
