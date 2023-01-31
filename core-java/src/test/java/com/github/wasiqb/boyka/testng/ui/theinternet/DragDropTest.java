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
import static com.github.wasiqb.boyka.actions.MouseActions.dragDropTo;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.DragDropPage.dragDropPage;

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
public class DragDropTest {
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
        createDriver (platformType, driverKey);
        navigate ().to (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Drag and Drop test.
     */
    @Test (description = "Drag Drop test")
    public void testDragDrop () {
        dragDropTo (dragDropPage ().getDraggable (), dragDropPage ().getDroppable ());
        verifyTextOf (dragDropPage ().getHeader ()).isEqualTo ("Dropped!");
    }
}
