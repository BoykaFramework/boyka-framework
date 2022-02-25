package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.performElementAction;

import com.github.wasiqb.boyka.pages.Locator;

public final class KeyboardActions {
    public static void enterText (final Locator locator, final String text) {
        performElementAction (e -> e.sendKeys (text), locator);
    }

    private KeyboardActions () {
        // Utility class
    }
}
