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

package io.github.boykaframework.testng.ui.ecomm;

import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.ecomm.pages.HomePage.homePage;

import io.github.boykaframework.enums.PlatformType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddCartTest {
    @AfterMethod
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            onWindow ().takeScreenshot ();
        }
    }

    @BeforeClass
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String chromeConfig) {
        createSession (platformType, chromeConfig);
    }

    @AfterClass
    public void tearDownClass () {
        clearSession ();
    }

    @Test
    public void testAddCart () {
        navigate ().to ("https://ecommerce-playground.lambdatest.io/");
        withMouse (homePage ().getProduct1 ()).scrollIntoView ();
        withMouse (homePage ().getProduct1 ()).hover ();
        withMouse (homePage ().getAddToCart ()).click ();
        withMouse (homePage ().getCloseToast ()).click ();

        onElement (homePage ().getCartCount ()).verifyText ()
            .isEqualTo ("1");
    }
}
