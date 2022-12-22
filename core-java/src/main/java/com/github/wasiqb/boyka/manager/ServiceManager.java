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

import static com.github.wasiqb.boyka.enums.CloudProviders.NONE;
import static com.github.wasiqb.boyka.enums.Message.ERROR_SERVER_NOT_RUNNING;
import static com.github.wasiqb.boyka.enums.Message.ERROR_STARTING_SERVER;
import static com.github.wasiqb.boyka.enums.Message.ERROR_STOPPING_SERVER;
import static com.github.wasiqb.boyka.enums.Message.INVALID_REMOTE_URL;
import static com.github.wasiqb.boyka.enums.Message.SERVER_ALREADY_RUNNING;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.BOOTSTRAP_PORT_NUMBER;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.REBOOT;
import static io.appium.java_client.service.local.flags.AndroidServerFlag.SUPPRESS_ADB_KILL_SERVER;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.ALLOW_INSECURE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static java.lang.String.join;
import static java.lang.Thread.currentThread;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;

import com.github.wasiqb.boyka.config.ui.mobile.server.ServerSetting;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.apache.logging.log4j.Logger;

/**
 * Handle Appium Server sessions.
 *
 * @author Wasiq Bhamla
 * @since 06-Sept-2022
 */
public class ServiceManager {
    private static final Logger LOG = getLogger ();

    private       AppiumServiceBuilder     builder;
    private       AppiumDriverLocalService service;
    private final ServerSetting            setting;

    ServiceManager (final ServerSetting setting) {
        this.setting = setting;
        if (!isCloud ()) {
            this.builder = new AppiumServiceBuilder ();
            buildService ();
        }
    }

    /**
     * Determines if the server is running.
     *
     * @return true, if server is running, else false.
     */
    public boolean isRunning () {
        if (isCloud ()) {
            return true;
        }
        if (!this.setting.isExternal ()) {
            LOG.trace ("Checking if Appium Service is running...");
            return this.service.isRunning ();
        }
        final var address = new InetSocketAddress (this.setting.getHost (), this.setting.getPort ());
        try (final Socket socket = new Socket ()) {
            socket.connect (address, 2000);
        } catch (final IOException e) {
            handleAndThrow (ERROR_SERVER_NOT_RUNNING, e, e.getMessage ());
        }
        return true;
    }

    URL getServiceUrl () {
        LOG.trace ("Fetching Appium Service URL...");
        if (!isCloud ()) {
            return this.service.getUrl ();
        }
        try {
            return new URL (getUrl ());
        } catch (final MalformedURLException e) {
            handleAndThrow (INVALID_REMOTE_URL, e);
        }
        return null;
    }

    void startServer () {
        if (this.service != null && !isCloud () && !this.setting.isExternal ()) {
            final var running = this.service.isRunning ();
            if (running) {
                throwError (SERVER_ALREADY_RUNNING);
            }
            var failed = false;
            try {
                this.service.start ();
            } catch (final AppiumServerHasNotBeenStartedLocallyException e) {
                failed = true;
                handleAndThrow (ERROR_STARTING_SERVER, e, e.getMessage ());
            } finally {
                if (failed) {
                    stopServer ();
                }
            }
        }
    }

    void stopServer () {
        if (!isCloud () && !this.setting.isExternal () && this.service != null && this.service.isRunning ()) {
            try {
                this.service.stop ();
                this.service = null;
            } catch (final Exception e) {
                handleAndThrow (ERROR_STOPPING_SERVER, e, e.getMessage ());
            }
        }
    }

    private void buildService () {
        LOG.trace ("Building Appium Service started...");
        this.builder.withIPAddress (this.setting.getHost ())
            .withTimeout (ofSeconds (this.setting.getTimeout ()));
        setPort ();
        setAppiumJS ();
        setNodeExe ();
        setArguments ();
        this.service = AppiumDriverLocalService.buildService (this.builder);
        LOG.trace ("Building Appium Service done...");
    }

    private String getUrl () {
        final var sb = new StringBuilder (this.setting.getProtocol ()
            .name ()).append ("://");
        if (isCloud ()) {
            sb.append (this.setting.getUserName ())
                .append (":")
                .append (this.setting.getPassword ())
                .append ("@");
        }
        sb.append (this.setting.getHost ());
        if (this.setting.getPort () > 0) {
            sb.append (":")
                .append (this.setting.getPort ());
        }
        if (isNotEmpty (this.setting.getBasePath ())) {
            sb.append (this.setting.getBasePath ());
        }
        return sb.toString ();
    }

    private boolean isCloud () {
        return this.setting.getCloud () != NONE && isNotEmpty (this.setting.getUserName ()) && isNotEmpty (
            this.setting.getPassword ());
    }

    private void setAndroidArguments () {
        final var android = this.setting.getAndroid ();
        if (android != null) {
            setArgument (BOOTSTRAP_PORT_NUMBER, android.getBootstrapPort ());
            setArgument (REBOOT, android.isReboot ());
            setArgument (SUPPRESS_ADB_KILL_SERVER, android.isSuppressAdbKill ());
        }
    }

    private void setAppiumJS () {
        if (this.setting.getAppiumPath () != null) {
            final var appJs = new File (this.setting.getAppiumPath ());
            this.builder.withAppiumJS (appJs);
        }
    }

    private void setArgument (final ServerArgument flag, final boolean value) {
        if (value) {
            this.builder.withArgument (flag);
        }
    }

    private void setArgument (final ServerArgument flag, final int value) {
        if (value > 0) {
            this.builder.withArgument (flag, Integer.toString (value));
        }
    }

    private void setArgument (final ServerArgument flag, final String value) {
        if (isNoneEmpty (value)) {
            this.builder.withArgument (flag, value);
        }
    }

    private void setArguments () {
        setAndroidArguments ();
        setLogArguments ();
        setCommonArguments ();
    }

    private void setCommonArguments () {
        setArgument (BASEPATH, this.setting.getBasePath ());
        setArgument (SESSION_OVERRIDE, this.setting.isSessionOverride ());
        if (this.setting.getAllowInsecure () != null && !this.setting.getAllowInsecure ()
            .isEmpty ()) {
            setArgument (ALLOW_INSECURE, join (",", this.setting.getAllowInsecure ()));
        }
    }

    private void setLogArguments () {
        final var logs = getSession ().getSetting ()
            .getUi ()
            .getLogging ();
        if (!logs.isEnable ()) {
            return;
        }
        if (logs.getLevel () != null) {
            setArgument (LOG_LEVEL, logs.getLevel ()
                .getLevel ());
        }
        setLogFile ();
    }

    private void setLogFile () {
        final var logFolderPath = getSession ().getSetting ()
            .getUi ()
            .getLogging ()
            .getPath ();
        if (logFolderPath != null) {
            final var filePath = new File (format ("{0}/server-{1}.log", logFolderPath, currentThread ().getId ()));
            this.builder.withLogFile (filePath);
        }
    }

    private void setNodeExe () {
        if (this.setting.getNodePath () != null) {
            final var node = new File (this.setting.getNodePath ());
            this.builder.usingDriverExecutable (node);
        }
    }

    private void setPort () {
        if (this.setting.getPort () > 0) {
            this.builder.usingPort (this.setting.getPort ());
        } else {
            this.builder.usingAnyFreePort ();
        }
    }
}
