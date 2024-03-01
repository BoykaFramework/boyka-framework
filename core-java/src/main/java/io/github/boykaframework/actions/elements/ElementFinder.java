/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

import static io.github.boykaframework.enums.Message.ELEMENT_NOT_FOUND;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.util.List;

import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.WaitStrategy;
import io.github.boykaframework.exception.FrameworkError;
import io.github.boykaframework.utils.ErrorHandler;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
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
     * @param waitStrategy {@link WaitStrategy} for finding the element
     *
     * @return {@link WebElement}
     */
    public static WebElement find (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var elements = finds (locator, waitStrategy);
        if (elements.isEmpty ()) {
            ErrorHandler.throwError (ELEMENT_NOT_FOUND, locator.getName (), getSession ().getPlatformType ());
        }
        if (locator.getFilter () != null) {
            return elements.stream ()
                .filter (locator.getFilter ())
                .findFirst ()
                .orElseThrow (() -> new FrameworkError (format (ELEMENT_NOT_FOUND.getMessageText (), locator.getName (),
                    getSession ().getPlatformType ())));
        }
        return elements.get (locator.getIndex ());
    }

    /**
     * Find all elements on UI.
     *
     * @param locator {@link Locator} to find elements
     * @param waitStrategy {@link WaitStrategy} for finding the element
     *
     * @return {@link List} of {@link WebElement}
     */
    public static List<WebElement> finds (final Locator locator, final WaitStrategy waitStrategy) {
        LOGGER.traceEntry ();
        final var driver = getSession ().getDriver ();
        final List<WebElement> elements;
        if (locator.getParent () != null) {
            final var parent = find (locator.getParent (), waitStrategy);
            elements = finds (driver, parent, locator);
        } else {
            waitForElement (locator, waitStrategy);
            elements = finds (driver, locator);
        }
        return LOGGER.traceExit (elements);
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final WebElement parent,
        final Locator locator) {
        LOGGER.traceEntry ();
        final var platformLocator = locator.getLocator ();
        if (platformLocator == null) {
            ErrorHandler.throwError (ELEMENT_NOT_FOUND, locator.getName (), getSession ().getPlatformType ());
        }
        return LOGGER.traceExit (
            parent != null ? parent.findElements (locator.getLocator ()) : driver.findElements (locator.getLocator ()));
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final Locator locator) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (finds (driver, null, locator));
    }

    private static void waitForElement (final Locator locator, final WaitStrategy waitStrategy) {
        try {
            final var wait = getSession ().getWait ();
            if (requireNonNull (waitStrategy, "Wait Strategy is null") == WaitStrategy.CLICKABLE) {
                wait.until (elementToBeClickable (locator.getLocator ()));
            } else {
                wait.until (visibilityOfElementLocated (locator.getLocator ()));
            }
        } catch (final TimeoutException e) {
            ErrorHandler.handleAndThrow (ELEMENT_NOT_FOUND, e, locator.getName (), getSession ().getPlatformType ());
        }
    }

    private ElementFinder () {
        // Intentionally left blank
    }
}
