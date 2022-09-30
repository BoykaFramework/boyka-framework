/*
 * MIT License
 *
 * Copyright (c) 2022, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.ui.pages.saucedemo;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Checkout page.
 *
 * @author Wasiq Bhamla
 * @since 13-Mar-2022
 */
@Getter
public class CheckoutPage {
    /**
     * Gets checkout page instance.
     *
     * @return Checkout page instance.
     */
    public static CheckoutPage checkoutPage () {
        return new CheckoutPage ();
    }

    private final Locator completeHeader = buildLocator ().web (cssSelector ("h2.complete-header"))
        .build ();
    private final Locator completeText   = buildLocator ().web (cssSelector ("div.complete-text"))
        .build ();
    private final Locator continueButton = buildLocator ().web (id ("continue"))
        .build ();
    private final Locator finish         = buildLocator ().web (id ("finish"))
        .build ();
    private final Locator firstName      = buildLocator ().web (id ("first-name"))
        .build ();
    private final Locator lastName       = buildLocator ().web (id ("last-name"))
        .build ();
    private final Locator title          = buildLocator ().web (cssSelector ("span.title"))
        .build ();
    private final Locator zipCode        = buildLocator ().web (id ("postal-code"))
        .build ();

    private CheckoutPage () {
        // Avoid explicit page initialization.
    }
}
