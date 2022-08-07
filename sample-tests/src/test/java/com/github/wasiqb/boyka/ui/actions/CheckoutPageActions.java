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

package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.DropDownActions.selectByText;
import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.ui.pages.CheckoutPage.checkoutPage;

import com.github.wasiqb.boyka.ui.data.BillingData;

/**
 * Checkoout page actions
 *
 * @author Faisal Khatri
 * @since 30/07/2022
 */
public class CheckoutPageActions {

    public static CheckoutPageActions checkoutPageActions () {
        return new CheckoutPageActions ();
    }

    public ConfirmOrderPageActions checkoutProduct () {
        clickOn (checkoutPage ().getAgreeTermsAndConditionsField ());
        clickOn (checkoutPage ().getContinueBtn ());
        return new ConfirmOrderPageActions ();
    }

    public CheckoutPageActions setBillingAddress (final BillingData billingData) {
        enterText (checkoutPage ().getFirstNameField (), billingData.getFirstName ());
        enterText (checkoutPage ().getLastNameField (), billingData.getLastName ());
        enterText (checkoutPage ().getAddressLineOneField (), billingData.getAddressLineOne ());
        enterText (checkoutPage ().getCityField (), billingData.getCity ());
        enterText (checkoutPage ().getPostCodeField (), billingData.getPostCode ());
        selectByText (checkoutPage ().getCountryField (), billingData.getCountry ());
        selectByText (checkoutPage ().getStateField (), billingData.getState ());
        return this;
    }

    public String textOfUnitPriceOfCameraLens () {
        return textOf (checkoutPage ().getGetUnitPriceOfCameraLens ());
    }
}
