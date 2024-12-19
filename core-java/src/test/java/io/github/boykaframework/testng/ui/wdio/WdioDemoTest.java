/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.testng.ui.wdio;

import static io.github.boykaframework.actions.device.DeviceActions.onDevice;
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.actions.elements.FingerActions.withFinger;
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
import static io.github.boykaframework.enums.PlatformType.ANDROID;
import static io.github.boykaframework.enums.SwipeDirection.DOWN;
import static io.github.boykaframework.enums.SwipeDirection.UP;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.testng.ui.wdio.pages.AlertDialog.alertDialog;
import static io.github.boykaframework.testng.ui.wdio.pages.DragDropPage.dragDropPage;
import static io.github.boykaframework.testng.ui.wdio.pages.SignUpPage.signUpPage;
import static io.github.boykaframework.testng.ui.wdio.pages.SwipePage.swipePage;
import static io.github.boykaframework.testng.ui.wdio.pages.WDIOHomePage.wdioHomePage;
import static io.github.boykaframework.testng.ui.wdio.pages.WebViewPage.webViewPage;

import io.github.boykaframework.enums.PlatformType;
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
    @AfterMethod
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
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String driverKey) {
        createSession (platformType, driverKey);
        onDevice ().startRecording ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
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
     * Sign Up test.
     */
    @Test
    public void testSignUp () {
        final var userName = "admin@gmail.com";
        final var password = "Admin@1234";
        withFinger (wdioHomePage ().getLoginTab ()).tap ();

        withFinger (signUpPage ().getSignUpTab ()).tap ();
        onTextBox (signUpPage ().getEmail ()).enterText (userName);
        onTextBox (signUpPage ().getPassword ()).enterText (password);
        onTextBox (signUpPage ().getConfirmPassword ()).enterText (password);

        if (getSession ().getPlatformType () == ANDROID) {
            onDevice ().hideKeyboard ();
        }

        withFinger (signUpPage ().getSignUp ()).tap ();

        alertDialog ().verifyMessage ("You successfully signed up!");
    }

    /**
     * Test Swipe page.
     */
    @Test
    public void testSwipe () {
        withFinger (wdioHomePage ().getSwipeTab ()).tap ();
        withFinger (swipePage ().getLogo ()).swipeTill (UP);
        onElement (swipePage ().getLogo ()).verifyIsDisplayed ()
            .isTrue ();

        withFinger (swipePage ().getCarousal ()).swipeTill (DOWN);
        onElement (swipePage ().getCarousal ()).verifyIsDisplayed ()
            .isTrue ();
    }

    /**
     * Test web view screen.
     */
    @Test (description = "Test WebView screen")
    public void testWebView () {
        withMouse (wdioHomePage ().getWebViewTab ()).click ();

        verifyWebView ();
    }

    private void verifyWebView () {
        withContext ().switchToWebView ();

        onElement (webViewPage ().getPageTitle ()).verifyText ()
            .isEqualTo ("Next-gen browser and mobile automation test framework for Node.js");

        withContext ().switchToNative ();

        onElement (wdioHomePage ().getWebViewTab ()).verifyIsDisplayed ()
            .isTrue ();
    }
}
