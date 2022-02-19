package com.github.wasiqb.boyka.sessions;

import static com.github.wasiqb.boyka.enums.Messages.APP_TYPE_NOT_SUPPORT_DRIVERS;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static java.time.Duration.ofSeconds;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.exception.FrameworkError;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Getter
public class DriverSession<D extends WebDriver> {
    private final ApplicationType  applicationType;
    private final D                driver;
    private final FrameworkSetting setting;
    private       WebDriverWait    wait;

    public DriverSession (final ApplicationType applicationType, final D driver) {
        this.applicationType = applicationType;
        if (this.applicationType == ApplicationType.API) {
            throw new FrameworkError (APP_TYPE_NOT_SUPPORT_DRIVERS.getMessage ());
        }
        this.driver = driver;
        this.setting = loadSetting ();
        setDriverWaits ();
    }

    private void setDriverWaits () {
        final var playback = this.setting.getUi ()
            .getPlayback ();
        final WebDriver.Timeouts timeouts = this.driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (playback.getImplicitWait ()));
        if (this.applicationType == ApplicationType.WEB) {
            timeouts.pageLoadTimeout (ofSeconds (playback.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (playback.getScriptTimeout ()));
        }
        this.wait = new WebDriverWait (getDriver (), ofSeconds (playback.getExplicitWait ()));
    }
}
