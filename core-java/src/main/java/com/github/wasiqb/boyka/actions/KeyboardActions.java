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

package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.actions.ElementActions.clear;
import static com.github.wasiqb.boyka.enums.Message.NO_KEYBOARD_ERROR;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.enums.PlatformType.IOS;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static io.appium.java_client.remote.HideKeyboardStrategy.TAP_OUTSIDE;
import static java.util.Arrays.stream;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.Keys.chord;

import com.github.wasiqb.boyka.builders.Locator;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.Logger;

/**
 * Perform Keyboard actions.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class KeyboardActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Append text in text field.
     *
     * @param locator {@link Locator} of text field
     * @param text text to append
     */
    public static void appendText (final Locator locator, final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Appending text {} to element {}", text, locator.getName ());
        performElementAction (e -> e.sendKeys (text), locator);
        LOGGER.traceExit ();
    }

    /**
     * Enter text in text field.
     *
     * @param locator {@link Locator} of text field
     * @param text text to enter
     */
    public static void enterText (final Locator locator, final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} in element {}", text, locator.getName ());
        clear (locator);
        appendText (locator, text);
        LOGGER.traceExit ();
    }

    /**
     * Hides the keyboard if visible.
     */
    public static void hideKeyboard () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        if (isKeyboardVisible ()) {
            if (platform == IOS) {
                performDriverAction ((final IOSDriver d) -> d.hideKeyboard (TAP_OUTSIDE, "return"));
            } else if (platform == ANDROID) {
                performDriverAction (HidesKeyboard::hideKeyboard);
            }
        }
    }

    /**
     * Gets the keyboard state whether it is visible or not.
     *
     * @return true, if visible.
     */
    public static boolean isKeyboardVisible () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        return getDriverAttribute (HasOnScreenKeyboard::isKeyboardShown, false);
    }

    /**
     * Press specified keys in text field.
     *
     * @param locator {@link Locator} of text field
     * @param keys keys to press
     */
    public static void pressKey (final Locator locator, final CharSequence... keys) {
        LOGGER.traceEntry ();
        stream (keys).forEach (key -> LOGGER.info ("Pressing key {} in element {}", key, locator.getName ()));
        performElementAction (e -> e.sendKeys (chord (keys)), locator);
        LOGGER.traceExit ();
    }

    private KeyboardActions () {
        // Utility class
    }
}
