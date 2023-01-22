package com.github.wasiqb.boyka.testng.ui.mydemo;

import static com.github.wasiqb.boyka.actions.DriverActions.saveLogs;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.mydemo.actions.SauceDemoActions;
import com.github.wasiqb.boyka.testng.ui.mydemo.pages.ProductDetailsPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Sauce Demo Test class
 *
 * @author Wasiq Bhamla
 * @since 18-Dec-2022
 */
public class SauceDemoTest {
    private SauceDemoActions sauceDemo;

    /**
     * Setup test method to take screenshot after each test method.
     */
    @AfterMethod (alwaysRun = true)
    public void afterMethod () {
        takeScreenshot ();
    }

    /**
     * Setup test class by initializing driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class", alwaysRun = true)
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String driverKey) {
        createDriver (platformType, driverKey);
        this.sauceDemo = new SauceDemoActions ();
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
     * Test login functionality.
     */
    @Test (description = "Test Add to cart")
    public void testAddToCart () {
        this.sauceDemo.verifyAddToCart ("Sauce Labs Backpack", 2, ProductDetailsPage.Color.RED);
    }

    /**
     * Test add to cart functionality.
     */
    @Test (description = "Test cart page", dependsOnMethods = "testAddToCart")
    public void testCartPage () {
        this.sauceDemo.verifyCartPage ();
    }

    /**
     * Test checkout page step 1.
     */
    @Test (description = "Test checkout page", dependsOnMethods = "testLogin")
    public void testCheckoutPage () {
        this.sauceDemo.verifyCheckout ();
    }

    @Test (description = "Test login page", dependsOnMethods = "testCartPage")
    public void testLogin () {
        this.sauceDemo.verifyLogin ("bob@example.com", "10203040");
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test order review page", dependsOnMethods = "testPaymentPage")
    public void testOrderReview () {
        this.sauceDemo.verifyOrderReview ();
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test payment page", dependsOnMethods = "testCheckoutPage")
    public void testPaymentPage () {
        this.sauceDemo.verifyPayment ();
    }
}
