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

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Checkout page objects
 *
 * @author Faisal Khatri
 * @since 29/07/2022
 */
@Getter
public class CheckoutPage {

    public static CheckoutPage checkoutPage () {
        return new CheckoutPage ();
    }

    private final Locator agreeTermsAndConditionsField = buildLocator ().web (By.cssSelector ("#input-agree +label"))
        .build ();
    private final Locator continueBtn                  = buildLocator ().web (By.cssSelector ("button#button-save"))
        .build ();
    private final Locator getUnitPriceOfCameraLens     = buildLocator ().web (
            By.cssSelector ("#checkout-total > tbody > tr:nth-child(1) > td.text-right"))
        .build ();
    private final Locator paymentAddressForm           = buildLocator ().web (By.id ("payment-address"))
        .build ();
    private final Locator firstNameField               = buildLocator ().web (By.id ("input-payment-firstname"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator lastNameField                = buildLocator ().web (By.id ("input-payment-lastname"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator addressLineOneField          = buildLocator ().web (By.id ("input-payment-address-1"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator cityField                    = buildLocator ().web (By.id ("input-payment-city"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator countryField                 = buildLocator ().web (By.id ("input-payment-country"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator postCodeField                = buildLocator ().web (By.id ("input-payment-postcode"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator stateField                   = buildLocator ().web (By.id ("input-payment-zone"))
        .parent (this.paymentAddressForm)
        .build ();

}
