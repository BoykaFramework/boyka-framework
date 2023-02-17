package com.github.wasiqb.boyka.testng.ui.wdio;

import static com.github.wasiqb.boyka.actions.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.FingerActions.withFinger;
import static com.github.wasiqb.boyka.actions.WindowActions.onWindow;
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
        createDriver (platformType, driverKey);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class", alwaysRun = true)
    public void tearDownTestClass () {
        withDriver ().saveLogs ();
        closeDriver ();
    }

    /**
     * Test Drag drop
     */
    @Test
    public void testDragDrop () {
        withFinger (wdioHomePage ().getDragTab ()).tapOn ();
        for (var index = 1; index <= 3; index++) {
            withFinger (dragDropPage ().leftSourceTile (index)).dragTo (dragDropPage ().leftTargetTile (index));
            withFinger (dragDropPage ().centerSourceTile (index)).dragTo (dragDropPage ().centerTargetTile (index));
            withFinger (dragDropPage ().rightSourceTile (index)).dragTo (dragDropPage ().rightTargetTile (index));
        }
        onElement (dragDropPage ().getTitle ()).verifyTextOf ()
            .isEqualTo ("Congratulations");
        onElement (dragDropPage ().getDescription ()).verifyTextOf ()
            .isEqualTo ("You made it, click retry if you want to try it again.");
    }
}
