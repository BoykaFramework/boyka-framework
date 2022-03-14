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

package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static java.text.MessageFormat.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

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
    /**
     * Home page instance.
     *
     * @return {@link HomePage}
     */
    public static HomePage homePage () {
        return new HomePage ();
    }

    private final Locator logout             = buildLocator ().web (id ("logout_sidebar_link"))
        .build ();
    private final Locator menuButton         = buildLocator ().web (id ("react-burger-menu-btn"))
        .build ();
    private final Locator productParent      = buildLocator ().web (cssSelector ("div.inventory_item"))
        .build ();
    private final Locator productDescription = buildLocator ().parent (this.productParent)
        .web (cssSelector ("div.inventory_item_desc"))
        .build ();
    private final Locator addToCartButton    = buildLocator ().parent (this.productParent)
        .web (id ("add-to-cart-sauce-labs-backpack"))
        .build ();
    private final Locator productPrice       = buildLocator ().parent (this.productParent)
        .web (cssSelector ("div.inventory_item_price"))
        .build ();
    private final Locator productTitle       = buildLocator ().parent (this.productParent)
        .web (cssSelector ("div.inventory_item_name"))
        .build ();
    private final Locator shoppingCart       = buildLocator ().web (cssSelector ("a.shopping_cart_link"))
        .build ();
    private final Locator shoppingCartCount  = buildLocator ().web (cssSelector ("span.shopping_cart_badge"))
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
        return buildLocator ().web (xpath (format (".//*[text()=\"{0}\"]", productName)))
            .build ();
    }
}
