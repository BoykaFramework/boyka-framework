/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.AutomationType.UI_AUTOMATOR;
import static com.github.wasiqb.boyka.enums.CloudProviders.NONE;
import static com.github.wasiqb.boyka.enums.DeviceType.CLOUD;
import static com.github.wasiqb.boyka.enums.DeviceType.VIRTUAL;
import static com.github.wasiqb.boyka.enums.Message.APP_TYPE_NOT_SUPPORTED;
import static com.github.wasiqb.boyka.enums.Message.CAPABILITIES_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Message.EMPTY_BROWSER_NOT_ALLOWED;
import static com.github.wasiqb.boyka.enums.Message.ERROR_QUITTING_DRIVER;
import static com.github.wasiqb.boyka.enums.Message.HOSTNAME_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Message.INVALID_BROWSER;
import static com.github.wasiqb.boyka.enums.Message.INVALID_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Message.NULL_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Message.PASSWORD_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.Message.PROTOCOL_REQUIRED_FOR_HOST;
import static com.github.wasiqb.boyka.enums.Message.USER_NAME_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;
import static com.github.wasiqb.boyka.utils.Validator.requireNonNull;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.HashMap;

import com.github.wasiqb.boyka.config.ui.TimeoutSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.ApplicationSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.DeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.device.VirtualDeviceSetting;
import com.github.wasiqb.boyka.config.ui.mobile.server.ServerSetting;
import com.github.wasiqb.boyka.config.ui.web.WebSetting;
import com.github.wasiqb.boyka.enums.DeviceType;
import com.github.wasiqb.boyka.enums.PlatformType;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class DriverManager {
    private static final Logger LOGGER = getLogger ();

    /**
     * Closes the driver instance and clears driver session.
     */
    public static void closeDriver () {
        LOGGER.traceEntry ();
        LOGGER.info ("Closing driver instance");
        try {
            getSession ().getDriver ()
                .quit ();
            if (getSession ().getPlatformType () != WEB) {
                getSession ().getServiceManager ()
                    .stopServer ();
            }
        } catch (final WebDriverException e) {
            handleAndThrow (ERROR_QUITTING_DRIVER, e);
        } finally {
            clearSession ();
        }
        LOGGER.traceExit ();
    }

    /**
     * Creates driver instance.
     *
     * @param platformType the application type
     * @param driverKey the driver config key
     *
     * @return the driver instance
     */
    public static DriverManager createDriver (final PlatformType platformType, final String driverKey) {
        LOGGER.traceEntry ();
        LOGGER.info ("Creating Driver Instance for {} and driver key: {}", platformType, driverKey);
        final var instance = new DriverManager (platformType, driverKey);
        instance.setupDriver ();
        return LOGGER.traceExit (instance);
    }

    private final PlatformType platformType;

    private DriverManager (final PlatformType platformType, final String driverKey) {
        LOGGER.traceEntry ();
        this.platformType = platformType;
        getSession ().setConfigKey (driverKey);
        LOGGER.traceExit ();
    }

    private Capabilities getCapabilities (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var capabilities = requireNonNull (webSetting.getCapabilities (), CAPABILITIES_REQUIRED_FOR_REMOTE);
        final var remoteCapabilities = new DesiredCapabilities ();
        capabilities.forEach (remoteCapabilities::setCapability);
        return LOGGER.traceExit (remoteCapabilities);
    }

    private String getHostName (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        if (requireNonNullElse (webSetting.getCloud (), NONE) != NONE) {
            final var hostNamePattern = "{0}:{1}@{2}";
            return format (hostNamePattern, requireNonNull (webSetting.getUserName (), USER_NAME_REQUIRED_FOR_CLOUD),
                requireNonNull (webSetting.getPassword (), PASSWORD_REQUIRED_FOR_CLOUD),
                requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE));
        }
        return LOGGER.traceExit (requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE));
    }

    private URL getRemoteUrl (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        final var URL_PATTERN = "{0}://{1}/wd/hub";
        final var hostName = new StringBuilder (getHostName (webSetting));
        if (webSetting.getPort () != 0) {
            hostName.append (":")
                .append (webSetting.getPort ());
        }
        final var url = format (URL_PATTERN,
            requireNonNull (webSetting.getProtocol (), PROTOCOL_REQUIRED_FOR_HOST, hostName).name ()
                .toLowerCase (), hostName);
        try {
            return LOGGER.traceExit (new URL (url));
        } catch (final MalformedURLException e) {
            handleAndThrow (INVALID_REMOTE_URL, e);
        }
        return null;
    }

    private void setAndroidApplicationOptions (final UiAutomator2Options options,
        final ApplicationSetting application) {
        if (isNotEmpty (application.getPath ())) {
            if (!application.isExternal ()) {
                options.setApp (Path.of (getProperty ("user.dir"), "/src/test/resources", application.getPath ())
                    .toString ());
            } else {
                options.setApp (interpolate (application.getPath ()));
            }
        }
        options.setAppWaitActivity (application.getWaitActivity ());
        options.setAppWaitDuration (ofSeconds (application.getWaitTimeout ()));
        options.setAndroidInstallTimeout (ofSeconds (application.getInstallTimeout ()));
    }

    private void setAvdOptions (final UiAutomator2Options options, final DeviceType type,
        final VirtualDeviceSetting avd) {
        if (type == VIRTUAL && avd != null) {
            options.setAvd (avd.getName ());
            options.setIsHeadless (avd.isHeadless ());
            options.setAvdLaunchTimeout (ofSeconds (avd.getLaunchTimeout ()));
            options.setAvdReadyTimeout (ofSeconds (avd.getReadyTimeout ()));
        }
    }

    private void setCommonUiAutomatorOptions (final UiAutomator2Options options, final DeviceSetting deviceSetting) {
        setAndroidApplicationOptions (options, deviceSetting.getApplication ());
        options.setAutomationName (deviceSetting.getAutomation ()
            .getName ());
        options.setPlatformName (deviceSetting.getOs ()
            .name ());
        options.setPlatformVersion (deviceSetting.getVersion ());
        options.setDeviceName (deviceSetting.getName ());
    }

    private void setDriverSize (final WebSetting webSetting) {
        if (this.platformType == WEB) {
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
    }

    private void setDriverWaits (final TimeoutSetting timeoutSetting) {
        LOGGER.traceEntry ();
        final var driver = getSession ().getDriver ();
        final var timeouts = driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (timeoutSetting.getImplicitWait ()));
        if (this.platformType == WEB) {
            timeouts.pageLoadTimeout (ofSeconds (timeoutSetting.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (timeoutSetting.getScriptTimeout ()));
        }
        getSession ().setWait (new WebDriverWait (driver, ofSeconds (timeoutSetting.getExplicitWait ())));
        LOGGER.traceExit ();
    }

    private void setLocalUiAutomatorOptions (final UiAutomator2Options options, final DeviceSetting deviceSetting) {
        setAvdOptions (options, deviceSetting.getType (), deviceSetting.getAvd ());
        options.setClearSystemFiles (deviceSetting.isClearFiles ());
        options.setClearDeviceLogsOnStart (deviceSetting.isClearLogs ());
        options.setNoReset (deviceSetting.isNoReset ());
        options.setFullReset (deviceSetting.isFullReset ());
        options.setUiautomator2ServerLaunchTimeout (ofSeconds (deviceSetting.getServerLaunchTimeout ()));
        options.setUiautomator2ServerInstallTimeout (ofSeconds (deviceSetting.getServerInstallTimeout ()));
    }

    private void setupAndroidDriver () {
        final var androidSetting = getSession ().getMobileSetting ();
        final var serverSetting = androidSetting.getServer ();
        final var deviceSetting = androidSetting.getDevice ();
        final var automation = deviceSetting.getAutomation ();
        if (automation == UI_AUTOMATOR) {
            setupUiAutomatorDriver (serverSetting, deviceSetting);
        }
        setupAndroidSettings (deviceSetting);
    }

    private void setupAndroidSettings (final DeviceSetting setting) {
        final AndroidDriver driver = (AndroidDriver) getSession ().getDriver ();
        driver.setSetting (Setting.IGNORE_UNIMPORTANT_VIEWS, setting.isIgnoreUnimportantViews ());
    }

    private void setupAppiumServer () {
        final var serviceManager = new ServiceManager (getSession ().getMobileSetting ()
            .getServer ());
        getSession ().setServiceManager (serviceManager);
        getSession ().getServiceManager ()
            .startServer ();
    }

    private WebDriver setupChromeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        chromedriver ().setup ();
        final var options = new ChromeOptions ();
        options.addArguments ("enable-automation");
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new ChromeDriver (options));
    }

    private void setupCloudAndroidDriver (final UiAutomator2Options options, final ServerSetting serverSetting,
        final DeviceSetting deviceSetting) {
        switch (serverSetting.getCloud ()) {
            case BROWSER_STACK:
                setupCloudAndroidDriverOptions (options, deviceSetting, "bstack");
                break;
            case LAMBDA_TEST:
            default:
                setupCloudAndroidDriverOptions (options, deviceSetting, "lt");
                break;
        }
    }

    private void setupCloudAndroidDriverOptions (final UiAutomator2Options options, final DeviceSetting deviceSetting,
        final String optionPrefix) {
        final var capabilities = deviceSetting.getCapabilities ();
        if (capabilities != null) {
            final var optionCapabilities = new HashMap<String, Object> ();
            capabilities.forEach ((k, v) -> {
                if (v instanceof String) {
                    optionCapabilities.put (k, interpolate (v.toString ()));
                } else {
                    optionCapabilities.put (k, v);
                }
            });
            options.setCapability (format ("{0}:options", optionPrefix), optionCapabilities);
        }
    }

    private void setupDriver () {
        LOGGER.traceEntry ();
        if (this.platformType == API) {
            throwError (APP_TYPE_NOT_SUPPORTED, this.platformType);
        }
        if (this.platformType == WEB) {
            setupWebDriver ();
        } else {
            setupAppiumServer ();
            setupMobileDriver ();
        }
        LOGGER.traceExit ();
    }

    private WebDriver setupEdgeDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        edgedriver ().setup ();
        final var options = new EdgeOptions ();
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new EdgeDriver (options));
    }

    private WebDriver setupFirefoxDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        firefoxdriver ().setup ();
        final var options = new FirefoxOptions ();
        options.setHeadless (webSetting.isHeadless ());
        return LOGGER.traceExit (new FirefoxDriver (options));
    }

    private void setupMobileDriver () {
        if (this.platformType == ANDROID) {
            setupAndroidDriver ();
        }
    }

    private WebDriver setupRemoteDriver (final WebSetting webSetting) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (new RemoteWebDriver (requireNonNull (getRemoteUrl (webSetting), NULL_REMOTE_URL),
            getCapabilities (webSetting)));
    }

    private WebDriver setupSafariDriver () {
        LOGGER.traceEntry ();
        safaridriver ().setup ();
        return LOGGER.traceExit (new SafariDriver ());
    }

    private void setupUiAutomatorDriver (final ServerSetting serverSetting, final DeviceSetting deviceSetting) {
        final UiAutomator2Options options = new UiAutomator2Options ();
        setCommonUiAutomatorOptions (options, deviceSetting);
        if (deviceSetting.getType () == CLOUD) {
            setupCloudAndroidDriver (options, serverSetting, deviceSetting);
        } else {
            setLocalUiAutomatorOptions (options, deviceSetting);
        }
        setDriver (this.platformType, new AndroidDriver (getSession ().getServiceManager ()
            .getServiceUrl (), options));
    }

    private void setupWebDriver () {
        LOGGER.traceEntry ();
        final var webSetting = getSession ().getWebSetting ();
        switch (requireNonNull (webSetting.getBrowser (), EMPTY_BROWSER_NOT_ALLOWED)) {
            case CHROME:
                setDriver (this.platformType, setupChromeDriver (webSetting));
                break;
            case NONE:
                throwError (INVALID_BROWSER);
                break;
            case REMOTE:
                setDriver (this.platformType, setupRemoteDriver (webSetting));
                break;
            case SAFARI:
                setDriver (this.platformType, setupSafariDriver ());
                break;
            case EDGE:
                setDriver (this.platformType, setupEdgeDriver (webSetting));
                break;
            case FIREFOX:
            default:
                setDriver (this.platformType, setupFirefoxDriver (webSetting));
                break;
        }
        setDriverSize (webSetting);
        setDriverWaits (getSession ().getSetting ()
            .getUi ()
            .getTimeout ());
        LOGGER.traceExit ();
    }
}
