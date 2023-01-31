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

import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.rightClickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.ContextMenuPage.contextMenuPage;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.HomePage.homePage;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Context menu test.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
public class ContextMenuTest {
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
        navigate ().to (URL);
        clickOn (homePage ().link ("Context Menu"));
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test context menu.
     */
    @Test (description = "Test context menu")
    public void testContextMenu () {
        rightClickOn (contextMenuPage ().getHotSpot ());
        verifyAcceptAlert ().isEqualTo ("You selected a context menu");
    }
}
