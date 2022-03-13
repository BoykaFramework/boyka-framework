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
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Perform Mouse actions on UI elements.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class MouseActions {
    private static final Logger LOGGER = getLogger ();

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

    private MouseActions () {
        // Utility class
    }
}
