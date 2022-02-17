package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;

import com.github.wasiqb.boyka.enums.Browsers;
import com.github.wasiqb.boyka.enums.Platforms;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverManager {
    private final Browsers  browsers;
    private final Platforms platforms;

    public DriverManager (final Platforms platforms, final Browsers browsers) {
        this.platforms = platforms;
        this.browsers = browsers;
    }

    public DriverManager (final Platforms platforms) {
        this (platforms, Browsers.NONE);
    }

    public void setupDriver () {
        if (this.platforms == Platforms.WEB) {
            switch (this.browsers) {
                case CHROME:
                    setDriver (setupChromeDriver ());
                    break;
                case NONE:
                    break;
                case REMOTE:
                    break;
                case SAFARI:
                    setDriver (setupSafariDriver ());
                    break;
                case EDGE:
                    setDriver (setupEdgeDriver ());
                    break;
                case FIREFOX:
                    setDriver (setupFirefoxDriver ());
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

    private WebDriver setupSafariDriver () {
        safaridriver ().setup ();
        return new SafariDriver ();
    }
}