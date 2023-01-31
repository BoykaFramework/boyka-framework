package com.github.wasiqb.boyka.testng.ui.wdio;

import static com.github.wasiqb.boyka.actions.DriverActions.saveLogs;
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.actions.ElementActions.tapOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.wdio.pages.DragDropPage.dragDropPage;
import static com.github.wasiqb.boyka.testng.ui.wdio.pages.WDIOHomePage.wdioHomePage;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for testing different Mobile gestures.
 *
 * @author Wasiq Bhamla
 * @since 24-Dec-2022
 */
public class WdioDemoTest {
    /**
     * Setup test method to take screenshot after each test method.
     */
    @AfterMethod (alwaysRun = true)
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            takeScreenshot ();
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
     * Test Drag drop
     */
    @Test
    public void testDragDrop () {
        tapOn (wdioHomePage ().getDragTab ());
        for (var index = 1; index <= 3; index++) {
            swipe ().dragTo (dragDropPage ().leftSourceTile (index), dragDropPage ().leftTargetTile (index));
            swipe ().dragTo (dragDropPage ().centerSourceTile (index), dragDropPage ().centerTargetTile (index));
            swipe ().dragTo (dragDropPage ().rightSourceTile (index), dragDropPage ().rightTargetTile (index));
        }
        verifyTextOf (dragDropPage ().getTitle ()).isEqualTo ("Congratulations");
        verifyTextOf (dragDropPage ().getDescription ()).isEqualTo (
            "You made it, click retry if you want to try it again.");
    }
}
