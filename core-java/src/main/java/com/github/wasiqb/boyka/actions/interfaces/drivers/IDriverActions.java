/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.actions.interfaces.drivers;

import java.time.Duration;
import java.util.function.Function;

import org.openqa.selenium.WebDriver;

/**
 * All other driver related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface IDriverActions {
    /**
     * Executes javascript in browser.
     *
     * @param script Javascript to execute
     * @param args Arguments to pass to javascript
     * @param <T> Return type of the result of the script
     *
     * @return the result of the script
     */
    <T> T executeScript (final String script, final Object... args);

    /**
     * Pause for the specified time.
     *
     * @param time Duration to pause.
     */
    void pause (final Duration time);

    /**
     * Save all the available logs to files in `logs` folder.
     */
    void saveLogs ();

    /**
     * Wait for a specific condition to be true.
     *
     * @param condition condition to wait for
     * @param <T> type of condition
     *
     * @return value of the condition
     */
    <T> T waitUntil (final Function<WebDriver, T> condition);
}
