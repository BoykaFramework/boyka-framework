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

import static com.github.wasiqb.boyka.actions.DriverActions.saveLogs;
import static com.github.wasiqb.boyka.actions.DriverActions.swipeDown;
import static com.github.wasiqb.boyka.actions.DriverActions.swipeUp;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.actions.ElementActions.tapOn;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
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
    @AfterMethod (alwaysRun = true)
    public void afterMethod () {
        takeScreenshot ();
    }

    /**
     * Setup test class by initialising driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class", alwaysRun = true)
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String driverKey) {
        createDriver (platformType, driverKey);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class", alwaysRun = true)
    public void tearDownTestClass () {
        saveLogs ();
        closeDriver ();
    }

    /**
     * Test add to cart functionality.
     */
    @Test (description = "Test adding a product to cart", dependsOnMethods = "testSwipe")
    public void testAddToCart () {
        tapOn (homePage ().getViewToggle ());
        verifyElementDisplayed (homePage ().getProductTitle ()).isTrue ();
        tapOn (homePage ().getAddToCartButton ());

        verifyTextOf (homePage ().getProductPrice ()).isEqualTo ("$29.99");
        verifyTextOf (homePage ().getShoppingCartCount ()).isEqualTo ("1");
    }

    /**
     * Test checkout page step 1.
     */
    @Test (description = "Test checkout page step 1.", dependsOnMethods = "testProductCartPage")
    public void testCheckoutStep1 () {
        tapOn (cartPage ().getCheckout ());

        enterText (checkoutPage ().getFirstName (), "Wasiq");
        enterText (checkoutPage ().getLastName (), "Bhamla");
        enterText (checkoutPage ().getZipCode (), "12345");
        tapOn (checkoutPage ().getContinueButton ());
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test checkout page step 2.", dependsOnMethods = "testCheckoutStep1")
    public void testCheckoutStep2 () {
        swipeUp ();
        swipeUp ();
        tapOn (checkoutPage ().getFinish ());

        verifyTextOf (checkoutPage ().getCompleteHeader ()).isEqualTo ("THANK YOU FOR YOU ORDER");
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
    @Test (description = "Test product cart page", dependsOnMethods = "testProductDetailsPage")
    public void testProductCartPage () {
        tapOn (homePage ().getShoppingCart ());

        verifyElementDisplayed (cartPage ().getCheckout ()).isTrue ();
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test product details page", dependsOnMethods = "testAddToCart")
    public void testProductDetailsPage () {
        tapOn (homePage ().productItem ("Sauce Labs Backpack"));

        verifyElementDisplayed (productDetailsPage ().getContainer ()).isTrue ();
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test Sign out.", dependsOnMethods = "testCheckoutStep2")
    public void testSignOut () {
        tapOn (homePage ().getMenuButton ());
        tapOn (homePage ().getLogout ());

        verifyElementDisplayed (loginPage ().getUsername ()).isTrue ();
    }

    /**
     * Test Swipe up and down action.
     */
    @Test (description = "Test Swipe up and down action", dependsOnMethods = "testLogin")
    public void testSwipe () {
        swipeUp ();
        swipeDown ();
    }
}
