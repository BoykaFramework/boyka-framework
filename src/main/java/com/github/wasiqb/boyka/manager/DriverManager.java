package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.Messages.INVALID_BROWSER;
import static com.github.wasiqb.boyka.sessions.ParallelSession.clear;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getDriver;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.config.ui.WebSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    public static DriverManager createDriver (final ApplicationType applicationType, final String driverKey) {
        final var instance = new DriverManager (applicationType, driverKey);
        instance.setupDriver ();
        return instance;
    }

    private final ApplicationType  applicationType;
    private final String           driverKey;
    private final FrameworkSetting setting;

    private DriverManager (final ApplicationType applicationType, final String driverKey) {
        this.applicationType = applicationType;
        this.driverKey = driverKey;
        this.setting = loadSetting ();
    }

    public void close () {
        getDriver ().quit ();
        clear ();
    }

    private WebDriver setupBrowserStack (final WebSetting webSetting) {
        return null;
    }

    private WebDriver setupChromeDriver () {
        chromedriver ().setup ();
        final ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        return new ChromeDriver (options);
    }

    private void setupDriver () {
        if (this.applicationType == ApplicationType.WEB) {
            final var webSetting = this.setting.getUi ()
                .getWebSetting (this.driverKey);
            setupWebDriver (webSetting);
        }
    }

    private WebDriver setupEdgeDriver () {
        edgedriver ().setup ();
        return new EdgeDriver ();
    }

    private WebDriver setupFirefoxDriver () {
        firefoxdriver ().setup ();
        return new FirefoxDriver ();
    }

    private WebDriver setupRemoteDriver (final WebSetting webSetting) {
        if (webSetting.getCloud () == CloudProviders.BROWSER_STACK) {
            return setupBrowserStack (webSetting);
        }
        return setupSeleniumGrid ();
    }

    private WebDriver setupSafariDriver () {
        safaridriver ().setup ();
        return new SafariDriver ();
    }

    private WebDriver setupSeleniumGrid () {
        return null;
    }

    private void setupWebDriver (final WebSetting webSetting) {
        switch (webSetting.getBrowser ()) {
            case CHROME:
                setDriver (this.applicationType, setupChromeDriver (), this.setting);
                break;
            case NONE:
                throw new FrameworkError (INVALID_BROWSER.getMessage ());
            case REMOTE:
                setDriver (this.applicationType, setupRemoteDriver (webSetting), this.setting);
                break;
            case SAFARI:
                setDriver (this.applicationType, setupSafariDriver (), this.setting);
                break;
            case EDGE:
                setDriver (this.applicationType, setupEdgeDriver (), this.setting);
                break;
            case FIREFOX:
                setDriver (this.applicationType, setupFirefoxDriver (), this.setting);
                break;
        }
    }
}