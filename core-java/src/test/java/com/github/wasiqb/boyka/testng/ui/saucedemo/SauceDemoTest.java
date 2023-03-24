package com.github.wasiqb.boyka.testng.ui.saucedemo;

import static com.github.wasiqb.boyka.actions.drivers.ContextActions.withContext;
import static com.github.wasiqb.boyka.actions.drivers.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearAllSessions;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.exception.FrameworkError;
import com.github.wasiqb.boyka.testng.ui.saucedemo.actions.SauceDemoActions;
import org.testng.ITestResult;
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
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            onWindow ().takeScreenshot ();
        }
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
        createSession (platformType, driverKey);
        this.sauceDemo = new SauceDemoActions ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class", alwaysRun = true)
    public void tearDownTestClass () {
        withDriver ().saveLogs ();
        clearAllSessions ();
    }

    /**
     * Test add to cart functionality.
     */
    @Test (description = "Test adding a product to cart", dependsOnMethods = "testLogin")
    public void testAddToCart () {
        this.sauceDemo.verifyAddToCart ();
    }

    /**
     * Test checkout page step 1.
     */
    @Test (description = "Test checkout page step 1.", dependsOnMethods = "testAddToCart")
    public void testCheckoutStep1 () {
        this.sauceDemo.verifyCheckoutStep1 ();
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test checkout page step 2.", dependsOnMethods = "testCheckoutStep1")
    public void testCheckoutStep2 () {
        this.sauceDemo.verifyCheckoutStep2 ();
    }

    /**
     * Test context switching in Native app
     */
    @Test (description = "Test context switching in Native app", dependsOnMethods = "testSignOut", expectedExceptions = FrameworkError.class)
    public void testContextSwitching () {
        withContext ().switchToWebView ("WEBVIEW");
    }

    /**
     * Test login functionality.
     */
    @Test (description = "Test login functionality")
    public void testLogin () {
        this.sauceDemo.verifyLogin ("standard_user", "secret_sauce");
    }

    /**
     * Test checkout page step 2.
     */
    @Test (description = "Test Sign out.", dependsOnMethods = "testCheckoutStep2")
    public void testSignOut () {
        this.sauceDemo.verifySignOut ();
    }
}
