package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.enums.Messages.APP_TYPE_NOT_SUPPORT_DRIVERS;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getDriver;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;

import java.util.List;

import com.github.wasiqb.boyka.exception.FrameworkError;
import com.github.wasiqb.boyka.pages.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ElementFinder {
    public static WebElement find (final Locator locator) {
        return finds (locator).get (0);
    }

    public static List<WebElement> finds (final Locator locator) {
        final var driver = getDriver ();
        final List<WebElement> element;
        if (locator.getParent () != null) {
            final var parent = find (locator.getParent ());
            element = finds (driver, parent, locator);
        } else {
            element = finds (driver, locator);
        }
        return element;
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final Locator locator) {
        return finds (driver, null, locator);
    }

    private static <D extends WebDriver> List<WebElement> finds (final D driver, final WebElement parent,
        final Locator locator) {
        switch (getSession ().getApplicationType ()) {
            case ANDROID:
                return parent != null
                       ? parent.findElements (locator.getAndroid ())
                       : driver.findElements (locator.getAndroid ());
            case IOS:
                return parent != null
                       ? parent.findElements (locator.getIos ())
                       : driver.findElements (locator.getIos ());
            case WEB:
                return parent != null
                       ? parent.findElements (locator.getWeb ())
                       : driver.findElements (locator.getWeb ());
            case API:
            default:
                throw new FrameworkError (APP_TYPE_NOT_SUPPORT_DRIVERS.getMessage ());
        }
    }

    private ElementFinder () {
        // Intentionally left blank
    }
}
