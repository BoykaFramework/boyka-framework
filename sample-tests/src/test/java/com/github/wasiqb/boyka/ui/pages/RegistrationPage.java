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

package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.ui.data.RegisterUserData;
import com.github.wasiqb.boyka.ui.data.TestDataBuilder;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Registration page objects
 *
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
@Getter
public class RegistrationPage {
    public static RegistrationPage registrationPage () {
        return new RegistrationPage ();
    }

    private final Locator          agreePrivacyPolicyField = Locator.buildLocator ()
        .web (By.cssSelector ("#input-agree +label"))
        .build ();
    private final Locator          continueBtn             = Locator.buildLocator ()
        .web (By.cssSelector ("input.btn-primary"))
        .build ();
    private final Locator          pageHeader              = Locator.buildLocator ()
        .web (By.tagName ("h1"))
        .build ();
    private final RegisterUserData registerUserData;
    private final Locator          registrationForm        = Locator.buildLocator ()
        .web (By.id ("content"))
        .build ();
    private final Locator          lastNameField           = Locator.buildLocator ()
        .web (By.id ("input-lastname"))
        .parent (this.registrationForm)
        .build ();
    private final Locator          firstNameField          = Locator.buildLocator ()
        .web (By.id ("input-firstname"))
        .parent (this.registrationForm)
        .build ();
    private final Locator          emailField              = Locator.buildLocator ()
        .parent (this.registrationForm)
        .web (By.id ("input-email"))
        .build ();
    private final Locator          confirmPasswordField    = Locator.buildLocator ()
        .web (By.id ("input-confirm"))
        .parent (this.registrationForm)
        .build ();
    private final Locator          passwordField           = Locator.buildLocator ()
        .web (By.id ("input-password"))
        .parent (this.registrationForm)
        .build ();
    private final Locator          telephoneField          = Locator.buildLocator ()
        .web (By.id ("input-telephone"))
        .parent (this.registrationForm)
        .build ();

    private RegistrationPage () {
        this.registerUserData = TestDataBuilder.getRegisterUserData ();
    }

}
