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

package com.github.wasiqb.boyka.testng.web.theinternet;

import static com.github.wasiqb.boyka.actions.DriverActions.cookie;
import static com.github.wasiqb.boyka.actions.DriverActions.cookies;
import static com.github.wasiqb.boyka.actions.DriverActions.deleteAllCookies;
import static com.github.wasiqb.boyka.actions.DriverActions.deleteCookie;
import static com.github.wasiqb.boyka.actions.DriverActions.minimize;
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.theinternet.pages.HomePage.homePage;
import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.enums.ApplicationType;
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
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupClass (final ApplicationType appType, final String driverKey) {
        createDriver (appType, driverKey);
        minimize ();
        navigateTo (URL);
        clickOn (homePage ().link ("JavaScript Alerts"));
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
        deleteAllCookies ();
        assertThat (cookies ().size ()).isEqualTo (0);
    }

    /**
     * Test delete a single cookie.
     */
    @Test (description = "Tests delete of single cookie", priority = 2)
    public void testDeleteSingleCookie () {
        final var cookies = cookies ();
        final var cookieCount = cookies.size ();
        deleteCookie (cookies.get (0));
        assertThat (cookies ().size ()).isEqualTo (cookieCount - 1);
    }

    /**
     * Test get cookie.
     */
    @Test (description = "Test get cookie", priority = 1)
    public void testGetCookie () {
        final var cookie = cookies ().get (0);
        assertThat (cookie (cookie).getName ()).isEqualTo (cookie);
    }
}
