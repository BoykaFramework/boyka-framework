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
import static com.github.wasiqb.boyka.actions.ElementFinder.find;
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
        LOGGER.info ("Click and hold on element: {}", locator);
        new Actions (getSession ().getDriver ()).clickAndHold (find (locator, CLICKABLE))
            .perform ();
        LOGGER.traceExit ();
    }

    /**
     * Click on element
     *
     * @param locator {@link Locator} of element
     */
    public static void clickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", locator);
        performElementAction (WebElement::click, locator);
        LOGGER.traceExit ();
    }

    /**
     * DoubleClick on element
     *
     * @param locator {@link Locator} of element
     */
    public static void doubleClickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", locator);
        new Actions (getSession ().getDriver ()).doubleClick (find (locator, CLICKABLE))
            .perform ();
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
        LOGGER.info ("Drag and Drop on element: {} , {}", source, destination);
        new Actions (getSession ().getDriver ()).dragAndDrop (find (source, CLICKABLE), find (destination, CLICKABLE))
            .perform ();
        LOGGER.traceExit ();
    }

    /**
     * Hover on element
     *
     * @param locator {@link Locator} of element
     */
    public static void hoverOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", locator);
        new Actions (getSession ().getDriver ()).moveToElement (find (locator, CLICKABLE))
            .perform ();
        LOGGER.traceExit ();
    }

    /**
     * RightClick on element
     *
     * @param locator {@link Locator} of element
     */
    public static void rightClickOn (final Locator locator) {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", locator);
        new Actions (getSession ().getDriver ()).contextClick (find (locator, CLICKABLE))
            .perform ();
        LOGGER.traceExit ();
    }

    private MouseActions () {
        // Utility class
    }
}
