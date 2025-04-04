/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.ui.theinternet;

import static com.google.common.truth.Truth.assertWithMessage;
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.HomePage.homePage;
import static io.github.boykaframework.testng.ui.theinternet.pages.MultiWindowPage.multiWindowPage;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.WindowType.TAB;
import static org.openqa.selenium.support.ui.ExpectedConditions.urlMatches;

import io.github.boykaframework.enums.PlatformType;
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
        createSession ("WindowTest", platformType, driverKey);
        onWindow ().maximize ();
        navigate ().to (URL);
        withMouse (homePage ().link ("Multiple Windows")).click ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test case to verify browser back navigation.
     */
    @Test (description = "Test browser back navigation")
    public void testBackNavigation () {
        navigate ().back ();
        navigate ().verifyUrl ()
            .isEqualTo (URL);
    }

    /**
     * Test case to verify execute script.
     */
    @Test (description = "Test execute script method")
    public void testExecuteScript () {
        final String script = "alert('Hello World');";
        withDriver ().executeScript (script);
        onAlert ().verifyAccept ()
            .isEqualTo ("Hello World");
    }

    /**
     * Test case to verify browser forward navigation.
     */
    @Test (description = "Test browser forward navigation")
    public void testForwardNavigation () {
        navigate ().forward ();
        navigate ().verifyUrl ()
            .isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify opening new tab window.
     */
    @Test (description = "Test to verify opening new tab")
    public void testOpenNewTab () {
        try {
            onWindow ().switchToNew (TAB);
            onWindow ().verifyTitle ()
                .isEmpty ();
            navigate ().verifyUrl ()
                .isEqualTo ("about:blank");
        } finally {
            onWindow ().close ();
        }
    }

    /**
     * Test case to verify opening new window.
     */
    @Test
    public void testOpenWindow () {
        final var currentWindow = onWindow ().currentHandle ();
        withMouse (multiWindowPage ().getClickHere ()).click ();
        final var newWindow = onWindow ().handles ()
            .stream ()
            .filter (handle -> !handle.equals (currentWindow))
            .findFirst ();
        assertWithMessage ("Window").that (newWindow.isPresent ())
            .isTrue ();
        onWindow ().switchTo (newWindow.orElseThrow ());
        withDriver ().waitUntil (urlMatches (format ("{0}windows/new", URL)), ofSeconds (10));
        navigate ().verifyUrl ()
            .isEqualTo (format ("{0}windows/new", URL));
        onElement (multiWindowPage ().getTitle ()).verifyText ()
            .isEqualTo ("New Window");
        onWindow ().close ();
        navigate ().verifyUrl ()
            .isEqualTo (format ("{0}windows", URL));
    }

    /**
     * Test case to verify browser refresh.
     */
    @Test (description = "Test to verify page refresh")
    public void testRefreshPage () {
        navigate ().refresh ();
        navigate ().verifyUrl ()
            .isEqualTo (format ("{0}windows", URL));
    }
}
