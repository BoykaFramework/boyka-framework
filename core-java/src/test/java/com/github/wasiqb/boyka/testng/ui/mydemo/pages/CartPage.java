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
import static java.text.MessageFormat.format;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Cart page.
 *
 * @author Wasiq Bhamla
 * @since 20-Jan-2023
 */
@Getter
public class CartPage {
    private static final CartPage CART_PAGE = new CartPage ();

    /**
     * Gets Cart page instance.
     *
     * @return Cart page instance
     */
    public static CartPage cartPage () {
        return CART_PAGE;
    }

    private final Locator checkout     = buildLocator ().web (id ("checkout"))
        .android (accessibilityId ("Proceed To Checkout button"))
        .ios (accessibilityId ("Proceed To Checkout button"))
        .name ("Checkout")
        .build ();
    private final Locator productName  = buildLocator ().android (accessibilityId ("product label"))
        .ios (accessibilityId ("product label"))
        .name ("Product Name")
        .build ();
    private final Locator productPrice = buildLocator ().android (accessibilityId ("product price"))
        .ios (accessibilityId ("product price"))
        .name ("Product Price")
        .build ();
    private final Locator remove       = buildLocator ().android (accessibilityId ("remove item"))
        .ios (accessibilityId ("remove item"))
        .name ("Remove")
        .build ();
    private final Locator totalItems   = buildLocator ().android (accessibilityId ("total number"))
        .ios (accessibilityId ("total number"))
        .name ("Total Items")
        .build ();
    private final Locator totalPrice   = buildLocator ().android (accessibilityId ("total price"))
        .ios (accessibilityId ("total price"))
        .name ("Total Price")
        .build ();

    private CartPage () {
        // Avoid explicit class initialisation
    }

    public Locator getColor (final ProductDetailsPage.Color color) {
        return buildLocator ().android (accessibilityId (format ("{0} circle", color.name ()
                .toLowerCase ())))
            .ios (accessibilityId (format ("{0} circle", color.name ()
                .toLowerCase ())))
            .name ("Color")
            .build ();
    }
}
