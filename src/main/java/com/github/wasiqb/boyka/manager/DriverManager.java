package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.Messages.INVALID_BROWSER;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;

import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.enums.Browsers;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private final ApplicationType applicationType;
    private final Browsers        browsers;

    public DriverManager (final ApplicationType applicationType, final Browsers browsers) {
        this.applicationType = applicationType;
        this.browsers = browsers;
    }

    public DriverManager (final ApplicationType platforms) {
        this (platforms, Browsers.NONE);
    }

    public void setupDriver () {
        if (this.applicationType == ApplicationType.WEB) {
            switch (this.browsers) {
                case CHROME:
                    setDriver (this.applicationType, setupChromeDriver ());
                    break;
                case NONE:
                    throw new FrameworkError (INVALID_BROWSER.getMessage ());
                case REMOTE:
                    setDriver (this.applicationType, setupRemoteDriver ());
                    break;
                case SAFARI:
                    setDriver (this.applicationType, setupSafariDriver ());
                    break;
                case EDGE:
                    setDriver (this.applicationType, setupEdgeDriver ());
                    break;
                case FIREFOX:
                    setDriver (this.applicationType, setupFirefoxDriver ());
                    break;
            }
        }
    }

    private WebDriver setupChromeDriver () {
        chromedriver ().setup ();
        final ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-dev-shm-usage");
        return new ChromeDriver (options);
    }

    private WebDriver setupEdgeDriver () {
        edgedriver ().setup ();
        return new EdgeDriver ();
    }

    private WebDriver setupFirefoxDriver () {
        firefoxdriver ().setup ();
        return new FirefoxDriver ();
    }

    private WebDriver setupRemoteDriver () {
        //        return new RemoteWebDriver (remoteUrl, caps);
        return null;
    }

    private WebDriver setupSafariDriver () {
        safaridriver ().setup ();
        return new SafariDriver ();
    }
}