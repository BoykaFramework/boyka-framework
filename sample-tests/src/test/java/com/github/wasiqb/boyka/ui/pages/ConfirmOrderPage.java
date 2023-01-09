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
 * Confirm order page objects
 *
 * @author Faisal Khatri
 * @since 30/07/2022
 */
@Getter
public class ConfirmOrderPage {
    private static final ConfirmOrderPage INSTANCE = new ConfirmOrderPage ();

    public static ConfirmOrderPage confirmOrderPage () {
        return INSTANCE;
    }

    private final Locator confirmOrderBtn = Locator.buildLocator ()
        .web (By.id ("button-confirm"))
        .name ("Confirm Order")
        .build ();

    private final Locator pageHeader = Locator.buildLocator ()
        .web (By.tagName ("h1"))
        .name ("Page Header")
        .build ();

    private final Locator productName = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.table-responsive > table > tbody > tr > td:nth-child(1)"))
        .name ("Product Name")
        .build ();

    private final Locator shippingAddress = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.row > div:nth-child(2) > div > div"))
        .name ("Shipping Address")
        .build ();

    private final Locator unitPrice = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.table-responsive > table > tbody > tr > td:nth-child(4)"))
        .name ("Unit Price")
        .build ();
}
