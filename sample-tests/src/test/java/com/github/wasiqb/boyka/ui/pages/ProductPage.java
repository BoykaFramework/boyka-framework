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

package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Product page objects
 *
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
@Getter
public class ProductPage {
    private static final ProductPage INSTANCE = new ProductPage ();

    public static ProductPage productPage () {
        return INSTANCE;
    }

    private final Locator addToCartBtn       = Locator.buildLocator ()
        .web (By.cssSelector ("div.product-action > button.btn.btn-cart.cart-29"))
        .name ("Add to cart")
        .build ();
    private final Locator notificationPopUp  = Locator.buildLocator ()
        .web (By.id ("notification-box-top"))
        .name ("Notification")
        .build ();
    private final Locator checkoutBtn        = Locator.buildLocator ()
        .web (By.cssSelector ("div.form-row > div:nth-child(2) > a"))
        .parent (this.notificationPopUp)
        .name ("Checkout Button")
        .build ();
    private final Locator palmTreoCameraLens = Locator.buildLocator ()
        .web (By.cssSelector ("#entry_212408 > div > div:nth-child(2)"))
        .name ("Camera Lens")
        .build ();
    private final Locator successMessage     = Locator.buildLocator ()
        .web (By.cssSelector ("div > div.toast-body > div.align-items-start > p"))
        .parent (this.notificationPopUp)
        .name ("Success Message")
        .build ();
}
