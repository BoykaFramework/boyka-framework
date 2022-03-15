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

import static com.github.wasiqb.boyka.actions.ElementActions.attributeOf;
import static com.github.wasiqb.boyka.actions.ElementActions.isDisplayed;
import static com.github.wasiqb.boyka.actions.ElementActions.isEnabled;
import static com.github.wasiqb.boyka.actions.ElementActions.isSelected;
import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;

/**
 * Verification class for verifying the UI element attributes.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class VerifyElementActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Verify attribute of element.
     *
     * @param locator locator of element
     * @param attribute attribute to verify
     *
     * @return {@link StringSubject} to verify the result
     */
    public static StringSubject verifyAttributeOf (final Locator locator, final String attribute) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying attribute of {}", locator);
        LOGGER.traceExit ();
        return assertThat (attributeOf (locator, attribute));
    }

    /**
     * Verify if element is displayed.
     *
     * @param locator locator of element
     *
     * @return {@link BooleanSubject} to verify the result
     */
    public static BooleanSubject verifyElementDisplayed (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is displayed", locator);
        LOGGER.traceExit ();
        return assertThat (isDisplayed (locator));
    }

    /**
     * Verify if element is enabled.
     *
     * @param locator locator of element
     *
     * @return {@link BooleanSubject} to verify the result
     */
    public static BooleanSubject verifyElementEnabled (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is enabled", locator);
        LOGGER.traceExit ();
        return assertThat (isEnabled (locator));
    }

    /**
     * Verify if element is selected.
     *
     * @param locator locator of element
     *
     * @return {@link BooleanSubject} to verify the result
     */
    public static BooleanSubject verifyElementSelected (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying element {} is selected", locator);
        LOGGER.traceExit ();
        return assertThat (isSelected (locator));
    }

    /**
     * Verify text of element.
     *
     * @param locator locator of element
     *
     * @return {@link StringSubject} to verify the result
     */
    public static StringSubject verifyTextOf (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying text of {}", locator);
        LOGGER.traceExit ();
        return assertThat (textOf (locator));
    }

    private VerifyElementActions () {
        // Utility class
    }
}
