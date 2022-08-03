package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.uitests.pages.RegistrationPage.registrationPage;

import com.github.wasiqb.boyka.uitests.pages.RegistrationSuccessPage;
import com.github.wasiqb.boyka.uitests.testdata.RegisterUserData;
import com.github.wasiqb.boyka.uitests.testdata.TestDataBuilder;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class RegistrationPageActions {

    private static final String           PASSWORD = "Password@123";
    private final        RegisterUserData registerUserData;

    public RegistrationPageActions () {
        this.registerUserData = TestDataBuilder.getRegisterUserData ();
    }

    public RegistrationSuccessPage registerUser () {
        enterText (registrationPage ().getFirstNameField (), this.registerUserData.getFirstName ());
        enterText (registrationPage ().getLastNameField (), this.registerUserData.getLastName ());
        enterText (registrationPage ().getEmailField (), this.registerUserData.getEmail ());
        enterText (registrationPage ().getTelephoneField (), this.registerUserData.getTelephone ());
        enterText (registrationPage ().getPasswordField (), PASSWORD);
        enterText (registrationPage ().getConfirmPasswordField (), PASSWORD);
        hoverOn (registrationPage ().getAgreePrivacyPolicyField ());
        clickOn (registrationPage ().getAgreePrivacyPolicyField ());
        clickOn (registrationPage ().getContinueBtn ());
        return new RegistrationSuccessPage ();
    }

    public void verifyPageHeader () {
        verifyTextOf (registrationPage ().getPageHeader ()).isEqualTo ("Register Account");
    }
}
