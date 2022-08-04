package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.RegistrationSuccessPage.registrationSuccessPage;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class RegistrationSuccessPageActions {

    public MyAccountPageActions continueToMyAccount () {
        clickOn (registrationSuccessPage ().getContinueBtn ());
        return new MyAccountPageActions ();
    }

    public RegistrationSuccessPageActions verifySuccessfulRegistration () {
        verifyTextOf (registrationSuccessPage ().getPageHeader ()).isEqualTo ("Your Account Has Been Created!");
        return this;
    }
}
