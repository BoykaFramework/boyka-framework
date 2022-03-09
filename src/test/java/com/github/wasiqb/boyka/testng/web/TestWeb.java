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
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.web.pages.LoginPage.loginPage;
import static com.github.wasiqb.boyka.testng.web.pages.ProductDetailsPage.productDetailsPage;
import static com.github.wasiqb.boyka.testng.web.pages.ProductPage.productPage;

import com.github.wasiqb.boyka.enums.ApplicationType;
import org.testng.Assert;
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

    @Test (description = "Test adding a product to cart", priority = 2)
    public void testAddToCart () {
        verifyElementDisplayed (productPage ().getSauceLabsBackPackTitle ()).isTrue ();
        verifyElementDisplayed (productPage ().getSauceLabsBackPackDesc ()).isTrue ();
        clickOn (productPage ().getSauceLabsBackPackAddToCardBtn ());

        Assert.assertEquals (productPage ().getSauceLabsBackPackPrice (), "$29.99");
        Assert.assertEquals (productPage ().getShoppingCart (), 1);
    }

    /**
     * Test login functionality.
     */
    @Test(description = "Test login functionality", priority = 1)
    public void testLogin() {
        navigateTo("https://www.saucedemo.com/");
        enterText(loginPage().getUsername(), "standard_user");
        enterText(loginPage().getPassword(), "secret_sauce");
        clickOn(loginPage().getLoginButton());
        verifyBrowserTitle().isEqualTo("Swag Labs");
        verifyElementDisplayed(homePage().getMenuButton()).isTrue();
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test login functionality")
    public void testProductDetails () {
        clickOn (homePage ().productItem ("Sauce Labs Backpack"));
        verifyElementDisplayed (productDetailsPage ().getContainer ()).isTrue ();
    }
}
