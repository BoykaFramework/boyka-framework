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

package io.github.boykaframework.testng.ui.theinternet;

import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.AlertPage.alertPage;
import static io.github.boykaframework.testng.ui.theinternet.pages.HomePage.homePage;

import io.github.boykaframework.actions.drivers.NavigateActions;
import io.github.boykaframework.actions.drivers.WindowActions;
import io.github.boykaframework.enums.PlatformType;
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
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createSession ("AlertsTest", platformType, driverKey);
        WindowActions.onWindow ()
            .fullScreen ();
        NavigateActions.navigate ()
            .to (URL);
        withMouse (homePage ().link ("JavaScript Alerts")).click ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        NavigateActions.navigate ()
            .back ();
        NavigateActions.navigate ()
            .verifyUrl ()
            .isEqualTo (URL);
        clearSession ();
    }

    /**
     * This will test Accept alert button action.
     */
    @Test (description = "Tests Accept alert")
    public void testAcceptAlert () {
        withMouse (alertPage ().getAlertButton ()).click ();
        onAlert ().verifyAccept ()
            .isEqualTo ("I am a JS Alert");
        onElement (alertPage ().getResult ()).verifyText ()
            .isEqualTo ("You successfully clicked an alert");
    }

    /**
     * This will test accept confirm button action.
     */
    @Test (description = "Tests Accept confirm alert")
    public void testAcceptConfirmAlert () {
        withMouse (alertPage ().getConfirmButton ()).click ();
        onAlert ().verifyAccept ()
            .isEqualTo ("I am a JS Confirm");
        onElement (alertPage ().getResult ()).verifyText ()
            .isEqualTo ("You clicked: Ok");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test (description = "Tests Dismiss confirm alert")
    public void testDismissConfirmAlert () {
        withMouse (alertPage ().getConfirmButton ()).click ();
        onAlert ().verifyDismiss ()
            .isEqualTo ("I am a JS Confirm");
        onElement (alertPage ().getResult ()).verifyText ()
            .isEqualTo ("You clicked: Cancel");
    }

    /**
     * This will test dismiss confirm button action.
     */
    @Test (description = "Tests Dismiss prompt alert")
    public void testDismissPromptAlert () {
        withMouse (alertPage ().getPromptButton ()).click ();
        onAlert ().verifyAccept ("Wasiq")
            .isEqualTo ("I am a JS prompt");
        onElement (alertPage ().getResult ()).verifyText ()
            .isEqualTo ("You entered: Wasiq");
    }
}
