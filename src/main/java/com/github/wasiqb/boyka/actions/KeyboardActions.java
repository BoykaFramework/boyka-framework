package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.ElementFinder.find;

import com.github.wasiqb.boyka.pages.Locator;

public final class KeyboardActions {
    public static void enterText (final Locator element, final String text) {
        find (element).sendKeys (text);
    }

    private KeyboardActions () {
        // Utility class
    }
}
