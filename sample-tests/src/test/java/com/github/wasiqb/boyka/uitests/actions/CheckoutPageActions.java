package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;
import static com.github.wasiqb.boyka.uitests.pages.CheckoutPage.checkoutPage;

import com.github.wasiqb.boyka.uitests.pages.ConfirmOrderPage;

/**
 * @author Faisal Khatri
 * @since 30/07/2022
 */
public class CheckoutPageActions {

    public ConfirmOrderPage checkoutProduct () {
        hoverOn (checkoutPage ().getAgreeTermsAndConditionsField ());
        clickOn (checkoutPage ().getAgreeTermsAndConditionsField ());
        clickOn (checkoutPage ().getContinueBtn ());
        return ConfirmOrderPage.confirmOrderPage ();
    }

    public CheckoutPageActions setBillingAddress (final BillingData billingData) {
        enterText (checkoutPage ().getFirstNameField (), billingData.getFirstName ());
        enterText (checkoutPage ().getLastNameField (), billingData.getLastName ());
        enterText (checkoutPage ().getAddressLineOneField (), billingData.getAddressLineOne ());
        enterText (checkoutPage ().getCityField (), billingData.getCity ());
        enterText (checkoutPage ().getPostCodeField (), billingData.getPostCode ());
        checkoutPage ().getCountryField ()
            .selectByVisibleText (billingData.getCountry ()); // To Do
        checkoutPage ().getStateField ()
            .selectByVisibleText (billingData.getState ()); // To Do
        return this;
    }

    public void verifyUnitPriceOfCameraLens () {
        textOf (checkoutPage ().getGetUnitPriceOfCameraLens ());
    }
}
