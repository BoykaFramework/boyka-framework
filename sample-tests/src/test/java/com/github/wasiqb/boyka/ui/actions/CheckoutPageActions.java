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

import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.DropDownActions.onDropDown;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
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
        withMouse (checkoutPage ().getAgreeTermsAndConditionsField ()).click ();
        withMouse (checkoutPage ().getContinueBtn ()).click ();
        return new ConfirmOrderPageActions ();
    }

    public CheckoutPageActions setBillingAddress (final BillingData billingData) {
        onTextBox (checkoutPage ().getFirstNameField ()).enterText (billingData.getFirstName ());
        onTextBox (checkoutPage ().getLastNameField ()).enterText (billingData.getLastName ());
        onTextBox (checkoutPage ().getAddressLineOneField ()).enterText (billingData.getAddressLineOne ());
        onTextBox (checkoutPage ().getCityField ()).enterText (billingData.getCity ());
        onTextBox (checkoutPage ().getPostCodeField ()).enterText (billingData.getPostCode ());
        onDropDown (checkoutPage ().getCountryField ()).selectByText (billingData.getCountry ());
        onDropDown (checkoutPage ().getStateField ()).selectByText (billingData.getState ());
        return this;
    }

    public String textOfUnitPriceOfCameraLens () {
        return onElement (checkoutPage ().getGetUnitPriceOfCameraLens ()).getText ();
    }
}
