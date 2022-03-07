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

import static com.github.wasiqb.boyka.actions.CommonActions.verifyElementBooleanAttribute;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.BooleanSubject;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

/**
 * Verification class for verifying the UI element attributes.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public final class VerifyElementActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Verify if element is displayed.
     *
     * @param locator locator of element
     *
     * @return {@link BooleanSubject} to verify the result
     */
    public static BooleanSubject verifyElementDisplayed (final Locator locator) {
        LOGGER.traceEntry ("Locator: {}", locator);
        return verifyElementBooleanAttribute (WebElement::isDisplayed, locator);
    }

    private VerifyElementActions () {
        // Utility class
    }
}
