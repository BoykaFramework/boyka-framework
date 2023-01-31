/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.ui.mydemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.androidUIAutomator;
import static io.appium.java_client.AppiumBy.className;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Home page object for Sauce demo application.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
@Getter
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Home page instance.
     *
     * @return {@link HomePage}
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator catalogMenu       = buildLocator ().android (accessibilityId ("menu item catalog"))
        .ios (accessibilityId ("tab bar option catalog"))
        .name ("Catalog Menu")
        .build ();
    private final Locator header            = buildLocator ().android (accessibilityId ("container header"))
        .ios (accessibilityId ("container header"))
        .name ("Page Header")
        .build ();
    private final Locator loginMenu         = buildLocator ().android (accessibilityId ("menu item log in"))
        .ios (accessibilityId ("menu item log in"))
        .name ("Login Menu")
        .build ();
    private final Locator logoutMenu        = buildLocator ().android (accessibilityId ("menu item log out"))
        .ios (accessibilityId ("menu item log out"))
        .name ("Logout Menu")
        .build ();
    private final Locator menuButton        = buildLocator ().android (accessibilityId ("open menu"))
        .ios (accessibilityId ("tab bar option menu"))
        .name ("Menu Button")
        .build ();
    private final Locator shoppingCart      = buildLocator ().android (accessibilityId ("cart badge"))
        .ios (accessibilityId ("tab bar option cart"))
        .name ("Shopping Cart")
        .build ();
    private final Locator shoppingCartCount = buildLocator ().parent (this.shoppingCart)
        .android (className ("android.widget.TextView"))
        .name ("Shopping Cart Count")
        .build ();

    private HomePage () {
        // Avoid explicit class initialisation
    }

    /**
     * Get product item title locator based on its text.
     *
     * @param productName Product name
     *
     * @return {@link Locator} of product item title
     */
    public Locator productItem (final String productName) {
        return buildLocator ().android (
                androidUIAutomator (format ("new UiSelector().textContains(\"{0}\")", productName)))
            .ios (iOSNsPredicateString (
                format ("label CONTAINS \"{0}\" AND type == \"XCUIElementTypeStaticText\"", productName)))
            .name (format ("Product [{0}]", productName))
            .build ();
    }
}
