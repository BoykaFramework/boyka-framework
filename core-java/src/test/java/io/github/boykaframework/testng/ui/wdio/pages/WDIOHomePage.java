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

package io.github.boykaframework.testng.ui.wdio.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * WDIO Sample app home page
 *
 * @author Wasiq Bhamla
 * @since 24-Dec-2022
 */
@Getter
public class WDIOHomePage {
    private static final WDIOHomePage WDIO_HOME_PAGE = new WDIOHomePage ();

    /**
     * Home page instance.
     *
     * @return {@link WDIOHomePage}
     */
    public static WDIOHomePage wdioHomePage () {
        return WDIO_HOME_PAGE;
    }

    private final Locator dragTab    = Locator.buildLocator ()
        .android (accessibilityId ("Drag"))
        .ios (accessibilityId ("Drag"))
        .name ("Drag Tab")
        .build ();
    private final Locator loginTab   = Locator.buildLocator ()
        .name ("Login Tab")
        .android (accessibilityId ("Login"))
        .ios (accessibilityId ("Login"))
        .build ();
    private final Locator swipeTab   = Locator.buildLocator ()
        .name ("Swipe Tab")
        .android (accessibilityId ("Swipe"))
        .ios (accessibilityId ("Swipe"))
        .build ();
    private final Locator webViewTab = Locator.buildLocator ()
        .android (accessibilityId ("Webview"))
        .ios (accessibilityId ("Webview"))
        .name ("Drag Tab")
        .build ();
}
