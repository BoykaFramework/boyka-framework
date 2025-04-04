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

package io.github.boykaframework.actions.elements;

import static com.google.common.truth.Truth.assertWithMessage;
import static io.github.boykaframework.actions.CommonActions.pause;
import static io.github.boykaframework.actions.CommonActions.performElementAction;
import static io.github.boykaframework.enums.ListenerType.TEXT_BOX_ACTION;
import static io.github.boykaframework.enums.PlatformType.ANDROID;
import static io.github.boykaframework.enums.PlatformType.IOS;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.google.common.truth.StringSubject;
import io.github.boykaframework.actions.interfaces.elements.ITextBoxActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.ApplicationType;
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

    private TextBoxActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (TEXT_BOX_ACTION);
    }

    @Override
    public void enterText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Entering text {} to element {}", text, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onEnterText (this.locator, text));
        pause (this.delaySetting.getBeforeTyping ());
        sendKeys (text);
        LOGGER.traceExit ();
    }

    @Override
    public void focus () {
        LOGGER.traceEntry ();
        LOGGER.info ("Focusing element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onFocus (this.locator));
        sendKeys (EMPTY);
        LOGGER.traceExit ();
    }

    @Override
    public String inputValue () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting input value of textbox located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onInputValue (this.locator));
        final var attribute = getInputAttribute ();
        return LOGGER.traceExit (getAttributeValue (attribute));
    }

    @Override
    public StringSubject verifyInputValue () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verify the input value of textbox located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyInputValue (this.locator));
        return LOGGER.traceExit (
            assertWithMessage (this.locator.getName ()).that (getAttributeValue (getInputAttribute ())));
    }

    private String getInputAttribute () {
        var result = "value";
        if (getSession ().getPlatformType () == ANDROID) {
            result = "text";
        }
        return result;
    }

    private void sendKeys (final String text) {
        performElementAction (e -> {
            if (!isEmpty (text)) {
                e.sendKeys (text);
                if (getSession ().getPlatformType () == IOS && getSession ().getMobileSetting ()
                    .getDevice ()
                    .getApplication ()
                    .getType () != ApplicationType.WEB) {
                    e.sendKeys ("\n");
                }
            }
        }, this.locator);
    }
}
