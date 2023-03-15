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

package com.github.wasiqb.boyka.actions.elements;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.enums.Message.NO_KEYBOARD_ERROR;
import static com.github.wasiqb.boyka.enums.PlatformType.IOS;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Arrays.stream;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.Keys.chord;

import com.github.wasiqb.boyka.actions.interfaces.elements.ITextBoxActions;
import com.github.wasiqb.boyka.builders.Locator;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;

/**
 * All text box related actions
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2023
 */
public class TextBoxActions extends ClickableActions implements ITextBoxActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets instance for text box actions class
     *
     * @param locator Locator of the element
     *
     * @return Instance of Text box actions class
     */
    public static ITextBoxActions onTextBox (final Locator locator) {
        return new TextBoxActions (locator);
    }

    TextBoxActions (final Locator locator) {
        super (locator);
    }

    @Override
    public void enterText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} to element {}", text, this.locator.getName ());
        performElementAction (e -> {
            e.sendKeys (text);
            if (getSession ().getPlatformType () == IOS) {
                e.sendKeys ("\n");
            }
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void hideKeyboard () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        if (isKeyboardVisible ()) {
            performDriverAction (HidesKeyboard::hideKeyboard);
        }
    }

    @Override
    public boolean isKeyboardVisible () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        return getDriverAttribute (HasOnScreenKeyboard::isKeyboardShown, false);
    }

    @Override
    public void pressKey (final CharSequence... keys) {
        LOGGER.traceEntry ();
        stream (keys).forEach (key -> LOGGER.info ("Pressing key {} in element {}", key, this.locator.getName ()));
        performElementAction (e -> e.sendKeys (chord (keys)), this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void uploadFile (final String filePath) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering File path [{}] to element {}", filePath, this.locator.getName ());
        performElementAction (e -> {
            ((RemoteWebElement) e).setFileDetector (new LocalFileDetector ());
            enterText (filePath);
        }, this.locator);
        LOGGER.traceExit ();
    }
}
