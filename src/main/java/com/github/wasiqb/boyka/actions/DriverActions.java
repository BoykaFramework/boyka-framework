package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;

import org.openqa.selenium.WebDriver;

public final class DriverActions {
    public static void navigateTo (final String url) {
        performDriverAction (driver -> driver.get (url));
    }

    public static String title () {
        return getDriverAttribute (WebDriver::getTitle);
    }

    private DriverActions () {
        // Utility class.
    }
}
