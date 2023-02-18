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

import static com.github.wasiqb.boyka.actions.CommonActions.getElementAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.enums.Message.ERROR_DESELECT_FROM_DROPDOWN;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static com.google.common.truth.Truth.assertThat;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import com.github.wasiqb.boyka.actions.interfaces.elements.IDropDownActions;
import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.StringSubject;
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
    public static DropDownActions onDropDown (final Locator locator) {
        return new DropDownActions (locator);
    }

    DropDownActions (final Locator locator) {
        super (locator);
    }

    @Override
    public void deselectAll () {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {}", this.locator.getName ());
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
        return getElementAttribute (e -> {
            final var select = new Select (e);
            return select.getAllSelectedOptions ()
                .stream ()
                .map (WebElement::getText)
                .collect (toList ());
        }, this.locator, emptyList ());
    }

    @Override
    public StringSubject verifySelectedItem () {
        return assertThat (selectedItem ());
    }

    @Override
    public IterableSubject verifySelectedItems () {
        return assertThat (selectedItems ());
    }
}
