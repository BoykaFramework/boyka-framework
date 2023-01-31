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

package com.github.wasiqb.boyka.testng.ui.mydemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Checkout page.
 *
 * @author Wasiq Bhamla
 * @since 13-Mar-2022
 */
@Getter
public class CheckoutPage {
    private static final CheckoutPage CHECKOUT_PAGE = new CheckoutPage ();

    /**
     * Gets checkout page instance.
     *
     * @return Checkout page instance.
     */
    public static CheckoutPage checkoutPage () {
        return CHECKOUT_PAGE;
    }

    private final Locator address1 = buildLocator ().android (accessibilityId ("Address Line 1* input field"))
        .ios (accessibilityId ("Address Line 1* input field"))
        .name ("Address 1")
        .build ();
    private final Locator address2 = buildLocator ().android (accessibilityId ("Address Line 2 input field"))
        .ios (accessibilityId ("Address Line 2 input field"))
        .name ("Address 2")
        .build ();
    private final Locator city     = buildLocator ().android (accessibilityId ("City* input field"))
        .ios (accessibilityId ("City* input field"))
        .name ("City")
        .build ();
    private final Locator country  = buildLocator ().android (accessibilityId ("Country* input field"))
        .ios (accessibilityId ("Country* input field"))
        .name ("Country")
        .build ();
    private final Locator fullName = buildLocator ().android (accessibilityId ("Full Name* input field"))
        .ios (accessibilityId ("Full Name* input field"))
        .name ("Full Name")
        .build ();
    private final Locator payment  = buildLocator ().android (accessibilityId ("To Payment button"))
        .ios (accessibilityId ("To Payment button"))
        .name ("Payment")
        .build ();
    private final Locator state    = buildLocator ().android (accessibilityId ("State/Region input field"))
        .ios (accessibilityId ("State/Region input field"))
        .name ("State")
        .build ();
    private final Locator zipCode  = buildLocator ().android (accessibilityId ("Zip Code* input field"))
        .ios (accessibilityId ("Zip Code* input field"))
        .name ("Zip Code")
        .build ();

    private CheckoutPage () {
        // Avoid explicit page initialization.
    }
}
