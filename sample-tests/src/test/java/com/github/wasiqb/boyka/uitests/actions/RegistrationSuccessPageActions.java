package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.uitests.pages.RegistrationSuccessPage.registrationSuccessPage;

import com.github.wasiqb.boyka.uitests.pages.MyAccountPage;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class RegistrationSuccessPageActions {

    public MyAccountPage continueToMyAccount () {
        clickOn (registrationSuccessPage ().getContinueBtn ());
        return new MyAccountPage ();
    }

    public void verifySuccessfulRegistration () {
        verifyTextOf (registrationSuccessPage ().getPageHeader ()).isEqualTo ("Your Account Has Been Created!");
    }
}
