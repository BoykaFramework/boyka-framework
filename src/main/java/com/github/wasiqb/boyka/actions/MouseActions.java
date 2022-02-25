package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;

import com.github.wasiqb.boyka.pages.Locator;
import org.openqa.selenium.WebElement;

public final class MouseActions {
    public static void clickOn (final Locator locator) {
        performElementAction (WebElement::click, locator);
    }

    private MouseActions () {
        // Utility class
    }
}
