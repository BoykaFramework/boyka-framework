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

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class ReviewOrderPage {
    private static final ReviewOrderPage REVIEW_ORDER_PAGE = new ReviewOrderPage ();

    public static ReviewOrderPage reviewOrderPage () {
        return REVIEW_ORDER_PAGE;
    }

    private final Locator placeOrder   = buildLocator ().android (accessibilityId ("Place Order button"))
        .ios (accessibilityId ("Place Order button"))
        .name ("Place Order")
        .build ();
    private final Locator productName  = buildLocator ().android (accessibilityId ("product label"))
        .ios (accessibilityId ("product label"))
        .name ("Product Name")
        .build ();
    private final Locator productPrice = buildLocator ().android (accessibilityId ("product price"))
        .ios (accessibilityId ("product price"))
        .name ("Product Price")
        .build ();

    private ReviewOrderPage () {
        // Utility class.
    }
}
