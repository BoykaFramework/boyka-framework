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

package io.github.boykaframework.actions.elements;

import static com.google.common.truth.Truth.assertWithMessage;
import static io.github.boykaframework.actions.CommonActions.getElementAttribute;
import static io.github.boykaframework.actions.CommonActions.pause;
import static io.github.boykaframework.actions.CommonActions.performElementAction;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.enums.ListenerType.ELEMENT_ACTION;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import io.github.boykaframework.actions.interfaces.elements.IElementActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IElementActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.config.ui.DelaySetting;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Perform element specific actions.
 *
 * @author Wasiq Bhamla
 * @since 09-Mar-2022
 */
public class ElementActions implements IElementActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handle all other element actions
     *
     * @param locator Locator of the element
     *
     * @return {@link IElementActions} instance objects
     */
    public static IElementActions onElement (final Locator locator) {
        return new ElementActions (locator);
    }

    protected final DelaySetting            delaySetting;
    protected final Locator                 locator;
    private final   IElementActionsListener listener;

    ElementActions (final Locator locator) {
        this.locator = locator;
        this.listener = getSession ().getListener (ELEMENT_ACTION);
        this.delaySetting = getSession ().getSetting ()
            .getUi ()
            .getDelay ();
    }

    @Override
    public void clear () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clearing element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClear (this.locator));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (WebElement::clear, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public String getAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting attribute: {} of element located by: {}", attribute, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetAttribute (this.locator, attribute));
        LOGGER.traceExit ();
        return getElementAttribute (e -> e.getAttribute (attribute), this.locator, EMPTY);
    }

    @Override
    public String getStyle (final String styleName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting style: {} of element located by: {}", styleName, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetStyle (this.locator, styleName));
        LOGGER.traceExit ();
        return getElementAttribute (e -> e.getCssValue (styleName), this.locator, EMPTY);
    }

    @Override
    public String getText () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting text of element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetText (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::getText, this.locator, EMPTY));
    }

    @Override
    public boolean isDisplayed () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is displayed", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onIsDisplayed (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::isDisplayed, this.locator, false));
    }

    @Override
    public boolean isEnabled () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is enabled", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onIsEnabled (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::isEnabled, this.locator, false));
    }

    @Override
    public boolean isSelected () {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is selected", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onIsSelected (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::isSelected, this.locator, false));
    }

    @Override
    public void scrollIntoView () {
        LOGGER.info ("Scrolling element located by [{}] into view", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onScrollIntoView (this.locator));
        pause (this.delaySetting.getBeforeMouseMove ());
        performElementAction (e -> withDriver ().executeScript ("arguments[0].scrollIntoView(true);", e), this.locator);
    }

    @Override
    public StringSubject verifyAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying attribute of {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyAttribute (this.locator, attribute));
        LOGGER.traceExit ();
        return assertWithMessage (attribute).that (getAttribute (attribute));
    }

    @Override
    public BooleanSubject verifyIsDisplayed () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is displayed", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsDisplayed (this.locator));
        LOGGER.traceExit ();
        return assertWithMessage ("Displayed").that (isDisplayed ());
    }

    @Override
    public BooleanSubject verifyIsEnabled () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is enabled", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsEnabled (this.locator));
        LOGGER.traceExit ();
        return assertWithMessage ("Enabled").that (isEnabled ());
    }

    @Override
    public BooleanSubject verifyIsSelected () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is selected", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyIsSelected (this.locator));
        LOGGER.traceExit ();
        return assertWithMessage ("Selected").that (isSelected ());
    }

    @Override
    public StringSubject verifyStyle (final String styleName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying style of {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyStyle (this.locator, styleName));
        LOGGER.traceExit ();
        return assertWithMessage (styleName).that (getStyle (styleName));
    }

    @Override
    public StringSubject verifyText () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying text of {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyText (this.locator));
        LOGGER.traceExit ();
        return assertWithMessage ("Text").that (getText ().trim ());
    }
}
