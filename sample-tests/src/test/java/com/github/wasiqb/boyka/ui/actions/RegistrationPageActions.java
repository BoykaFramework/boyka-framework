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

import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.RegistrationPage.registrationPage;

import com.github.wasiqb.boyka.ui.data.RegisterUserData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;

/**
 * Registration page actions
 *
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class RegistrationPageActions {

    private static final String           PASSWORD = "Password@123";
    private final        RegisterUserData registerUserData;

    public RegistrationPageActions () {
        this.registerUserData = TestDataBuilder.getRegisterUserData ();
    }

    public RegistrationSuccessPageActions registerUser () {
        enterText (registrationPage ().getFirstNameField (), this.registerUserData.getFirstName ());
        enterText (registrationPage ().getLastNameField (), this.registerUserData.getLastName ());
        enterText (registrationPage ().getEmailField (), this.registerUserData.getEmail ());
        enterText (registrationPage ().getTelephoneField (), this.registerUserData.getTelephone ());
        enterText (registrationPage ().getPasswordField (), PASSWORD);
        enterText (registrationPage ().getConfirmPasswordField (), PASSWORD);
        clickOn (registrationPage ().getAgreePrivacyPolicyField ());
        clickOn (registrationPage ().getContinueBtn ());
        return new RegistrationSuccessPageActions ();
    }

    public RegistrationPageActions verifyPageHeader () {
        verifyTextOf (registrationPage ().getPageHeader ()).isEqualTo ("Register Account");
        return this;
    }
}
