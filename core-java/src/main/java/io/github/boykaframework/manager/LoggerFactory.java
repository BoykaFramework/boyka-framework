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

import static org.apache.logging.log4j.core.config.ConfigurationFactory.CATEGORY;

import java.net.URI;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;

/**
 * Handles Log4J logging configurations.
 *
 * @author Wasiq Bhamla
 * @since 02-Apr-2025
 */
@Plugin (name = "LoggerFactory", category = CATEGORY)
@Order (100)
public class LoggerFactory extends ConfigurationFactory {
    @Override
    public Configuration getConfiguration (final LoggerContext loggerContext, final ConfigurationSource source) {
        return getConfiguration (loggerContext, source.toString (), null);
    }

    @Override
    public Configuration getConfiguration (final LoggerContext loggerContext, final String name,
        final URI configLocation) {
        final LoggingBuilder builder = new LoggingBuilder ();
        return builder.build (name, newConfigurationBuilder ());
    }

    @Override
    protected String[] getSupportedTypes () {
        return new String[] { "*" };
    }
}
