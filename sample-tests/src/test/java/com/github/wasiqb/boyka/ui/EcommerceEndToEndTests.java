package com.github.wasiqb.boyka.ui;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.ui.actions.HomePageActions.homePageActions;

import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.ui.data.BillingData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
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
