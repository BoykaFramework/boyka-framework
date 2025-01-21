/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.actions.interfaces.drivers;

import java.util.List;

import com.google.common.truth.StringSubject;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WindowType;

/**
 * All window related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface IWindowActions {
    /**
     * Close browser window.
     */
    void close ();

    /**
     * Gets the current window handle.
     *
     * @return the current window handle
     */
    String currentHandle ();

    /**
     * Switch browser window to full screen.
     */
    void fullScreen ();

    /**
     * Gets the base 64 content of the screenshot.
     *
     * @return Image content in Base64 encoded string
     */
    String getScreenshot ();

    /**
     * Title of the browser.
     *
     * @return title of the browser
     */
    String getTitle ();

    /**
     * Gets all open window handles.
     *
     * @return all open window handles
     */
    List<String> handles ();

    /**
     * Makes browser window maximized.
     */
    void maximize ();

    /**
     * Makes browser window minimized.
     */
    void minimize ();

    /**
     * \ Starts video recording of the window.
     */
    void startRecording ();

    /**
     * Stops video recording of the window.
     */
    void stopRecording ();

    /**
     * Switch to window for specific name / handle.
     *
     * @param nameOrHandle name or handle of the window
     */
    void switchTo (final String nameOrHandle);

    /**
     * Switch to default first window.
     */
    void switchToDefault ();

    /**
     * Switch to new window.
     *
     * @param type type of window
     */
    void switchToNew (final WindowType type);

    /**
     * Takes screenshot of browser.
     */
    void takeScreenshot ();

    /**
     * Take screenshot of browser.
     *
     * @param fileName file name
     */
    void takeScreenshot (final String fileName);

    /**
     * Verify browser title.
     *
     * @return {@link StringSubject} to verify browser title
     */
    StringSubject verifyTitle ();

    /**
     * Gets the window / device screen size.
     *
     * @return Size of the window screen
     */
    Dimension viewportSize ();
}
