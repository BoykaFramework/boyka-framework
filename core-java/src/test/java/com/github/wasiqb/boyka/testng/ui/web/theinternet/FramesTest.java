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
import static com.github.wasiqb.boyka.actions.DriverActions.switchToFrame;
import static com.github.wasiqb.boyka.actions.DriverActions.switchToParentFrame;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.pages.theinternet.FramesPage.framesPage;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.pages.theinternet.HomePage;
import com.github.wasiqb.boyka.testng.ui.pages.theinternet.NestedFramePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Frames related tests.
 *
 * @author Wasiq Bhamla
 * @since 23-Jul-2022
 */
public class FramesTest {
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
        navigateTo (URL);
        clickOn (HomePage.homePage ()
            .link ("Frames"));
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test nested bottom frame.
     */
    @Test
    public void testNestedBottomFrame () {
        try {
            clickOn (framesPage ().getNestedFrames ());
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameBottom ());
            verifyTextOf (NestedFramePage.nestedFramePage ()
                .getBody ()).isEqualTo ("BOTTOM");
        } finally {
            switchToParentFrame ();
        }
    }

    /**
     * Test nested left frame.
     */
    @Test
    public void testNestedLeftFrame () {
        try {
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameTop ());
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameLeft ());
            verifyTextOf (NestedFramePage.nestedFramePage ()
                .getBody ()).isEqualTo ("LEFT");
        } finally {
            switchToParentFrame ();
            switchToParentFrame ();
        }
    }

    /**
     * Test nested middle frame.
     */
    @Test
    public void testNestedMiddleFrame () {
        try {
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameTop ());
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameMiddle ());
            verifyTextOf (NestedFramePage.nestedFramePage ()
                .getBody ()).isEqualTo ("MIDDLE");
        } finally {
            switchToParentFrame ();
            switchToParentFrame ();
        }
    }

    /**
     * Test nested right frame.
     */
    @Test
    public void testNestedRightFrame () {
        try {
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameTop ());
            switchToFrame (NestedFramePage.nestedFramePage ()
                .getFrameRight ());
            verifyTextOf (NestedFramePage.nestedFramePage ()
                .getBody ()).isEqualTo ("RIGHT");
        } finally {
            switchToParentFrame ();
            switchToParentFrame ();
        }
    }
}
