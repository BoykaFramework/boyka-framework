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

import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

import java.util.List;

import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.enums.WaitStrategy;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Finds element on UI.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class ElementFinder {
    private static final Logger LOGGER = getLogger ();

    /**
     * Find single element on UI.
     *
     * @param locator {@link Locator} to find element
     * @param waitStrategy {@link WaitStrategy} to wait for element
     *
     * @return {@link WebElement}
     */
    public static WebElement find (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ("locator: {}, waitUntil: {}", locator, waitStrategy);
        return LOGGER.traceExit (finds (locator, waitStrategy).get (0));
    }

    /**
     * Find all elements on UI.
     *
     * @param locator {@link Locator} to find elements
     * @param waitStrategy {@link WaitStrategy} to wait for element
     *
     * @return {@link List} of {@link WebElement}
     */
    public static List<WebElement> finds (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ("locator: {}, waitUntil: {}", locator, waitStrategy);
        final var driver = getSession ().getDriver ();
        final List<WebElement> element;
        if (locator.getParent () != null) {
            final var parent = find (locator.getParent (), waitStrategy);
            element = finds (driver, parent, locator, waitStrategy);
        } else {
            element = finds (driver, locator, waitStrategy);
        }
        return LOGGER.traceExit (element);
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final WebElement parent,
        final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var wait = getSession ().getWait ();
        switch (waitStrategy) {
            case CLICKABLE:
                wait.until (elementToBeClickable (locator.getLocator ()));
                break;
            case VISIBLE:
            default:
                wait.until (visibilityOfAllElementsLocatedBy (locator.getLocator ()));
        }
        return LOGGER.traceExit (
            parent != null ? parent.findElements (locator.getLocator ()) : driver.findElements (locator.getLocator ()));
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final Locator locator,
        final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (finds (driver, null, locator, waitStrategy));
    }

    private ElementFinder () {
        // Intentionally left blank
    }
}
