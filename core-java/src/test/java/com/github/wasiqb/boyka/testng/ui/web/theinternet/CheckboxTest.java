/*
 * MIT License
 *
 * Copyright (c) 2022, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.ui.web.theinternet;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementSelected;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.pages.theinternet.CheckboxPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CheckboxTest {
    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";

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
        navigateTo (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test select checkbox.
     */
    @Test (description = "Verify select checkbox")
    public void testCheckOption () {
        verifyElementSelected (CheckboxPage.checkboxPage ()
            .getOption1 ()).isFalse ();
        verifyElementSelected (CheckboxPage.checkboxPage ()
            .getOption2 ()).isTrue ();
        clickOn (CheckboxPage.checkboxPage ()
            .getOption1 ());
        verifyElementSelected (CheckboxPage.checkboxPage ()
            .getOption1 ()).isTrue ();
    }
}
