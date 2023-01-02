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

import static com.github.wasiqb.boyka.actions.CommonActions.getElementAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.enums.Message.ERROR_DESELECT_FROM_DROPDOWN;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.List;

import com.github.wasiqb.boyka.builders.Locator;
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
public final class DropDownActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Deselects all the selected values.
     *
     * @param locator {@link Locator} of drop down
     */
    public static void deselectAll (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {}", locator.getName ());
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectAll ();
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Deselects the option from the dropdown based on its index.
     *
     * @param locator {@link Locator} of dropdown
     * @param index index of the option to deselect
     */
    public static void deselectByIndex (final Locator locator, final int index) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by index: {}", locator.getName (), index);
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByIndex (index);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Deselects the option from the dropdown based on its visible text.
     *
     * @param locator {@link Locator} of dropdown
     * @param text visible text of the option to deselect
     */
    public static void deselectByText (final Locator locator, final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by visible text: {}", locator.getName (), text);
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByVisibleText (text);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Deselects the option from the dropdown based on its value.
     *
     * @param locator {@link Locator} of dropdown
     * @param value value of the option to deselect
     */
    public static void deselectByValue (final Locator locator, final String value) {
        LOGGER.traceEntry ();
        LOGGER.info ("Deselecting element located by: {} by value: {}", locator.getName (), value);
        performElementAction (e -> {
            final var select = new Select (e);
            if (!select.isMultiple ()) {
                throwError (ERROR_DESELECT_FROM_DROPDOWN);
            }
            select.deselectByValue (value);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Selects the value from dropdown based on index.
     *
     * @param locator locator of the dropdown
     * @param index index to be selected
     */
    public static void selectByIndex (final Locator locator, final int index) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by index: {}", locator.getName (), index);
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByIndex (index);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Selects the value from dropdown based on visible text.
     *
     * @param locator locator of the dropdown
     * @param text text to be selected
     */
    public static void selectByText (final Locator locator, final String text) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by text: {}", locator.getName (), text);
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByVisibleText (text);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Selects the value from dropdown based on value.
     *
     * @param locator locator of the dropdown
     * @param value value to be selected
     */
    public static void selectByValue (final Locator locator, final String value) {
        LOGGER.traceEntry ();
        LOGGER.info ("Selecting element located by: {} by value: {}", locator.getName (), value);
        performElementAction (e -> {
            final var select = new Select (e);
            select.selectByValue (value);
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Returns the first selected value from dropdown.
     *
     * @param locator locator of the dropdown
     *
     * @return first selected value
     */
    public static String selectedItem (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting selected option from element located by: {}", locator.getName ());
        return getElementAttribute (element -> {
            final var select = new Select (element);
            try {
                return select.getFirstSelectedOption ()
                    .getText ();
            } catch (final NoSuchElementException e) {
                return EMPTY;
            }
        }, locator, EMPTY);
    }

    /**
     * Gets all the selected values from the dropdown.
     *
     * @param locator locator of the dropdown
     *
     * @return list of selected values
     */
    public static List<String> selectedItems (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting all selected options from element located by: {}", locator.getName ());
        return getElementAttribute (e -> {
            final var select = new Select (e);
            return select.getAllSelectedOptions ()
                .stream ()
                .map (WebElement::getText)
                .collect (toList ());
        }, locator, emptyList ());
    }

    private DropDownActions () {
        // Utility class.
    }
}
