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

package io.github.boykaframework.testng.ui.ecomm.pages;

import static org.openqa.selenium.By.cssSelector;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * E-Comm Home Page
 *
 * @author Wasiq Bhamla
 * @since 02-Oct-2024
 */
@Getter
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Gets the Home page instance
     *
     * @return Home page
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator addToCart  = Locator.buildLocator ()
        .name ("Add To Cart")
        .web (cssSelector ("div.product-action button[title=\"Add to Cart\"]"))
        .build ();
    private final Locator cartCount  = Locator.buildLocator ()
        .name ("Cart Count")
        .web (cssSelector ("div.cart-icon span"))
        .build ();
    private final Locator closeToast = Locator.buildLocator ()
        .name ("Close Toast")
        .web (cssSelector ("button[data-dismiss=\"toast\"]"))
        .build ();
    private final Locator product1   = Locator.buildLocator ()
        .web (cssSelector ("div.entry-section div[aria-label=\"1 / 10\"] div.product-thumb-top"))
        .name ("Product 1")
        .build ();

    private HomePage () {
        // Utility class.
    }
}
