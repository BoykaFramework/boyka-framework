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
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
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
        onTextBox (registrationPage ().getFirstNameField ()).enterText (this.registerUserData.getFirstName ());
        onTextBox (registrationPage ().getLastNameField ()).enterText (this.registerUserData.getLastName ());
        onTextBox (registrationPage ().getEmailField ()).enterText (this.registerUserData.getEmail ());
        onTextBox (registrationPage ().getTelephoneField ()).enterText (this.registerUserData.getTelephone ());
        onTextBox (registrationPage ().getPasswordField ()).enterText (PASSWORD);
        onTextBox (registrationPage ().getConfirmPasswordField ()).enterText (PASSWORD);
        withMouse (registrationPage ().getAgreePrivacyPolicyField ()).click ();
        withMouse (registrationPage ().getContinueBtn ()).click ();
        return new RegistrationSuccessPageActions ();
    }

    public RegistrationPageActions verifyPageHeader () {
        onElement (registrationPage ().getPageHeader ()).verifyText ()
            .isEqualTo ("Register Account");
        return this;
    }
}
