/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.ui.saucedemo.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.iOSClassChain;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import io.github.boykaframework.builders.Locator;
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

    private final Locator completeHeader = Locator.buildLocator ()
        .web (cssSelector ("h2.complete-header"))
        .android (androidUIAutomator ("new UiSelector().textContains(\"THANK YOU\")"))
        .ios (iOSClassChain ("**/XCUIElementTypeStaticText[`label BEGINSWITH \"THANK YOU\"`]"))
        .name ("Complete Header")
        .build ();
    private final Locator completeText   = Locator.buildLocator ()
        .web (cssSelector ("div.complete-text"))
        .android (androidUIAutomator ("new UiSelector().textContains(\"Your order has been dispatched\")"))
        .ios (iOSClassChain ("**/XCUIElementTypeStaticText[`label BEGINSWITH \"Your order has been dispatched\"`]"))
        .name ("Complete Text")
        .build ();
    private final Locator continueButton = Locator.buildLocator ()
        .web (id ("continue"))
        .android (accessibilityId ("test-CONTINUE"))
        .ios (accessibilityId ("test-CONTINUE"))
        .name ("Continue Button")
        .build ();
    private final Locator finish         = Locator.buildLocator ()
        .web (id ("finish"))
        .android (androidUIAutomator (
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(new UiSelector().description(\"test-FINISH\"))"))
        .ios (accessibilityId ("test-FINISH"))
        .name ("Finish")
        .build ();
    private final Locator firstName      = Locator.buildLocator ()
        .web (id ("first-name"))
        .android (accessibilityId ("test-First Name"))
        .ios (accessibilityId ("test-First Name"))
        .name ("First Name")
        .build ();
    private final Locator lastName       = Locator.buildLocator ()
        .web (id ("last-name"))
        .android (accessibilityId ("test-Last Name"))
        .ios (accessibilityId ("test-Last Name"))
        .name ("Last Name")
        .build ();
    private final Locator title          = Locator.buildLocator ()
        .web (cssSelector ("span.title"))
        .android (androidUIAutomator ("new UiSelector().textContains(\"CHECKOUT:\")"))
        .ios (iOSClassChain ("**/XCUIElementTypeStaticText[`label BEGINSWITH \"CHECKOUT:\"`]"))
        .name ("Title")
        .build ();
    private final Locator zipCode        = Locator.buildLocator ()
        .web (id ("postal-code"))
        .android (accessibilityId ("test-Zip/Postal Code"))
        .ios (accessibilityId ("test-Zip/Postal Code"))
        .name ("Zip Code")
        .build ();

    private CheckoutPage () {
        // Avoid explicit page initialization.
    }
}
