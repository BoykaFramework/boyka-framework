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

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class PaymentPage {
    private static final PaymentPage PAYMENT_PAGE = new PaymentPage ();

    public static PaymentPage paymentPage () {
        return PAYMENT_PAGE;
    }

    private final Locator cardNumber   = buildLocator ().android (accessibilityId ("Card Number* input field"))
        .ios (accessibilityId ("Card Number* input field"))
        .name ("Card Number")
        .build ();
    private final Locator expiryDate   = buildLocator ().android (accessibilityId ("Expiration Date* input field"))
        .ios (accessibilityId ("Expiration Date* input field"))
        .name ("Expiry Date")
        .build ();
    private final Locator fullName     = buildLocator ().android (accessibilityId ("Full Name* input field"))
        .ios (accessibilityId ("Full Name* input field"))
        .name ("Full Name")
        .build ();
    private final Locator reviewOrder  = buildLocator ().android (accessibilityId ("Review Order button"))
        .ios (accessibilityId ("Review Order button"))
        .name ("Review Order")
        .build ();
    private final Locator securityCode = buildLocator ().android (accessibilityId ("Security Code* input field"))
        .ios (accessibilityId ("Security Code* input field"))
        .name ("Security Code")
        .build ();

    private PaymentPage () {
        // Utility class.
    }
}
