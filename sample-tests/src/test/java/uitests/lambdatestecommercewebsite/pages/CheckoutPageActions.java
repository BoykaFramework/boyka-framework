package uitests.lambdatestecommercewebsite.pages;

import static uitests.lambdatestecommercewebsite.pages.CheckoutPage.checkoutPage;


import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.;

/**
 * Created By Faisal Khatri on 29-07-2022
 */
public class CheckoutPageActions {

    public void verifyUnitPriceOfCameraLens() {
        verifyTextOf (checkoutPage ().getGetUnitPriceOfCameraLens ());

    }

    public CheckoutPageActions setBillingAddress (final BillingData billingData) {
        enterText (checkoutPage ().getFirstNameField (), billingData.getFirstName ());
        enterText (checkoutPage ().getLastNameField (), billingData.getLastName ());
        enterText (checkoutPage ().getAddressLineOneField (), billingData.getAddressLineOne ());
        enterText (checkoutPage ().getCityField (), billingData.getCity ());
        enterText (checkoutPage ().getPostCodeField (), billingData.getPostCode ());
        checkoutPage ().getCountryField ().selectByVisibleText (billingData.getCountry ()); // To Do
        checkoutPage ().getStateField ().selectByVisibleText (billingData.getState ()); // To Do
        return this;
    }

    public ConfirmOrderPage checkoutProduct () {
        hoverOn(checkoutPage ().getAgreeTermsAndConditionsField ());
        clickOn (checkoutPage ().getAgreeTermsAndConditionsField ());
        clickOn (checkoutPage ().getContinueBtn ());
        return ConfirmOrderPage.confirmOrderPage ();
    }


}
