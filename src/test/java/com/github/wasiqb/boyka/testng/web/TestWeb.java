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

package com.github.wasiqb.boyka.testng.web;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.CartPage.cartPage;
import static com.github.wasiqb.boyka.testng.web.pages.CheckoutPage.checkoutPage;
import static com.github.wasiqb.boyka.testng.web.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.web.pages.LoginPage.loginPage;
import static com.github.wasiqb.boyka.testng.web.pages.ProductDetailsPage.productDetailsPage;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.enums.ApplicationType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class to test web application.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public class TestWeb {
    private static final String URL = "https://www.saucedemo.com";

    /**
     * Setup test class by initialising driver.
     *
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupTestClass (final ApplicationType appType, final String driverKey) {
        createDriver (appType, driverKey);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        closeDriver ();
    }

    /**
     * Test add to cart functionality.
     */
    @Test (description = "Test adding a product to cart", priority = 2)
    public void testAddToCart () {
        verifyElementDisplayed (homePage ().getProductTitle ()).isTrue ();
        verifyElementDisplayed (homePage ().getProductDescription ()).isTrue ();
        clickOn (homePage ().getAddToCartButton ());

        verifyTextOf (homePage ().getProductPrice ()).isEqualTo ("$29.99");
        verifyTextOf (homePage ().getShoppingCartCount ()).isEqualTo ("1");
    }

    /**
     * Test checkout page step 1.
     */
    @Test (description = "Test checkout page step 1.", priority = 5)
    public void testCheckoutStep1 () {
        clickOn (cartPage ().getCheckout ());

        verifyBrowserUrl ().isEqualTo (format ("{0}/checkout-step-one.html", URL));
        verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: YOUR INFORMATION");
        enterText (checkoutPage ().getFirstName (), "Wasiq");
        enterText (checkoutPage ().getLastName (), "Bhamla");
        enterText (checkoutPage ().getZipCode (), "12345");
        clickOn (checkoutPage ().getContinueButton ());

        verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: OVERVIEW");
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test checkout page step 2.", priority = 6)
    public void testCheckoutStep2 () {
        clickOn (checkoutPage ().getFinish ());

        verifyBrowserUrl ().isEqualTo (format ("{0}/checkout-complete.html", URL));
        verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: COMPLETE!");
        verifyTextOf (checkoutPage ().getCompleteHeader ()).isEqualTo ("THANK YOU FOR YOUR ORDER");
        verifyTextOf (checkoutPage ().getCompleteText ()).isEqualTo (
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test login functionality", priority = 1)
    public void testLogin () {
        navigateTo (URL);
        verifyBrowserUrl ().startsWith (URL);
        enterText (loginPage ().getUsername (), "standard_user");
        enterText (loginPage ().getPassword (), "secret_sauce");
        clickOn (loginPage ().getLoginButton ());
        verifyBrowserUrl ().isEqualTo (format ("{0}/inventory.html", URL));
        verifyBrowserTitle ().isEqualTo ("Swag Labs");
        verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
        verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
    }

    /**
     * Test product cart page.
     */
    @Test (description = "Test product cart page", priority = 4)
    public void testProductCartPage () {
        clickOn (homePage ().getShoppingCart ());

        verifyBrowserUrl ().isEqualTo (format ("{0}/cart.html", URL));
        verifyElementDisplayed (cartPage ().getCheckout ()).isTrue ();
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test product details page", priority = 3)
    public void testProductDetailsPage () {
        clickOn (homePage ().productItem ("Sauce Labs Backpack"));

        verifyBrowserUrl ().startsWith (format ("{0}/inventory-item.html?id=", URL));
        verifyElementDisplayed (productDetailsPage ().getContainer ()).isTrue ();
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test Sign out.", priority = 7)
    public void testSignOut () {
        clickOn (homePage ().getMenuButton ());
        clickOn (homePage ().getLogout ());

        verifyBrowserUrl ().startsWith (URL);
        verifyElementDisplayed (loginPage ().getUsername ()).isTrue ();
    }
}