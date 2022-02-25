package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.ElementFinder.find;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;

import java.util.function.Consumer;
import java.util.function.Function;

import com.github.wasiqb.boyka.pages.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@SuppressWarnings ("unchecked")
final class CommonActions {
    public static <D extends WebDriver, E> E getDriverAttribute (final Function<D, E> action) {
        return action.apply ((D) getSession ().getDriver ());
    }

    public static <E> E getElementAttribute (final Function<WebElement, E> action, final Locator locator) {
        return action.apply (find (locator));
    }

    public static <D extends WebDriver> void performDriverAction (final Consumer<D> action) {
        action.accept ((D) getSession ().getDriver ());
    }

    public static void performElementAction (final Consumer<WebElement> action, final Locator locator) {
        action.accept (find (locator));
    }

    private CommonActions () {
        // Utility class
    }
}