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

import static com.github.wasiqb.boyka.actions.drivers.AlertActions.onAlert;
import static com.github.wasiqb.boyka.actions.drivers.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.DoubleClickPage.doubleClickPage;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Drag and drop test.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
public class DoubleClickTest {
    private static final String URL = "https://webdriveruniversity.com/Actions/index.html";

    /**
     * Setup test class by initialising driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createSession ("DoubleClickTest", platformType, driverKey);
        navigate ().to (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test click and hold method.
     */
    @Test (description = "Verify Click and Hold method")
    public void testClickAndHold () {
        withMouse (doubleClickPage ().getClickHold ()).clickAndHold ();
        onElement (doubleClickPage ().getClickHold ()).verifyText ()
            .isEqualTo ("Well done! keep holding that click now.....");
    }

    /**
     * Double click test.
     */
    @Test (description = "Double click test")
    public void testDoubleClick () {
        withMouse (doubleClickPage ().getDoubleClick ()).doubleClick ();
        onElement (doubleClickPage ().getDoubleClick ()).verifyStyle ("background-color")
            .isEqualTo ("rgba(147, 203, 90, 1)");
    }

    /**
     * Test hover and click method.
     */
    @Test (description = "Verify Click and Hold method")
    public void testHoverAndClick () {
        withMouse (doubleClickPage ().getHoverButton ()).hover ();
        withMouse (doubleClickPage ().getHoverMenu ()).click ();
        onAlert ().verifyAccept ()
            .isEqualTo ("Well done you clicked on the link!");
    }
}
