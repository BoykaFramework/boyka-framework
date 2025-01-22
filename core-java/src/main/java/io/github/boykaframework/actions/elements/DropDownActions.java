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
import static io.github.boykaframework.enums.ListenerType.DROP_DOWN_ACTION;
import static io.github.boykaframework.enums.Message.ERROR_DESELECT_FROM_DROPDOWN;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static java.util.Collections.emptyList;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import com.google.common.truth.IterableSubject;
import com.google.common.truth.StringSubject;
import io.github.boykaframework.actions.interfaces.elements.IDropDownActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IDropDownActionsListener;
import io.github.boykaframework.builders.Locator;
import lombok.Setter;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 * Performs dropdown specific actions.
 *
 * @author Wasiq Bhamla
 * @since 30-Jul-2022
 */
@Setter
public class DropDownActions extends ClickableActions implements IDropDownActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Interact with drop down element.
     *
     * @param locator {@link Locator} of the dropdown
     *
     * @return action instance to interact with dropdown
     */
    public static IDropDownActions onDropDown (final Locator locator) {
        return new DropDownActions (locator);
    }

    private final IDropDownActionsListener listener;

    DropDownActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (DROP_DOWN_ACTION);
    }

    @Override
    public void deselectAll () {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDeselectAll (this.locator));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectAll ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void deselectByIndex (final int index) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by index: {}", this.locator.getName (), index);
        ofNullable (this.listener).ifPresent (l -> l.onDeselectByIndex (this.locator, index));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByIndex (index);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void deselectByText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by visible text: {}", this.locator.getName (), text);
        ofNullable (this.listener).ifPresent (l -> l.onDeselectByText (this.locator, text));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByVisibleText (text);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void deselectByValue (final String value) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by value: {}", this.locator.getName (), value);
        ofNullable (this.listener).ifPresent (l -> l.onDeselectByValue (this.locator, value));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByValue (value);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void selectByIndex (final int index) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by index: {}", this.locator.getName (), index);
        ofNullable (this.listener).ifPresent (l -> l.onSelectByIndex (this.locator, index));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByIndex (index);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void selectByText (final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by text: {}", this.locator.getName (), text);
        ofNullable (this.listener).ifPresent (l -> l.onSelectByText (this.locator, text));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByVisibleText (text);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void selectByValue (final String value) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by value: {}", this.locator.getName (), value);
        ofNullable (this.listener).ifPresent (l -> l.onSelectByValue (this.locator, value));
        pause (this.delaySetting.getBeforeTyping ());
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByValue (value);
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public String selectedItem () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting selected option from element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onSelectedItem (this.locator));
        return getElementAttribute (element -> {
            final var select = new Select (element);
            try {
                return select.getFirstSelectedOption ()
                    .getText ();
            } catch (final NoSuchElementException e) {
                return EMPTY;
            }
        }, this.locator, EMPTY);
    }

    @Override
    public List<String> selectedItems () {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting all selected options from element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onSelectedItems (this.locator));
        return getElementAttribute (e -> {
            final var select = new Select (e);
            return select.getAllSelectedOptions ()
                .stream ()
                .map (WebElement::getText)
                .toList ();
        }, this.locator, emptyList ());
    }

    @Override
    public StringSubject verifySelectedItem () {
        LOGGER.info ("Verifying the selected item...");
        ofNullable (this.listener).ifPresent (l -> l.onVerifySelectedItem (this.locator));
        return assertWithMessage ("Selected Item").that (selectedItem ());
    }

    @Override
    public IterableSubject verifySelectedItems () {
        LOGGER.info ("Verifying the selected items...");
        ofNullable (this.listener).ifPresent (l -> l.onVerifySelectedItems (this.locator));
        return assertWithMessage ("Selected Items").that (selectedItems ());
    }
}
