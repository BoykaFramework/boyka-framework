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

import static com.github.wasiqb.boyka.actions.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.MouseActions.withMouse;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.FramesPage.framesPage;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.NestedFramePage.nestedFramePage;

import com.github.wasiqb.boyka.enums.PlatformType;
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
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createDriver (platformType, driverKey);
        withDriver ().navigate ()
            .to (URL);
        withMouse ().clickOn (homePage ().link ("Frames"));
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
            withMouse ().clickOn (framesPage ().getNestedFrames ());
            withDriver ().switchToFrame (nestedFramePage ().getFrameBottom ());
            verifyTextOf (nestedFramePage ().getBody ()).isEqualTo ("BOTTOM");
        } finally {
            withDriver ().switchToParentFrame ();
        }
    }

    /**
     * Test nested left frame.
     */
    @Test
    public void testNestedLeftFrame () {
        try {
            withDriver ().switchToFrame (nestedFramePage ().getFrameTop ());
            withDriver ().switchToFrame (nestedFramePage ().getFrameLeft ());
            verifyTextOf (nestedFramePage ().getBody ()).isEqualTo ("LEFT");
        } finally {
            withDriver ().switchToParentFrame ();
            withDriver ().switchToParentFrame ();
        }
    }

    /**
     * Test nested middle frame.
     */
    @Test
    public void testNestedMiddleFrame () {
        try {
            withDriver ().switchToFrame (nestedFramePage ().getFrameTop ());
            withDriver ().switchToFrame (nestedFramePage ().getFrameMiddle ());
            verifyTextOf (nestedFramePage ().getBody ()).isEqualTo ("MIDDLE");
        } finally {
            withDriver ().switchToParentFrame ();
            withDriver ().switchToParentFrame ();
        }
    }

    /**
     * Test nested right frame.
     */
    @Test
    public void testNestedRightFrame () {
        try {
            withDriver ().switchToFrame (nestedFramePage ().getFrameTop ());
            withDriver ().switchToFrame (nestedFramePage ().getFrameRight ());
            verifyTextOf (nestedFramePage ().getBody ()).isEqualTo ("RIGHT");
        } finally {
            withDriver ().switchToParentFrame ();
            withDriver ().switchToParentFrame ();
        }
    }
}
