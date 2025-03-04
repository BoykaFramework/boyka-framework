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
import static io.github.boykaframework.actions.CommonActions.getElementAttribute;
import static io.github.boykaframework.actions.CommonActions.pause;
import static io.github.boykaframework.actions.CommonActions.performElementAction;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.elements.ElementFinder.finds;
import static io.github.boykaframework.enums.ListenerType.ELEMENT_ACTION;
import static io.github.boykaframework.enums.WaitStrategy.VISIBLE;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.StringSubject;
import com.google.common.truth.Truth;
import io.github.boykaframework.actions.interfaces.elements.IElementActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IElementActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.config.ui.DelaySetting;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
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

    /**
     * delay related settings.
     */
    protected final DelaySetting            delaySetting;
    /**
     * Locator of the target element.
     */
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

    @SuppressWarnings ("unchecked")
    @Override
    public <T> T executeScript (final String script, final Object... args) {
        LOGGER.traceEntry ();
        LOGGER.info ("Executing script on element");
        ofNullable (this.listener).ifPresent (l -> l.onExecuteScript (this.locator, script, args));
        return (T) getElementAttribute (
            (driver, element) -> ((JavascriptExecutor) driver).executeScript (script, element, args), this.locator,
            null);
    }

    @Override
    public String getAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting attribute: {} of element located by: {}", attribute, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetAttribute (this.locator, attribute));
        return LOGGER.traceExit (getAttributeValue (attribute));
    }

    @Override
    public WebElement getElement () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting the WebElement located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetElement (this.locator));
        return getElementAttribute (e -> e, this.locator, null);
    }

    @Override
    public Point getLocation () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting location of element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetLocation (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::getLocation, this.locator, new Point (0, 0)));
    }

    @Override
    public String getProperty (final String property) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting Property: {} of element located by: {}", property, this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetProperty (property));
        return LOGGER.traceExit (getPropertyValue (property));
    }

    @Override
    public Dimension getSize () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting size of element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onGetSize (this.locator));
        return LOGGER.traceExit (getElementAttribute (WebElement::getSize, this.locator, new Dimension (0, 0)));
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
        return LOGGER.traceExit (displayed ());
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
    public List<String> itemList () {
        LOGGER.info ("Getting the list of elements: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (IElementActionsListener::onItemList);
        return getElementList ();
    }

    @Override
    public void scrollIntoView () {
        LOGGER.info ("Scrolling element located by [{}] into view", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onScrollIntoView (this.locator));
        pause (this.delaySetting.getBeforeMouseMove ());
        performElementAction (e -> {
            if (!displayed ()) {
                withDriver ().executeScript ("arguments[0].scrollIntoView(true);", e);
            }
        }, this.locator);
    }

    @Override
    public StringSubject verifyAttribute (final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying attribute of {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyAttribute (this.locator, attribute));
        LOGGER.traceExit ();
        return assertWithMessage (attribute).that (getAttributeValue (attribute));
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
    public IterableSubject verifyItems () {
        LOGGER.info ("Verifying the list of elements: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (IElementActionsListener::onVerifyItems);
        return Truth.assertWithMessage (this.locator.getName ())
            .that (getElementList ());
    }

    @Override
    public StringSubject verifyProperty (final String property) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying property of {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onVerifyProperty (property));
        LOGGER.traceExit ();
        return assertWithMessage (property).that (getProperty (property));
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

    /**
     * Gets the element attribute value.
     *
     * @param attribute Attribute name
     *
     * @return Attribute value
     */
    protected String getAttributeValue (final String attribute) {
        return getElementAttribute (e -> e.getDomAttribute (attribute), this.locator, EMPTY);
    }

    private boolean displayed () {
        return getElementAttribute (WebElement::isDisplayed, this.locator, false);
    }

    private List<String> getElementList () {
        return getElementAttribute (element -> {
            final var items = finds (this.locator, VISIBLE);
            return items.stream ()
                .map (WebElement::getText)
                .toList ();
        }, this.locator, List.of ());
    }

    private String getPropertyValue (final String property) {
        return getElementAttribute (e -> e.getDomProperty (property), this.locator, EMPTY);
    }
}
