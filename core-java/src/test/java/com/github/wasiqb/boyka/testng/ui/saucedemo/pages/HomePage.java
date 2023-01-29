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
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Home page instance.
     *
     * @return {@link HomePage}
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator logout             = Locator.buildLocator ()
        .web (id ("logout_sidebar_link"))
        .name ("Logout")
        .build ();
    private final Locator menuButton         = Locator.buildLocator ()
        .web (id ("react-burger-menu-btn"))
        .name ("Menu Button")
        .build ();
    private final Locator productParent      = Locator.buildLocator ()
        .web (cssSelector ("div#inventory_container"))
        .name ("Product Parent")
        .build ();
    private final Locator productDescription = Locator.buildLocator ()
        .parent (this.productParent)
        .name ("Product description")
        .web (cssSelector ("div.inventory_item_desc"))
        .build ();
    private final Locator addToCartButton    = Locator.buildLocator ()
        .parent (this.productParent)
        .name ("Add to cart button")
        .web (id ("add-to-cart-sauce-labs-backpack"))
        .build ();
    private final Locator productPrice       = Locator.buildLocator ()
        .parent (this.productParent)
        .name ("Product price")
        .web (cssSelector ("div.inventory_item_price"))
        .build ();
    private final Locator productTitle       = Locator.buildLocator ()
        .parent (this.productParent)
        .name ("Product title")
        .web (cssSelector ("div.inventory_item_name"))
        .build ();
    private final Locator shoppingCart       = Locator.buildLocator ()
        .web (cssSelector ("a.shopping_cart_link"))
        .name ("Shopping Cart")
        .build ();
    private final Locator shoppingCartCount  = Locator.buildLocator ()
        .web (cssSelector ("span.shopping_cart_badge"))
        .parent (this.shoppingCart)
        .name ("Shopping Cart count")
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
        return Locator.buildLocator ()
            .web (xpath (format (".//*[text()=\"{0}\"]", productName)))
            .name (format ("Product [{0}]", productName))
            .build ();
    }
}
