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

package com.github.wasiqb.boyka.testng.ui.mydemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Product Details Page
 *
 * @author Wasiq Bhamla
 * @since 20-Jan-2023
 */
@Getter
public class ProductDetailsPage {
    /**
     * Color enum
     */
    public enum Color {
        BLACK,
        BLUE,
        GRAY,
        RED
    }

    private static final ProductDetailsPage PRODUCT_DETAILS_PAGE = new ProductDetailsPage ();

    /**
     * Gets Product details page instance
     *
     * @return {@link ProductDetailsPage} instance
     */
    public static ProductDetailsPage productDetailsPage () {
        return PRODUCT_DETAILS_PAGE;
    }

    private final Locator addQuantity    = buildLocator ().android (accessibilityId ("counter plus button"))
        .ios (accessibilityId ("counter plus button"))
        .name ("Add Quantity")
        .build ();
    private final Locator addToCart      = buildLocator ().android (accessibilityId ("Add To Cart button"))
        .ios (accessibilityId ("Add To Cart button"))
        .name ("Add To Cart")
        .build ();
    private final Locator price          = buildLocator ().android (accessibilityId ("product price"))
        .ios (accessibilityId ("product price"))
        .name ("Product Price")
        .build ();
    private final Locator quantityParent = buildLocator ().android (accessibilityId ("counter amount"))
        .ios (accessibilityId ("counter amount"))
        .name ("Quantity Parent")
        .build ();
    private final Locator quantity       = buildLocator ().android (className ("android.widget.TextView"))
        .name ("Quantity")
        .parent (this.quantityParent)
        .build ();
    private final Locator removeQuantity = buildLocator ().android (accessibilityId ("counter minus button"))
        .ios (accessibilityId ("counter minus button"))
        .name ("Remove Quantity")
        .build ();
    private final Locator title          = buildLocator ().android (accessibilityId ("container header"))
        .ios (accessibilityId ("container header"))
        .name ("Product Title")
        .build ();

    private ProductDetailsPage () {
        // Utility Class.
    }

    /**
     * Gets color element.
     *
     * @param color Color
     *
     * @return {@link Locator} instance
     */
    public Locator getColor (final Color color) {
        return buildLocator ().android (accessibilityId (format ("{0} circle", color.name ()
                .toLowerCase ())))
            .ios (accessibilityId (format ("{0} circle", color.name ()
                .toLowerCase ())))
            .name ("Color")
            .build ();
    }
}
