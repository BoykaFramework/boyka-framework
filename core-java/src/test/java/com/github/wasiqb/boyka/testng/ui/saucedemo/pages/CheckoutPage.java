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

package com.github.wasiqb.boyka.testng.ui.saucedemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

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

    private final Locator completeHeader = buildLocator ().web (cssSelector ("h2.complete-header"))
        .android (androidUIAutomator ("new UiSelector().textContains(\"THANK YOU\")"))
        .name ("Complete Header")
        .build ();
    private final Locator completeText   = buildLocator ().web (cssSelector ("div.complete-text"))
        .android (androidUIAutomator ("new UiSelector().textContains(\"Your order has been dispatched\")"))
        .name ("Complete Text")
        .build ();
    private final Locator continueButton = buildLocator ().web (id ("continue"))
        .android (accessibilityId ("test-CONTINUE"))
        .name ("Continue Button")
        .build ();
    private final Locator finish         = buildLocator ().web (id ("finish"))
        .android (accessibilityId ("test-FINISH"))
        .name ("Finish")
        .build ();
    private final Locator firstName      = buildLocator ().web (id ("first-name"))
        .android (accessibilityId ("test-First Name"))
        .name ("First Name")
        .build ();
    private final Locator lastName       = buildLocator ().web (id ("last-name"))
        .android (accessibilityId ("test-Last Name"))
        .name ("Last Name")
        .build ();
    private final Locator title          = buildLocator ().web (cssSelector ("span.title"))
        .android (androidUIAutomator ("new UiSelector().text(\"CHECKOUT: INFORMATION\")"))
        .name ("Title")
        .build ();
    private final Locator zipCode        = buildLocator ().web (id ("postal-code"))
        .android (accessibilityId ("test-Zip/Postal Code"))
        .name ("Zip Code")
        .build ();

    private CheckoutPage () {
        // Avoid explicit page initialization.
    }
}
