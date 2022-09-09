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

package com.github.wasiqb.boyka.config.ui.mobile.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Appium server log lever.
 *
 * @author Wasiq Bhamla
 * @since 07-Sept-2022
 */
@ToString
@AllArgsConstructor
@Getter
public enum LogLevel {
    /**
     * Debug.
     */
    DEBUG ("debug"),
    /**
     * Debug+Debug
     */
    DEBUG_DEBUG ("debug:debug"),
    /**
     * Debug+Error
     */
    DEBUG_ERROR ("debug:error"),
    /**
     * Debug+Info
     */
    DEBUG_INFO ("debug:info"),
    /**
     * Debug+Warn
     */
    DEBUG_WARN ("debug:warn"),
    /**
     * Error
     */
    ERROR ("error"),
    /**
     * Error+Debug
     */
    ERROR_DEBUG ("error:debug"),
    /**
     * Error+Error
     */
    ERROR_ERROR ("error:error"),
    /**
     * Error+Info
     */
    ERROR_INFO ("error:info"),
    /**
     * Error+Warn
     */
    ERROR_WARN ("error:warn"),
    /**
     * Info
     */
    INFO ("info"),
    /**
     * Info+Debug
     */
    INFO_DEBUG ("info:debug"),
    /**
     * Info+Error
     */
    INFO_ERROR ("info:error"),
    /**
     * Info+Info
     */
    INFO_INFO ("info:info"),
    /**
     * Info+Warn
     */
    INFO_WARN ("info:warn"),
    /**
     * Warn
     */
    WARN ("warn"),
    /**
     * Warn+Debug
     */
    WARN_DEBUG ("warn:debug"),
    /**
     * Warn+Error
     */
    WARN_ERROR ("warn:error"),
    /**
     * Warn+Info
     */
    WARN_INFO ("warn:info"),
    /**
     * Warn+Warn
     */
    WARN_WARN ("warn:warn");

    private final String level;
}
