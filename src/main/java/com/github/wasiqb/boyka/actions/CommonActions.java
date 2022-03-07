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

import static com.github.wasiqb.boyka.actions.ElementFinder.find;
import static com.github.wasiqb.boyka.enums.WaitStrategy.CLICKABLE;
import static com.github.wasiqb.boyka.enums.WaitStrategy.VISIBLE;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.google.common.truth.Truth.assertThat;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.StringSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Common action methods to perform different actions on devices / browsers and on elements.
 *
 * @author Wasiq Bhamla
 * @since 25-Feb-2022
 */
@SuppressWarnings ("unchecked")
final class CommonActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Gets driver specific attributes.
     *
     * @param action action to get driver specific attributes
     * @param <D> driver type
     * @param <E> attribute type
     *
     * @return driver specific attribute.
     */
    public static <D extends WebDriver, E> E getDriverAttribute (final Function<D, E> action) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (action.apply ((D) getSession ().getDriver ()));
    }

    /**
     * Gets element specific attributes.
     *
     * @param action action to get element specific attributes
     * @param locator locator to find element
     * @param <E> attribute type
     *
     * @return element specific attribute.
     */
    public static <E> E getElementAttribute (final Function<WebElement, E> action, final Locator locator) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (action.apply (find (locator, VISIBLE)));
    }

    /**
     * Perform driver specific action.
     *
     * @param action action to perform
     * @param <D> driver type
     */
    public static <D extends WebDriver> void performDriverAction (final Consumer<D> action) {
        LOGGER.traceEntry ();
        action.accept ((D) getSession ().getDriver ());
        LOGGER.traceExit ();
    }

    /**
     * Perform element specific action.
     *
     * @param action action to perform
     * @param locator locator to find element
     */
    public static void performElementAction (final Consumer<WebElement> action, final Locator locator) {
        LOGGER.traceEntry ();
        action.accept (find (locator, CLICKABLE));
        LOGGER.traceExit ();
    }

    /**
     * Verify if driver boolean attributes.
     *
     * @param actual actual test
     * @param <D> driver type
     *
     * @return {@link BooleanSubject} to verify boolean attributes
     */
    public static <D extends WebDriver> StringSubject verifyDriverTextAttribute (final Function<D, String> actual) {
        LOGGER.traceEntry ();
        return assertThat (getDriverAttribute (actual));
    }

    /**
     * Verify if element boolean attributes.
     *
     * @param actual actual test
     * @param locator locator to find element
     *
     * @return {@link BooleanSubject} to verify boolean attributes
     */
    public static BooleanSubject verifyElementBooleanAttribute (final Predicate<WebElement> actual,
        final Locator locator) {
        LOGGER.traceEntry ();
        return assertThat (getElementAttribute (actual::test, locator));
    }

    private CommonActions () {
        // Utility class
    }
}