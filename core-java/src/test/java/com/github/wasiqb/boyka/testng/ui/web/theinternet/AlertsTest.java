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

import static com.github.wasiqb.boyka.actions.DriverActions.fullScreen;
import static com.github.wasiqb.boyka.actions.DriverActions.goBack;
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyDismissAlert;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.pages.theinternet.AlertPage;
import com.github.wasiqb.boyka.testng.ui.pages.theinternet.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * This will test all Web related actions.
 *
 * @author Wasiq Bhamla
 * @since 12-Jul-2022
 */
public class AlertsTest {
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
        fullScreen ();
        navigateTo (URL);
        clickOn (HomePage.homePage ()
            .link ("JavaScript Alerts"));
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        goBack ();
        verifyBrowserUrl ().isEqualTo (URL);
        closeDriver ();
    }

    /**
     * This will test Accept alert button action.
     */
    @Test (description = "Tests Accept alert")
    public void testAcceptAlert () {
        clickOn (AlertPage.alertPage ()
            .getAlertButton ());
        verifyAcceptAlert ().isEqualTo ("I am a JS Alert");
        verifyTextOf (AlertPage.alertPage ()
            .getResult ()).isEqualTo ("You successfully clicked an alert");
    }

    /**
     * This will test accept confirm button action.
     */
    @Test (description = "Tests Accept confirm alert")
    public void testAcceptConfirmAlert () {
        clickOn (AlertPage.alertPage ()
            .getConfirmButton ());
        verifyAcceptAlert ().isEqualTo ("I am a JS Confirm");
        verifyTextOf (AlertPage.alertPage ()
            .getResult ()).isEqualTo ("You clicked: Ok");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test (description = "Tests Dismiss confirm alert")
    public void testDismissConfirmAlert () {
        clickOn (AlertPage.alertPage ()
            .getConfirmButton ());
        verifyDismissAlert ().isEqualTo ("I am a JS Confirm");
        verifyTextOf (AlertPage.alertPage ()
            .getResult ()).isEqualTo ("You clicked: Cancel");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test (description = "Tests Dismiss prompt alert")
    public void testDismissPromptAlert () {
        clickOn (AlertPage.alertPage ()
            .getPromptButton ());
        verifyAcceptAlert ("Wasiq").isEqualTo ("I am a JS prompt");
        verifyTextOf (AlertPage.alertPage ()
            .getResult ()).isEqualTo ("You entered: Wasiq");
    }
}
