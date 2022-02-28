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

import static com.github.wasiqb.boyka.enums.Messages.CAPABILITIES_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Messages.HOSTNAME_REQUIRED_FOR_REMOTE;
import static com.github.wasiqb.boyka.enums.Messages.INVALID_BROWSER;
import static com.github.wasiqb.boyka.enums.Messages.PASSWORD_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.enums.Messages.PROTOCOL_REQUIRED_FOR_HOST;
import static com.github.wasiqb.boyka.enums.Messages.USER_NAME_REQUIRED_FOR_CLOUD;
import static com.github.wasiqb.boyka.sessions.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.edgedriver;
import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static io.github.bonigarcia.wdm.WebDriverManager.safaridriver;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

import java.net.URL;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.config.ui.WebSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.enums.CloudProviders;
import com.github.wasiqb.boyka.exception.FrameworkError;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class DriverManager {
    /**
     * Closes the driver instance and clears driver session.
     */
    public static void closeDriver () {
        getSession ().getDriver ()
            .quit ();
        clearSession ();
    }

    /**
     * Creates driver instance.
     *
     * @param applicationType the application type
     * @param driverKey the driver config key
     *
     * @return the driver instance
     */
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

    private Capabilities getCapabilities (final WebSetting webSetting) {
        final var capabilities = requireNonNull (webSetting.getCapabilities (),
            CAPABILITIES_REQUIRED_FOR_REMOTE.getMessage ());
        final var remoteCapabilities = new DesiredCapabilities ();
        capabilities.forEach (remoteCapabilities::setCapability);
        return remoteCapabilities;
    }

    private String getHostName (final WebSetting webSetting) {
        if (requireNonNullElse (webSetting.getCloud (), CloudProviders.NONE) != CloudProviders.NONE) {
            final var hostNamePattern = "{0}:{1}@{2}";
            return format (hostNamePattern,
                requireNonNull (webSetting.getUserName (), USER_NAME_REQUIRED_FOR_CLOUD.getMessage ()),
                requireNonNull (webSetting.getPassword (), PASSWORD_REQUIRED_FOR_CLOUD.getMessage ()),
                requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE.getMessage ()));
        }
        return requireNonNull (webSetting.getHost (), HOSTNAME_REQUIRED_FOR_REMOTE.getMessage ());
    }

    @SneakyThrows
    private URL getRemoteUrl (final WebSetting webSetting) {
        final var URL_PATTERN = "{0}://{1}/wd/hub";
        final var hostName = new StringBuilder (getHostName (webSetting));
        if (webSetting.getPort () != 0) {
            hostName.append (":")
                .append (webSetting.getPort ());
        }
        final var url = format (URL_PATTERN, requireNonNull (webSetting.getProtocol (),
            format (PROTOCOL_REQUIRED_FOR_HOST.getMessage (), hostName)).name ()
            .toLowerCase (), hostName);
        return new URL (url);
    }

    private WebDriver setupChromeDriver (final WebSetting webSetting) {
        chromedriver ().setup ();
        final ChromeOptions options = new ChromeOptions ();
        options.addArguments ("--no-sandbox");
        options.addArguments ("--disable-gpu");
        options.addArguments ("--disable-dev-shm-usage");
        if (webSetting.isHeadless ()) {
            options.addArguments ("--headless");
        }
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
        return new RemoteWebDriver (getRemoteUrl (webSetting), getCapabilities (webSetting));
    }

    private WebDriver setupSafariDriver () {
        safaridriver ().setup ();
        return new SafariDriver ();
    }

    private void setupWebDriver (final WebSetting webSetting) {
        switch (webSetting.getBrowser ()) {
            case CHROME:
                setDriver (this.applicationType, setupChromeDriver (webSetting), this.setting);
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
            default:
                setDriver (this.applicationType, setupFirefoxDriver (), this.setting);
                break;
        }
    }
}