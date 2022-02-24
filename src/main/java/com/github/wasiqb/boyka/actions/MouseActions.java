package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.ElementFinder.find;

import com.github.wasiqb.boyka.pages.Locator;

public final class MouseActions {
    public static void clickOn (final Locator locator) {
        find (locator).click ();
    }

    private MouseActions () {
        // Utility class
    }
}
