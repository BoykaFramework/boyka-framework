package com.github.wasiqb.boyka.ui;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.ui.actions.CheckoutPageActions.checkoutPageActions;
import static com.github.wasiqb.boyka.ui.actions.ConfirmOrderPageActions.confirmOrderPageActions;
import static com.github.wasiqb.boyka.ui.actions.HomePageActions.homePageActions;
import static com.github.wasiqb.boyka.ui.actions.OrderSuccessPageActions.orderSuccessPageActions;

import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.ui.data.BillingData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * End to End tests for LambdaTest Ecommerce Playground website.
 * @author Faisal Khatri
 * @since 8/4/2022
 **/
public class EcommerceEndToEndTests {
    private BillingData billingData;
    private String      unitPriceOfCameraLens;

    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        final String url = "https://ecommerce-playground.lambdatest.io/";
        createDriver (ApplicationType.WEB, "test_local_chrome");
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
