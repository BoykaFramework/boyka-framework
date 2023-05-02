package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.actions.drivers.NavigateActions.navigate;
import static com.github.wasiqb.boyka.enums.Message.CAPABILITIES_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Message.EMPTY_BROWSER_NOT_ALLOWED;
import static com.github.wasiqb.boyka.enums.Message.HOSTNAME_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Message.INVALID_BROWSER;
import static com.github.wasiqb.boyka.enums.Message.INVALID_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Message.NULL_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Message.PASSWORD_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.Message.PROTOCOL_REQUIRED_FOR_HOST;
import static com.github.wasiqb.boyka.enums.Message.USER_NAME_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.TargetProviders.LOCAL;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.setDriver;
import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static com.github.wasiqb.boyka.utils.Validator.requireNonNull;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNullElse;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.net.MalformedURLException;
import java.net.URL;

import com.github.wasiqb.boyka.config.ui.web.WebSetting;
import com.github.wasiqb.boyka.enums.Message;
import com.github.wasiqb.boyka.enums.TargetProviders;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

class WebDriverManager implements IDriverManager {
    private static final String HEADLESS = "--headless=new";
    private static final Logger LOGGER   = getLogger ();

    @Override
    public void setupDriver () {
        LOGGER.traceEntry ();
        final var webSetting = getSession ().getWebSetting ();
        try {
            switch (requireNonNull (webSetting.getBrowser (), EMPTY_BROWSER_NOT_ALLOWED)) {
                case CHROME:
                    setDriver (setupChromeDriver (webSetting));
                    break;
                case NONE:
                    throwError (INVALID_BROWSER);
                    break;
                case REMOTE:
                    setDriver (setupRemoteDriver (webSetting));
                    break;
                case SAFARI:
                    setDriver (setupSafariDriver ());
                    break;
                case EDGE:
                    setDriver (setupEdgeDriver (webSetting));
                    break;
                case FIREFOX:
                default:
                    setDriver (setupFirefoxDriver (webSetting));
                    break;
            }
        } catch (final SessionNotCreatedException e) {
            handleAndThrow (Message.SESSION_NOT_STARTED, e);
        }
        setDriverSize (webSetting);
        navigateToBaseUrl (webSetting.getBaseUrl ());
        LOGGER.traceExit ();
    }

    private Capabilities getCapabilities (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var capabilities = requireNonNull (webSetting.getCapabilities (), CAPABILITIES_REQUIRED_FOR_REMOTE);
        final var remoteCapabilities = new DesiredCapabilities ();
        capabilities.forEach (remoteCapabilities::setCapability);
        return LOGGER.traceExit (remoteCapabilities);
    }

    private String getHostName (final WebSetting webSetting, final TargetProviders target) {
        LOGGER.traceEntry ();
        final var host = requireNonNullElse (webSetting.getHost (),
            requireNonNull (target, HOSTNAME_REQUIRED_FOR_REMOTE).getHost ());
        if (requireNonNullElse (webSetting.getTarget (), LOCAL) != LOCAL) {
            final var hostNamePattern = "{0}:{1}@{2}";
            return format (hostNamePattern, requireNonNull (webSetting.getUserName (), USER_NAME_REQUIRED_FOR_CLOUD),
                requireNonNull (webSetting.getPassword (), PASSWORD_REQUIRED_FOR_CLOUD), host);
        }
        return LOGGER.traceExit (host);
    }

    private URL getRemoteUrl (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var URL_PATTERN = "{0}://{1}/wd/hub";
        final var target = webSetting.getTarget ();
        final var hostName = new StringBuilder (getHostName (webSetting, target));
        if (webSetting.getPort () != 0) {
            hostName.append (":")
                .append (webSetting.getPort ());
        }
        final var url = format (URL_PATTERN,
            requireNonNull (requireNonNullElse (webSetting.getProtocol (), target.getProtocol ()),
                PROTOCOL_REQUIRED_FOR_HOST, hostName).name ()
                .toLowerCase (), hostName);
        try {
            return LOGGER.traceExit (new URL (url));
        } catch (final MalformedURLException e) {
            handleAndThrow (INVALID_REMOTE_URL, e);
        }
        return null;
    }

    private void navigateToBaseUrl (final String baseUrl) {
        if (isNotEmpty (baseUrl)) {
            navigate ().to (baseUrl);
        }
    }

    private void setDriverSize (final WebSetting webSetting) {
        final var window = getSession ().getDriver ()
            .manage ()
            .window ();
        switch (webSetting.getResize ()) {
            case CUSTOM:
                window.setSize (webSetting.getCustomSize ());
                break;
            case FULL_SCREEN:
                window.fullscreen ();
                break;
            case MAXIMIZED:
                window.maximize ();
                break;
            case MINIMIZED:
                window.minimize ();
                break;
            case NORMAL:
            default:
                break;
        }
    }

    private WebDriver setupChromeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        chromedriver ().setup ();
        final var options = new ChromeOptions ();
        options.addArguments ("enable-automation");
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        ofNullable (webSetting.getBrowserOptions ()).ifPresent (l -> l.forEach (options::addArguments));
        if (webSetting.isHeadless ()) {
            options.addArguments (HEADLESS);
        }
        return LOGGER.traceExit (new ChromeDriver (options));
    }

    private WebDriver setupEdgeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        edgedriver ().setup ();
        final var options = new EdgeOptions ();
        options.addArguments ("enable-automation");
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        ofNullable (webSetting.getBrowserOptions ()).ifPresent (l -> l.forEach (options::addArguments));
        if (webSetting.isHeadless ()) {
            options.addArguments (HEADLESS);
        }
        return LOGGER.traceExit (new EdgeDriver (options));
    }

    private WebDriver setupFirefoxDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        firefoxdriver ().setup ();
        final var options = new FirefoxOptions ();
        ofNullable (webSetting.getBrowserOptions ()).ifPresent (l -> l.forEach (options::addArguments));
        if (webSetting.isHeadless ()) {
            options.addArguments (HEADLESS);
        }
        return LOGGER.traceExit (new FirefoxDriver (options));
    }

    private WebDriver setupRemoteDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();

        final var driver = new RemoteWebDriver (requireNonNull (getRemoteUrl (webSetting), NULL_REMOTE_URL),
            getCapabilities (webSetting));
        driver.setFileDetector (new LocalFileDetector ());
        return LOGGER.traceExit (driver);
    }

    private WebDriver setupSafariDriver () {
        LOGGER.traceEntry ();
        safaridriver ().setup ();
        return LOGGER.traceExit (new SafariDriver ());
    }
}
