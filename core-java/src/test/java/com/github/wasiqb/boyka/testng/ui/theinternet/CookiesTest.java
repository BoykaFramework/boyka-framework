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

import static com.github.wasiqb.boyka.actions.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.CookieActions.withCookies;
import static com.github.wasiqb.boyka.actions.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.WindowActions.onWindow;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.HomePage.homePage;
import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Cookies test.
 *
 * @author Wasiq Bhamla
 * @since 15-Jul-2022
 */
public class CookiesTest {
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
        onWindow ().minimize ();
        navigate ().to (URL);
        withMouse (homePage ().link ("JavaScript Alerts")).click ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test delete all cookies.
     */
    @Test (description = "Verify delete all cookies", priority = 3)
    public void testDeleteAllCookies () {
        withCookies ().deleteAll ();
        assertThat (withCookies ().cookies ()
            .size ()).isEqualTo (0);
    }

    /**
     * Test delete a single cookie.
     */
    @Test (description = "Tests delete of single cookie", priority = 2)
    public void testDeleteSingleCookie () {
        final var cookies = withCookies ().cookies ();
        final var cookieCount = cookies.size ();
        withCookies ().delete (cookies.get (0));
        assertThat (withCookies ().cookies ()
            .size ()).isEqualTo (cookieCount - 1);
    }

    /**
     * Test get cookie.
     */
    @Test (description = "Test get cookie", priority = 1)
    public void testGetCookie () {
        final var cookie = withCookies ().cookies ()
            .get (0);
        assertThat (withCookies ().cookie (cookie)
            .getName ()).isEqualTo (cookie);
    }
}
