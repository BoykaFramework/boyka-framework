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
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Perform element specific actions.
 *
 * @author Wasiq Bhamla
 * @since 09-Mar-2022
 */
public final class ElementActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets the value of the attribute of the element.
     *
     * @param locator locator of the element
     * @param attribute attribute of the element
     *
     * @return value of the attribute of the element
     */
    public static String attributeOf (final Locator locator, final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting attribute: {} of element located by: {}", attribute, locator);
        LOGGER.traceExit ();
        return getElementAttribute (e -> e.getAttribute (attribute), locator);
    }

    /**
     * Clear text, selection of element.
     *
     * @param locator {@link Locator} of text field
     */
    public static void clear (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Clearing element located by: {}", locator);
        performElementAction (WebElement::clear, locator);
        LOGGER.traceExit ();
    }

    /**
     * Gets the value if the element is displayed.
     *
     * @param locator locator of the element
     *
     * @return true if the element is displayed, false otherwise
     */
    public static boolean isDisplayed (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is displayed", locator);
        return LOGGER.traceExit (getElementAttribute (WebElement::isDisplayed, locator));
    }

    /**
     * Gets the value if the element is enabled.
     *
     * @param locator locator of the element
     *
     * @return true if the element is enabled, false otherwise
     */
    public static boolean isEnabled (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is enabled", locator);
        return LOGGER.traceExit (getElementAttribute (WebElement::isEnabled, locator));
    }

    /**
     * Gets the value if the element is selected.
     *
     * @param locator locator of the element
     *
     * @return true if the element is selected, false otherwise
     */
    public static boolean isSelected (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Checking if element located by: {} is selected", locator);
        return LOGGER.traceExit (getElementAttribute (WebElement::isSelected, locator));
    }

    /**
     * Submit the element.
     *
     * @param locator {@link Locator} of element
     */
    public static void submit (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Submitting element located by: {}", locator);
        performElementAction (WebElement::submit, locator);
        LOGGER.traceExit ();
    }

    /**
     * Gets the text of the element.
     *
     * @param locator locator of the element
     *
     * @return text of the element
     */
    public static String textOf (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Getting text of element located by: {}", locator);
        return LOGGER.traceExit (getElementAttribute (WebElement::getText, locator));
    }

    private ElementActions () {
        // Utility class
    }
}
