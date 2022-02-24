package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.ElementFinder.find;
import static com.google.common.truth.Truth.assertThat;

import java.util.function.Predicate;

import com.github.wasiqb.boyka.pages.Locator;
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
        return assertThat (actual.test (find (locator)));
    }

    private VerifyAction () {
        // Utility class
    }
}
