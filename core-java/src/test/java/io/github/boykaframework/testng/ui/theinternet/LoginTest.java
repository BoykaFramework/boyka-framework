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

import static io.github.boykaframework.actions.device.DeviceActions.onDevice;
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.LoginPage.loginPage;
import static java.text.MessageFormat.format;

import io.github.boykaframework.enums.PlatformType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Login Test class.
 *
 * @author Wasiq Bhamla
 * @since 16-Sept-2023
 */
public class LoginTest {
    private static final String URL = "https://the-internet.herokuapp.com/login";

    /**
     * Setup test method to take screenshot after each test method.
     */
    @AfterMethod
    public void afterMethod (final ITestResult result) {
        onWindow ().takeScreenshot ();
    }

    /**
     * Setup test class by initialising driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createSession (format ("LoginTest-{0}", platformType), platformType, driverKey);
        onDevice ().startRecording ();
        navigate ().to (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        onDevice ().stopRecording ();
        clearSession ();
    }

    @Test (description = "Test Login Flow")
    public void testLogin () {
        onTextBox (loginPage ().getUserName ()).enterText ("tomsmith");
        onTextBox (loginPage ().getPassword ()).enterText ("SuperSecretPassword!");
        withMouse (loginPage ().getLogin ()).click ();
        onElement (loginPage ().getMessage ()).verifyText ()
            .contains ("You logged into a secure area!");
        withMouse (loginPage ().getLogout ()).click ();
        onElement (loginPage ().getMessage ()).verifyText ()
            .contains ("You logged out of the secure area!");
    }
}
