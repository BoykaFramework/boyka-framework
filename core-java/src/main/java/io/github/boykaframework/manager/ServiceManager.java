/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.manager;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.ALLOW_INSECURE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.BASEPATH;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.CALLBACK_ADDRESS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.CALLBACK_PORT;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.DEBUG_LOG_SPACING;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.DENY_INSECURE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOCAL_TIMEZONE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_TIMESTAMP;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.RELAXED_SECURITY;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.SESSION_OVERRIDE;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.STRICT_CAPS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.USE_DRIVERS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.USE_PLUGINS;
import static io.appium.java_client.service.local.flags.GeneralServerFlag.WEB_HOOK;
import static java.lang.String.join;
import static java.lang.Thread.currentThread;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNullElse;
import static org.apache.commons.lang3.StringUtils.isNoneEmpty;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import io.github.boykaframework.config.ui.mobile.server.ServerSetting;
import io.github.boykaframework.enums.Message;
import io.github.boykaframework.enums.TargetProviders;
import io.github.boykaframework.utils.ErrorHandler;
import org.apache.logging.log4j.Logger;

/**
 * Handle Appium Server sessions.
 *
 * @author Wasiq Bhamla
 * @since 06-Sept-2022
 */
class ServiceManager {
    private static final Logger LOG = getLogger ();

    private       AppiumServiceBuilder     builder;
    private       AppiumDriverLocalService service;
    private final ServerSetting            setting;

    ServiceManager (final ServerSetting setting) {
        this.setting = setting;
        if (!isCloud () && !setting.isExternal ()) {
            this.builder = new AppiumServiceBuilder ();
            buildService ();
        }
    }

    URL getServiceUrl () {
        LOG.trace ("Fetching Appium Service URL...");
        if (!isCloud () && !this.setting.isExternal ()) {
            return this.service.getUrl ();
        }
        try {
            return new URL (getUrl ());
        } catch (final MalformedURLException e) {
            ErrorHandler.handleAndThrow (Message.INVALID_REMOTE_URL, e);
        }
        return null;
    }

    void startServer () {
        if (!isCloud () && !this.setting.isExternal ()) {
            var failed = false;
            try {
                this.service.start ();
            } catch (final AppiumServerHasNotBeenStartedLocallyException e) {
                failed = true;
                ErrorHandler.handleAndThrow (Message.ERROR_STARTING_SERVER, e, e.getMessage ());
            } finally {
                if (failed) {
                    stopServer ();
                }
            }
        }
    }

    void stopServer () {
        if (!isCloud () && !this.setting.isExternal () && !isNull (this.service) && this.service.isRunning ()) {
            try {
                this.service.stop ();
                this.service = null;
            } catch (final Exception e) {
                ErrorHandler.handleAndThrow (Message.ERROR_STOPPING_SERVER, e, e.getMessage ());
            }
        }
    }

    private void buildService () {
        LOG.trace ("Building Appium Service started...");
        if (isNotEmpty (this.setting.getConfigPath ())) {
            setArgument (() -> "--config", this.setting.getConfigPath ());
        } else {
            final var target = this.setting.getTarget ();
            this.builder.withIPAddress (getHost (target))
                .withTimeout (ofSeconds (this.setting.getTimeout ()));
            setPort ();
            setAppiumJS ();
            setNodeExe ();
            setArguments ();
        }
        this.service = AppiumDriverLocalService.buildService (this.builder);
        LOG.trace ("Building Appium Service done...");
    }

    private String getHost (final TargetProviders target) {
        return requireNonNullElse (this.setting.getHost (), target.getHost ());
    }

