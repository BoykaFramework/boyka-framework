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

package com.github.wasiqb.boyka.testng.ui.theinternet.web;

import static com.github.wasiqb.boyka.actions.DriverActions.closeWindow;
import static com.github.wasiqb.boyka.actions.DriverActions.currentWindowHandle;
import static com.github.wasiqb.boyka.actions.DriverActions.executeScript;
import static com.github.wasiqb.boyka.actions.DriverActions.goBack;
import static com.github.wasiqb.boyka.actions.DriverActions.goForward;
import static com.github.wasiqb.boyka.actions.DriverActions.maximize;
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.DriverActions.refresh;
import static com.github.wasiqb.boyka.actions.DriverActions.switchToNewWindow;
import static com.github.wasiqb.boyka.actions.DriverActions.switchToWindow;
import static com.github.wasiqb.boyka.actions.DriverActions.waitUntil;
import static com.github.wasiqb.boyka.actions.DriverActions.windowHandles;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.MultiWindowPage.multiWindowPage;
import static com.google.common.truth.Truth.assertThat;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.WindowType.TAB;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Browser window specific tests.
 *
 * @author Wasiq Bhamla
 * @since 16-Jul-2022
 */
public class WindowTest {
    private static final String URL = "https://the-internet.herokuapp.com/";

    /**
     * Setup test class by initialising driver.
     *
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupClass (final PlatformType appType, final String driverKey) {
        createDriver (appType, driverKey);
        maximize ();
        navigateTo (URL);
        clickOn (homePage ().link ("Multiple Windows"));
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test case to verify browser back navigation.
     */
    @Test (description = "Test browser back navigation")
    public void testBackNavigation () {
        goBack ();
        verifyBrowserUrl ().isEqualTo (URL);
    }

    /**
     * Test case to verify execute script.
     */
    @Test (description = "Test execute script method")
    public void testExecuteScript () {
        final String script = "alert('Hello World');";
        executeScript (script);
        verifyAcceptAlert ().isEqualTo ("Hello World");
    }

    /**
     * Test case to verify browser forward navigation.
     */
    @Test (description = "Test browser forward navigation")
    public void testForwardNavigation () {
        goForward ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify opening new tab window.
     */
    @Test (description = "Test to verify opening new tab")
    public void testOpenNewTab () {
        try {
            switchToNewWindow (TAB);
            verifyBrowserTitle ().isEmpty ();
            verifyBrowserUrl ().isEqualTo ("about:blank");
        } finally {
            closeWindow ();
        }
    }

    /**
     * Test case to verify opening new window.
     */
    @Test
    public void testOpenWindow () {
        final var currentWindow = currentWindowHandle ();
        clickOn (multiWindowPage ().getClickHere ());
        final var newWindow = windowHandles ().stream ()
            .filter (handle -> !handle.equals (currentWindow))
            .findFirst ();
        assertThat (newWindow.isPresent ()).isTrue ();
        switchToWindow (newWindow.get ());
        waitUntil (urlMatches (format ("{0}windows/new", URL)));
        verifyBrowserUrl ().isEqualTo (format ("{0}windows/new", URL));
        verifyTextOf (multiWindowPage ().getTitle ()).isEqualTo ("New Window");
        closeWindow ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify browser refresh.
     */
    @Test (description = "Test to verify page refresh")
    public void testRefreshPage () {
        refresh ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }
}
