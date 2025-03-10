/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

import static io.github.boykaframework.actions.drivers.FrameActions.onFrame;
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.FramesPage.framesPage;
import static io.github.boykaframework.testng.ui.theinternet.pages.HomePage.homePage;
import static io.github.boykaframework.testng.ui.theinternet.pages.NestedFramePage.nestedFramePage;

import io.github.boykaframework.enums.PlatformType;
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
        createSession ("FramesTest", platformType, driverKey);
        navigate ().to (URL);
        withMouse (homePage ().link ("Frames")).click ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test nested bottom frame.
     */
    @Test
    public void testNestedBottomFrame () {
        try {
            withMouse (framesPage ().getNestedFrames ()).click ();
            onFrame ().switchTo (nestedFramePage ().getFrameBottom ());
            onElement (nestedFramePage ().getBody ()).verifyText ()
                .isEqualTo ("BOTTOM");
        } finally {
            onFrame ().switchToParent ();
        }
    }

    /**
     * Test nested left frame.
     */
    @Test
    public void testNestedLeftFrame () {
        try {
            onFrame ().switchTo (nestedFramePage ().getFrameTop ());
            onFrame ().switchTo (nestedFramePage ().getFrameLeft ());
            onElement (nestedFramePage ().getBody ()).verifyText ()
                .isEqualTo ("LEFT");
        } finally {
            onFrame ().switchToParent ();
            onFrame ().switchToParent ();
        }
    }

    /**
     * Test nested middle frame.
     */
    @Test
    public void testNestedMiddleFrame () {
        try {
            onFrame ().switchTo (nestedFramePage ().getFrameTop ());
            onFrame ().switchTo (nestedFramePage ().getFrameMiddle ());
            onElement (nestedFramePage ().getBody ()).verifyText ()
                .isEqualTo ("MIDDLE");
        } finally {
            onFrame ().switchToParent ();
            onFrame ().switchToParent ();
        }
    }

    /**
     * Test nested right frame.
     */
    @Test
    public void testNestedRightFrame () {
        try {
            onFrame ().switchTo (nestedFramePage ().getFrameTop ());
            onFrame ().switchTo (nestedFramePage ().getFrameRight ());
            onElement (nestedFramePage ().getBody ()).verifyText ()
                .isEqualTo ("RIGHT");
        } finally {
            onFrame ().switchToParent ();
            onFrame ().switchToParent ();
        }
    }
}
