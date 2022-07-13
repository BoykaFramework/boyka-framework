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

package com.github.wasiqb.boyka.testng.web.pages.saucedemo;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Product details page.
 *
 * @author Wasiq Bhamla
 * @since 10-Mar-2022
 */
@Getter
public class ProductDetailsPage {
    /**
     * Gets the product details page instance.
     *
     * @return {@link ProductDetailsPage} instance
     */
    public static ProductDetailsPage productDetailsPage () {
        return new ProductDetailsPage ();
    }

    private final Locator container = buildLocator ().web (
            By.cssSelector ("div#inventory_item_container div.inventory_details"))
        .build ();

    private ProductDetailsPage () {
        // Avoid explicit class initialisation.
    }
}