    private String getUrl () {
        final var target = this.setting.getTarget ();
        final var sb = new StringBuilder (
            requireNonNullElse (this.setting.getProtocol (), target.getProtocol ()).name ()).append ("://");
        if (isCloud ()) {
            sb.append (this.setting.getUserName ())
                .append (":")
                .append (this.setting.getPassword ())
                .append ("@");
        }
        sb.append (getHost (target));
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
        return this.setting.getTarget () != TargetProviders.LOCAL && isNotEmpty (
            this.setting.getUserName ()) && isNotEmpty (this.setting.getPassword ());
    }

    private void setAppiumJS () {
        if (isNotEmpty (this.setting.getAppiumPath ())) {
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

    private void setArgument (final ServerArgument flag, final List<String> argList) {
        if (!isNull (argList) && !argList.isEmpty ()) {
            setArgument (flag, join (",", argList));
        }
    }

    private void setArguments () {
        setLogArguments ();
        setCommonArguments ();
    }

    private void setCommonArguments () {
        setArgument (BASEPATH, this.setting.getBasePath ());
        setArgument (SESSION_OVERRIDE, this.setting.isSessionOverride ());
        setArgument (USE_DRIVERS, this.setting.getDriver ()
            .getDriverName ());
        setArgument (ALLOW_INSECURE, this.setting.getAllowInsecure (this.setting.getDriver ()
            .getDriverName ()));
        setArgument (DENY_INSECURE, this.setting.getDenyInsecure (this.setting.getDriver ()
            .getDriverName ()));
        setArgument (CALLBACK_ADDRESS, this.setting.getCallbackAddress ());
        setArgument (CALLBACK_PORT, this.setting.getCallbackPort ());
        setArgument (() -> "--keep-alive-timeout", this.setting.getKeepAliveTimeout ());
        setArgument (() -> "--allow-cors", this.setting.isAllowCors ());
        setArgument (WEB_HOOK, this.setting.getWebhook ());
        setArgument (RELAXED_SECURITY, this.setting.isRelaxedSecurity ());
        setArgument (STRICT_CAPS, this.setting.isStrictCapabilities ());
        setPlugins ();
        setOtherArguments ();
    }

    private void setLogArguments () {
        setLogFile (ParallelSession.getSession ()
            .getSetting ()
            .getUi ()
            .getLogging ()
            .getPath ());
        final var logs = this.setting.getLogging ();
        if (!isNull (logs)) {
            if (!isNull (logs.getLevel ())) {
                setArgument (LOG_LEVEL, logs.getLevel ()
                    .getLevel ());
            }
            setArgument (LOCAL_TIMEZONE, logs.isLocalTimezone ());
            setArgument (LOG_TIMESTAMP, logs.isTimestamp ());
            setArgument (DEBUG_LOG_SPACING, logs.isDebugSpacing ());
        }
    }

    private void setLogFile (final String logFolderPath) {
        if (isNotEmpty (logFolderPath)) {
            final var fileName = format ("appium-{0}-server-{1}.log", ParallelSession.getSession ()
                .getPlatformType ()
                .name ()
                .toLowerCase (), currentThread ().getId ());
            final var filePath = Path.of (System.getProperty ("user.dir"), logFolderPath, fileName)
                .toFile ();
            this.builder.withLogFile (filePath);
        }
    }

    private void setNodeExe () {
        if (isNotEmpty (this.setting.getNodePath ())) {
            final var node = new File (this.setting.getNodePath ());
            this.builder.usingDriverExecutable (node);
        }
    }

    private void setOtherArguments () {
        final var otherArgs = this.setting.getOtherArgs ();
        if (!isNull (otherArgs)) {
            otherArgs.forEach ((k, v) -> {
                final ServerArgument flag = () -> k;
                if (v instanceof Boolean) {
                    setArgument (flag, (boolean) v);
                } else if (v instanceof Integer) {
                    setArgument (flag, (int) v);
                } else {
                    setArgument (flag, v.toString ());
                }
            });
        }
    }

    private void setPlugins () {
        if (!isNull (this.setting.getPlugins ())) {
            setArgument (USE_PLUGINS, join (",", this.setting.getPlugins ()));
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
