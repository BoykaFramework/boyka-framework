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

package com.github.wasiqb.boyka.testng.ui.saucedemo.android;

import static com.github.wasiqb.boyka.actions.DriverActions.swipeUp;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.actions.ElementActions.tapOn;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.CartPage.cartPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.CheckoutPage.checkoutPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.LoginPage.loginPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.ProductDetailsPage.productDetailsPage;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class to test web application.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
public class SauceDemoTest {
    /**
     * Setup test method to take screenshot after each test method.
     */
    @AfterMethod
    public void afterMethod () {
        takeScreenshot ();
    }

    /**
     * Setup test class by initialising driver.
     *
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupTestClass (final PlatformType appType, final String driverKey) {
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
    @Test (description = "Test adding a product to cart", dependsOnMethods = "testLogin")
    public void testAddToCart () {
        swipeUp (50);

        //        verifyElementDisplayed (homePage ().getProductTitle ()).isTrue ();
        //        verifyElementDisplayed (homePage ().getProductDescription ()).isTrue ();
        //        verifyAttributeOf (homePage ().getAddToCartButton (), "data-test").isEqualTo (
        //            "add-to-cart-sauce-labs-backpack");
        //        clickOn (homePage ().getAddToCartButton ());
        //
        //        verifyTextOf (homePage ().getProductPrice ()).isEqualTo ("$29.99");
        //        verifyTextOf (homePage ().getShoppingCartCount ()).isEqualTo ("1");
    }

    /**
     * Test checkout page step 1.
     */
    @Ignore
    @Test (description = "Test checkout page step 1.", priority = 5)
    public void testCheckoutStep1 () {
        clickOn (cartPage ().getCheckout ());

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
    @Ignore
    @Test (description = "Test checkout page step 2.", priority = 6)
    public void testCheckoutStep2 () {
        clickOn (checkoutPage ().getFinish ());

        verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: COMPLETE!");
        verifyTextOf (checkoutPage ().getCompleteHeader ()).isEqualTo ("THANK YOU FOR YOUR ORDER");
        verifyTextOf (checkoutPage ().getCompleteText ()).isEqualTo (
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test login functionality")
    public void testLogin () {
        enterText (loginPage ().getUsername (), "standard_user");
        enterText (loginPage ().getPassword (), "secret_sauce");
        tapOn (loginPage ().getLoginButton ());

        verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
        verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
    }

    /**
     * Test product cart page.
     */
    @Ignore
    @Test (description = "Test product cart page", priority = 4)
    public void testProductCartPage () {
        clickOn (homePage ().getShoppingCart ());

        verifyElementDisplayed (cartPage ().getCheckout ()).isTrue ();
    }

    /**
     * Test login functionality.
     */
    @Ignore
    @Test (description = "Test product details page", priority = 3)
    public void testProductDetailsPage () {
        clickOn (homePage ().productItem ("Sauce Labs Backpack"));

        verifyElementDisplayed (productDetailsPage ().getContainer ()).isTrue ();
    }

    /**
     * Test checkout page step 2.
     */
    @Ignore
    @Test (description = "Test Sign out.", priority = 7)
    public void testSignOut () {
        clickOn (homePage ().getMenuButton ());
        clickOn (homePage ().getLogout ());

        verifyElementDisplayed (loginPage ().getUsername ()).isTrue ();
    }
}
