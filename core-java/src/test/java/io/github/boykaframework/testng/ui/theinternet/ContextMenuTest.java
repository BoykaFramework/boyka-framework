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

import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.ContextMenuPage.contextMenuPage;
import static io.github.boykaframework.testng.ui.theinternet.pages.HomePage.homePage;

import io.github.boykaframework.enums.PlatformType;
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
        createSession ("ContextMenuTest", platformType, driverKey);
        navigate ().to (URL);
        withMouse (homePage ().link ("Context Menu")).click ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test context menu.
     */
    @Test (description = "Test context menu")
    public void testContextMenu () {
        withMouse (contextMenuPage ().getHotSpot ()).rightClick ();
        onAlert ().verifyAccept ()
            .isEqualTo ("You selected a context menu");
    }
}
