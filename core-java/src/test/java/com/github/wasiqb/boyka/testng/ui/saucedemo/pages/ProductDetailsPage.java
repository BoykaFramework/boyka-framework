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

package com.github.wasiqb.boyka.testng.ui.saucedemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.cssSelector;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Product details page.
 *
 * @author Wasiq Bhamla
 * @since 10-Mar-2022
 */
@Getter
public class ProductDetailsPage {
    private static final ProductDetailsPage PRODUCT_DETAILS_PAGE = new ProductDetailsPage ();

    /**
     * Gets the product details page instance.
     *
     * @return {@link ProductDetailsPage} instance
     */
    public static ProductDetailsPage productDetailsPage () {
        return PRODUCT_DETAILS_PAGE;
    }

    private final Locator container = buildLocator ().web (
            cssSelector ("div#inventory_item_container div.inventory_details"))
        .android (accessibilityId ("test-Inventory item page"))
        .ios (accessibilityId ("test-Inventory item page"))
        .name ("Container")
        .build ();
    private final Locator image     = buildLocator ().name ("Product Image")
        .android (accessibilityId ("test-Image Container"))
        .ios (accessibilityId ("test-Image Container"))
        .build ();

    private ProductDetailsPage () {
        // Avoid explicit class initialisation.
    }
}
