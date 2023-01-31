/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.ui.mydemo.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class OrderSuccessPage {
    private static final OrderSuccessPage ORDER_SUCCESS_PAGE = new OrderSuccessPage ();

    public static OrderSuccessPage orderSuccessPage () {
        return ORDER_SUCCESS_PAGE;
    }

    private final Locator continueShopping = Locator.buildLocator ()
        .android (accessibilityId ("Continue Shopping button"))
        .ios (accessibilityId ("Continue Shopping button"))
        .name ("Continue Shopping")
        .build ();

    private OrderSuccessPage () {
        // Utility class.
    }

    public Locator getMessageMatching (final String text) {
        return Locator.buildLocator ()
            .android (androidUIAutomator (format ("new UiSelector().textContains(\"{0}\")", text)))
            .ios (iOSNsPredicateString (
                format ("label CONTAINS \"{0}\" AND type == \"XCUIElementTypeStaticText\"", text)))
            .name (text)
            .build ();
    }
}
