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

import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;
import static com.github.wasiqb.boyka.actions.ElementActions.tapOn;
import static com.github.wasiqb.boyka.actions.ElementFinder.find;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.enums.WaitStrategy.CLICKABLE;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Perform Mouse actions on UI elements.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class MouseActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * LongPress on element
     *
     * @param locator {@link Locator} of element
     */
    public static void clickAndHold (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Click and hold on element: {}", locator.getName ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.clickAndHold (element)
                .perform ();
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * Click on element
     *
     * @param locator {@link Locator} of element
     */
    public static void clickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", locator.getName ());
        if (getSession ().getPlatformType () == WEB) {
            performElementAction (WebElement::click, locator);
        } else {
            tapOn (locator);
        }
        LOGGER.traceExit ();
    }

    /**
     * DoubleClick on element
     *
     * @param locator {@link Locator} of element
     */
    public static void doubleClickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", locator.getName ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.doubleClick (element)
                .perform ();
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * DragAndDrop on element
     *
     * @param source {@link Locator} of element
     * @param destination {@link Locator} of element
     */
    public static void dragDropTo (final Locator source, final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Drag and Drop on element: {} , {}", source.getName (), destination.getName ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.dragAndDrop (element, find (destination, CLICKABLE))
                .perform ();
        }, source);
        LOGGER.traceExit ();
    }

    /**
     * Hover on element
     *
     * @param locator {@link Locator} of element
     */
    public static void hoverOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", locator.getName ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.moveToElement (element)
                .perform ();
        }, locator);
        LOGGER.traceExit ();
    }

    /**
     * RightClick on element
     *
     * @param locator {@link Locator} of element
     */
    public static void rightClickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", locator.getName ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.contextClick (element)
                .perform ();
        }, locator);
        LOGGER.traceExit ();
    }

    private MouseActions () {
        // Utility class
    }
}
