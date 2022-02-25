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
import static com.google.common.truth.Truth.assertThat;

import java.util.function.Predicate;

import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.BooleanSubject;
import org.openqa.selenium.WebElement;

public final class VerifyAction {
    public static void verifyElementDisplayed (final Locator locator) {
        verifyElementBooleanAttribute (WebElement::isDisplayed, locator).isTrue ();
    }

    public static void verifyElementNotDisplayed (final Locator locator) {
        verifyElementBooleanAttribute (WebElement::isDisplayed, locator).isFalse ();
    }

    private static BooleanSubject verifyElementBooleanAttribute (final Predicate<WebElement> actual,
        final Locator locator) {
        return assertThat (getElementAttribute (actual::test, locator));
    }

    private VerifyAction () {
        // Utility class
    }
}
