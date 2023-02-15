/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.ui.theinternet;

import static com.github.wasiqb.boyka.actions.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.MouseActions.withMouse;
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
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createDriver (platformType, driverKey);
        withDriver ().maximize ();
        withDriver ().navigate ()
            .to (URL);
        withMouse ().clickOn (homePage ().link ("Multiple Windows"));
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
        withDriver ().navigate ()
            .back ();
        verifyBrowserUrl ().isEqualTo (URL);
    }

    /**
     * Test case to verify execute script.
     */
    @Test (description = "Test execute script method")
    public void testExecuteScript () {
        final String script = "alert('Hello World');";
        withDriver ().executeScript (script);
        verifyAcceptAlert ().isEqualTo ("Hello World");
    }

    /**
     * Test case to verify browser forward navigation.
     */
    @Test (description = "Test browser forward navigation")
    public void testForwardNavigation () {
        withDriver ().navigate ()
            .forward ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify opening new tab window.
     */
    @Test (description = "Test to verify opening new tab")
    public void testOpenNewTab () {
        try {
            withDriver ().switchToNewWindow (TAB);
            verifyBrowserTitle ().isEmpty ();
            verifyBrowserUrl ().isEqualTo ("about:blank");
        } finally {
            withDriver ().closeWindow ();
        }
    }

    /**
     * Test case to verify opening new window.
     */
    @Test
    public void testOpenWindow () {
        final var currentWindow = withDriver ().currentWindowHandle ();
        withMouse ().clickOn (multiWindowPage ().getClickHere ());
        final var newWindow = withDriver ().windowHandles ()
            .stream ()
            .filter (handle -> !handle.equals (currentWindow))
            .findFirst ();
        assertThat (newWindow.isPresent ()).isTrue ();
        withDriver ().switchToWindow (newWindow.get ());
        withDriver ().waitUntil (urlMatches (format ("{0}windows/new", URL)));
        verifyBrowserUrl ().isEqualTo (format ("{0}windows/new", URL));
        verifyTextOf (multiWindowPage ().getTitle ()).isEqualTo ("New Window");
        withDriver ().closeWindow ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify browser refresh.
     */
    @Test (description = "Test to verify page refresh")
    public void testRefreshPage () {
        withDriver ().navigate ()
            .refresh ();
        verifyBrowserUrl ().isEqualTo (format ("{0}windows", URL));
    }
}
