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

import static com.github.wasiqb.boyka.enums.Messages.APP_TYPE_NOT_SUPPORT_DRIVERS;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;

import java.util.List;

import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class ElementFinder {
    public static WebElement find (final Locator locator) {
        return finds (locator).get (0);
    }

    public static List<WebElement> finds (final Locator locator) {
        final var driver = getSession ().getDriver ();
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
