package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.DropDownActions.selectByText;
import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.ui.pages.CheckoutPage.checkoutPage;

import com.github.wasiqb.boyka.ui.data.BillingData;

/**
 * Checkoout page actions
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
