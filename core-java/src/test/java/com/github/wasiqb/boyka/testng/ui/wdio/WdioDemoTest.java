package com.github.wasiqb.boyka.testng.ui.wdio;

import static com.github.wasiqb.boyka.actions.device.DeviceActions.onDevice;
import static com.github.wasiqb.boyka.actions.drivers.ContextActions.withContext;
import static com.github.wasiqb.boyka.actions.drivers.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.testng.ui.wdio.pages.DragDropPage.dragDropPage;
import static com.github.wasiqb.boyka.testng.ui.wdio.pages.WDIOHomePage.wdioHomePage;
import static com.github.wasiqb.boyka.testng.ui.wdio.pages.WebViewPage.webViewPage;
import static java.text.MessageFormat.format;

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
        createSession (format ("WdioDemoTest-{0}", platformType), platformType, driverKey);
        onDevice ().startRecording ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class", alwaysRun = true)
    public void tearDownTestClass () {
        onDevice ().stopRecording ();
        withDriver ().saveLogs ();
        clearSession ();
    }

    /**
     * Test Drag drop
     */
    @Test
    public void testDragDrop () {
        withFinger (wdioHomePage ().getDragTab ()).tap ();
        for (var index = 1; index <= 3; index++) {
            withFinger (dragDropPage ().leftSourceTile (index)).dragTo (dragDropPage ().leftTargetTile (index));
            withFinger (dragDropPage ().centerSourceTile (index)).dragTo (dragDropPage ().centerTargetTile (index));
            withFinger (dragDropPage ().rightSourceTile (index)).dragTo (dragDropPage ().rightTargetTile (index));
        }
        onElement (dragDropPage ().getTitle ()).verifyText ()
            .isEqualTo ("Congratulations");
        onElement (dragDropPage ().getDescription ()).verifyText ()
            .isEqualTo ("You made it, click retry if you want to try it again.");
    }

    /**
     * Test web view screen.
     */
    @Test (description = "Test WebView screen")
    public void testWebView () {
        verifyWebView ();
    }

    private void verifyWebView () {
        withMouse (wdioHomePage ().getWebViewTab ()).click ();

        withContext ().switchToWebView ();

        onElement (webViewPage ().getPageTitle ()).verifyText ()
            .isEqualTo ("Next-gen browser and mobile automation test framework for Node.js");

        withContext ().switchToNative ();

        onElement (wdioHomePage ().getWebViewTab ()).verifyIsDisplayed ()
            .isTrue ();
    }
}
