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

import static com.github.wasiqb.boyka.actions.DriverActions.executeScript;
import static com.github.wasiqb.boyka.actions.DriverActions.pause;
import static com.github.wasiqb.boyka.actions.ElementFinder.find;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.enums.WaitStrategy.CLICKABLE;
import static com.github.wasiqb.boyka.enums.WaitStrategy.VISIBLE;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofMillis;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.wasiqb.boyka.builders.Locator;
import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Sequence;

/**
 * Common action methods to perform different actions on devices / browsers and on elements.
 *
 * @author Wasiq Bhamla
 * @since 25-Feb-2022
 */
@SuppressWarnings ("unchecked")
final class CommonActions {
    private static final String HIGHLIGHT_STYLE = "highlight.style";
    private static final Logger LOGGER          = getLogger ();

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
        final var element = find (locator, VISIBLE);
        highlight ("green", element);
        unhighlight (element);
        return LOGGER.traceExit (action.apply (element));
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
        final var element = find (locator, CLICKABLE);
        prepareElementAction (element);
        action.accept (element);
        LOGGER.traceExit ();
    }

    /**
     * Perform element specific action with Driver.
     *
     * @param action action to perform
     * @param locator locator to find element
     */
    public static <D extends WebDriver> void performElementAction (final BiConsumer<D, WebElement> action,
        final Locator locator) {
        LOGGER.traceEntry ();
        final var element = find (locator, CLICKABLE);
        prepareElementAction (element);
        action.accept ((D) getSession ().getDriver (), element);
        LOGGER.traceExit ();
    }

    /**
     * Perform Gestures on Mobile.
     *
     * @param sequences Collection of Sequences of gestures.
     */
    public static void performMobileGestures (final Collection<Sequence> sequences) {
        performDriverAction (driver -> ((AppiumDriver) driver).perform (sequences));
    }

    private static void highlight (final String color, final WebElement element) {
        if (getSession ().getPlatformType () == WEB && getSession ().getWebSetting ()
            .isHighlight ()) {
            final var style = element.getAttribute ("style");
            getSession ().setSharedData (HIGHLIGHT_STYLE, style);
            executeScript ("arguments[0].setAttribute('style', arguments[1] + arguments[2]);", element, style,
                format ("color: {0}; border: 3px solid {0};", color));
            pause (ofMillis (getSession ().getSetting ()
                .getUi ()
                .getTimeout ()
                .getHighlightDelay ()));
        }
    }

    private static void prepareElementAction (final WebElement element) {
        highlight ("red", element);
        unhighlight (element);
    }

    private static void unhighlight (final WebElement element) {
        if (getSession ().getPlatformType () == WEB && getSession ().getWebSetting ()
            .isHighlight ()) {
            final var style = getSession ().getSharedData (HIGHLIGHT_STYLE);
            executeScript ("arguments[0].setAttribute('style', arguments[1]);", element, style);
            getSession ().removeSharedData (HIGHLIGHT_STYLE);
        }
    }

    private CommonActions () {
        // Utility class
    }
}

