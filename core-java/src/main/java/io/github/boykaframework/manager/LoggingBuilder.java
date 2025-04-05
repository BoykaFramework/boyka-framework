/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.Level.ALL;
import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.ERROR;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.Level.WARN;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import io.github.boykaframework.config.logging.ArchiveSetting;
import io.github.boykaframework.config.logging.LoggerSetting;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 * Handle Log4j logging config
 *
 * @author Wasiq Bhamla
 * @since 02-Apr-2025
 */
class LoggingBuilder {
    @AllArgsConstructor
    @Getter
    private static class LoggerConfig {
        private String     fileName;
        private String     loggerName;
        private LoggerType loggerType;
        private Level      status;
    }

    private enum LoggerType {
        CONSOLE,
        FILE
    }

    private static final String DIR             = getProperty ("user.dir");
    private static final String MESSAGE_PATTERN = "[%d{HH:mm:ss.SSS}] [%-5level] [%tn] - %msg (%logger{1}:%method:%L) %throwable{short.message}%n";

    private final LoggerSetting setting;

    LoggingBuilder () {
        super ();
        this.setting = getSession ().getSetting ()
            .getLogging ();
    }

    Configuration build (final String name, final ConfigurationBuilder<BuiltConfiguration> build) {
        Configuration result = null;
        if (!isNull (this.setting)) {
            build.setConfigurationName (name);
            build.setStatusLevel (WARN);
            build.setMonitorInterval ("30");
            addAppender (build);
            addRootLogger (build);
            result = build.build ();
        }
        return result;
    }

    private void addAppender (final ConfigurationBuilder<BuiltConfiguration> build) {
        final var loggers = getLoggers ();
        loggers.forEach (loggerConfig -> {
            final var plugin = getAppenderPlugin (loggerConfig.getLoggerType ());
            final var appenderCom = build.newAppender (loggerConfig.getLoggerName (), plugin);
            addAttributes (appenderCom, loggerConfig.getLoggerType (), loggerConfig.getFileName ());
            addLayout (appenderCom, build);
            addComponent (appenderCom, build, loggerConfig.getLoggerType (), this.setting.getArchive ());
            build.add (appenderCom);
        });
    }

    private void addAttributes (final AppenderComponentBuilder appenderCom, final LoggerType type,
        final String fileName) {
        if (type == LoggerType.CONSOLE) {
            appenderCom.addAttribute ("target", ConsoleAppender.Target.valueOf ("SYSTEM_OUT"));
        } else if (type == LoggerType.FILE) {
            final var dir = Path.of (DIR, this.setting.getLogsFolder ())
                .toString ();

            appenderCom.addAttribute ("fileName", format ("{0}/{1}.log", dir, fileName));

            final var filePattern = "%s/%%d{yyyy-MM-dd}/%s-%%d{hh-mm}.log".formatted (dir, fileName);
            appenderCom.addAttribute ("filePattern", filePattern);

            appenderCom.addAttribute ("append", false);
            appenderCom.addAttribute ("immediateFlush", true);
        }
    }

    private void addComponent (final AppenderComponentBuilder component,
        final ConfigurationBuilder<BuiltConfiguration> build, final LoggerType loggerType,
        final ArchiveSetting archiveSetting) {
        if (loggerType == LoggerType.FILE) {
            final ComponentBuilder<?> comp = build.newComponent ("Policies");
            if (!isNull (archiveSetting)) {
                if (archiveSetting.getMaxDays () > 0) {
                    final ComponentBuilder<?> newComp = build.newComponent ("TimeBasedTriggeringPolicy");
                    newComp.addAttribute ("interval", archiveSetting.getMaxDays ());
                    newComp.addAttribute ("modulate", true);
                    comp.addComponent (newComp);
                }
                if (archiveSetting.getMaxSize () > 0) {
                    final ComponentBuilder<?> newComp = build.newComponent ("SizeBasedTriggeringPolicy");
                    newComp.addAttribute ("size", format ("{0} {1}", archiveSetting.getMaxSize (),
                        archiveSetting.getSizeUnit ()
                            .name ()));
                    comp.addComponent (newComp);
                }
                if (archiveSetting.isOnEveryRun ()) {
                    final ComponentBuilder<?> newComp = build.newComponent ("OnStartupTriggeringPolicy");
                    newComp.addAttribute ("minSize", 0);
                    comp.addComponent (newComp);
                }
            }
            component.addComponent (comp);
        }
    }

    private void addLayout (final AppenderComponentBuilder appenderCom,
        final ConfigurationBuilder<BuiltConfiguration> build) {
        final var layoutCom = build.newLayout ("PatternLayout");
        layoutCom.addAttribute ("pattern", MESSAGE_PATTERN);
        appenderCom.add (layoutCom);
    }

    private void addRootLogger (final ConfigurationBuilder<BuiltConfiguration> build) {
        final var root = build.newRootLogger (ALL);
        final var loggers = getLoggers ();

        for (final var logger : loggers) {
            final var ref = build.newAppenderRef (logger.getLoggerName ());
            ref.addAttribute ("level", logger.getStatus ());
            root.add (ref);
        }
        build.add (root);
    }

    private String getAppenderPlugin (final LoggerType loggerType) {
        return switch (loggerType) {
            case CONSOLE -> "Console";
            case FILE -> "RollingFile";
        };
    }

    private List<LoggerConfig> getLoggers () {
        final List<LoggerConfig> loggers = new ArrayList<> ();
        loggers.add (new LoggerConfig ("boyka-log-all", "all-log-appender", LoggerType.FILE, DEBUG));
        loggers.add (new LoggerConfig ("boyka-log-main", "test-log-appender", LoggerType.FILE, INFO));
        loggers.add (new LoggerConfig ("boyka-log-error", "error-log-appender", LoggerType.FILE, ERROR));
        if (this.setting.isConsoleLogs ()) {
            loggers.add (new LoggerConfig (null, "console-appender", LoggerType.CONSOLE, INFO));
        }
        return loggers;
    }
}
