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

import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.enums.ListenerType.TEXT_BOX_ACTION;
import static com.github.wasiqb.boyka.enums.PlatformType.IOS;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.Keys.chord;

import com.github.wasiqb.boyka.actions.interfaces.elements.ITextBoxActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import com.github.wasiqb.boyka.builders.Locator;
import org.apache.logging.log4j.Logger;

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

    private final ITextBoxActionsListener listener;

    TextBoxActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (TEXT_BOX_ACTION);
    }

    @Override
    public void enterText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} to element {}", text, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onEnterText (this.locator, text));
        performElementAction (e -> {
            e.sendKeys (text);
            if (getSession ().getPlatformType () == IOS) {
                e.sendKeys ("\n");
            }
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void pressKey (final CharSequence... keys) {
        LOGGER.traceEntry ();
        LOGGER.info ("Pressing keys [{}]...", asList (keys));
        ofNullable (this.listener).ifPresent (l -> l.onPressKey (this.locator, keys));
        stream (keys).forEach (key -> LOGGER.info ("Pressing key {} in element {}", key, this.locator.getName ()));
        performElementAction (e -> e.sendKeys (chord (keys)), this.locator);
        LOGGER.traceExit ();
    }
}
