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

package com.github.wasiqb.boyka.testng.web;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyAction.verifyElementDisplayed;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.web.pages.LoginPage.loginPage;

import com.github.wasiqb.boyka.enums.ApplicationType;
import com.github.wasiqb.boyka.manager.DriverManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestWeb {
    private DriverManager driverManager;

    @BeforeClass
    @Parameters ({ "appType", "driverKey" })
    public void setupTestClass (final ApplicationType appType, final String driverKey) {
        this.driverManager = createDriver (appType, driverKey);
    }

    @AfterClass
    public void tearDownTestClass () {
        this.driverManager.close ();
    }

    @Test
    public void testWeb () {
        navigateTo ("https://www.saucedemo.com/");
        enterText (loginPage ().getUsername (), "standard_user");
        enterText (loginPage ().getPassword (), "secret_sauce");
        clickOn (loginPage ().getLoginButton ());
        verifyElementDisplayed (homePage ().getMenuButton ());
    }
}
