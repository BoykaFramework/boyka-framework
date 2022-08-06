package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.ConfirmOrderPage.confirmOrderPage;

import java.text.MessageFormat;

import com.github.wasiqb.boyka.ui.data.BillingData;

/**
 * Confirm Order page actions
 * @author Faisal Khatri
 * @since 30/07/2022
 */
public class ConfirmOrderPageActions {

    public static ConfirmOrderPageActions confirmOrderPageActions () {
        return new ConfirmOrderPageActions ();
    }

    public OrderSuccessPageActions confirmOrder () {
        clickOn (confirmOrderPage ().getConfirmOrderBtn ());
        return new OrderSuccessPageActions ();
    }

    public ConfirmOrderPageActions verifyPageHeader () {
        verifyTextOf (confirmOrderPage ().getPageHeader ()).isEqualTo ("Confirm Order");
        return this;
    }

    public ConfirmOrderPageActions verifyProductName () {
        verifyTextOf (confirmOrderPage ().getProductName ()).isEqualTo ("Palm Treo Pro");
        return this;
    }

    public ConfirmOrderPageActions verifyShippingAddress (final BillingData billingData) {
        final String expectedShippingAddress = "{0} {1}\n{2}\n{3} {4}\n{5},{6}";
        verifyTextOf (confirmOrderPage ().getShippingAddress ()).isEqualTo (
            MessageFormat.format (expectedShippingAddress, billingData.getFirstName (), billingData.getLastName (),
                billingData.getAddressLineOne (), billingData.getCity (), billingData.getPostCode (),
                billingData.getState (), billingData.getCountry ()));
        return this;
    }

    public ConfirmOrderPageActions verifyUnitPrice (final String unitPrice) {
        verifyTextOf (confirmOrderPage ().getUnitPrice ()).isEqualTo (unitPrice);
        return this;
    }
}
