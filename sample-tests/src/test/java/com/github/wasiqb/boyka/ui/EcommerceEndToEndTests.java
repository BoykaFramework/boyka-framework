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

package com.github.wasiqb.boyka.ui;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.ui.actions.CheckoutPageActions.checkoutPageActions;
import static com.github.wasiqb.boyka.ui.actions.ConfirmOrderPageActions.confirmOrderPageActions;
import static com.github.wasiqb.boyka.ui.actions.HomePageActions.homePageActions;
import static com.github.wasiqb.boyka.ui.actions.OrderSuccessPageActions.orderSuccessPageActions;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.ui.data.BillingData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * End to End tests for LambdaTest Ecommerce Playground website.
 *
 * @author Faisal Khatri
 * @since 8/4/2022
 **/
public class EcommerceEndToEndTests {
    private BillingData billingData;
    private String      unitPriceOfCameraLens;

    @BeforeClass (description = "Setup test class")
    @Parameters ({ "driverKey" })
    public void setupTestClass (final String driverKey) {
        final String url = "https://ecommerce-playground.lambdatest.io/";
        createDriver (PlatformType.WEB, driverKey);
        this.billingData = TestDataBuilder.getBillingData ();
        navigateTo (url);
    }

    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        closeDriver ();
    }

    @Test (dependsOnMethods = "testRegisterUser")
    public void testAddProductToCart () {
        try {
            homePageActions ().shopByCategory ("Components")
                .addPalmTreoCameraLensToCart ()
                .verifySuccessMessage ()
                .checkoutProduct ();
        } finally {
            takeScreenshot ();
        }
    }

    @Test (dependsOnMethods = "testAddProductToCart")
    public void testCheckoutProduct () {
        this.unitPriceOfCameraLens = checkoutPageActions ().textOfUnitPriceOfCameraLens ();
        System.out.println ("Unit price of Camera Lens is: " + this.unitPriceOfCameraLens);
        checkoutPageActions ().setBillingAddress (this.billingData)
            .checkoutProduct ();
    }

    @Test (dependsOnMethods = "testCheckoutProduct")
    public void testConfirmOrder () throws InterruptedException {
        confirmOrderPageActions ().verifyPageHeader ()
            .verifyProductName ()
            .verifyUnitPrice (this.unitPriceOfCameraLens)
            .verifyShippingAddress (this.billingData)
            .confirmOrder ();
    }

    @Test (dependsOnMethods = "testConfirmOrder")
    public void testOrderSuccess () {
        orderSuccessPageActions ().verifySuccessMessage ()
            .continueToHomePage ();
    }

    @Test (description = "Test login functionality")
    public void testRegisterUser () {
        homePageActions ().openUserRegistrationPage ()
            .verifyPageHeader ()
            .registerUser ()
            .verifySuccessfulRegistration ()
            .continueToMyAccount ()
            .verifyPageHeader ();
    }
}
