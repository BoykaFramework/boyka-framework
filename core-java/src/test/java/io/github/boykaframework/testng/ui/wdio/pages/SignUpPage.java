/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.testng.ui.wdio.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Sign Up page object.
 *
 * @author Wasiq Bhamla
 * @since 21-Oct-2023
 */
@Getter
public class SignUpPage {
    private static final SignUpPage SIGN_UP_PAGE = new SignUpPage ();

    /**
     * Sign Up page object instance.
     *
     * @return {@link SignUpPage}
     */
    public static SignUpPage signUpPage () {
        return SIGN_UP_PAGE;
    }

    private final Locator confirmPassword = Locator.buildLocator ()
        .name ("Confirm Password")
        .android (accessibilityId ("input-repeat-password"))
        .ios (accessibilityId ("input-repeat-password"))
        .build ();
    private final Locator email           = Locator.buildLocator ()
        .name ("Email")
        .android (accessibilityId ("input-email"))
        .ios (accessibilityId ("input-email"))
        .build ();
    private final Locator password        = Locator.buildLocator ()
        .name ("Password")
        .android (accessibilityId ("input-password"))
        .ios (accessibilityId ("input-password"))
        .build ();
    private final Locator signUp          = Locator.buildLocator ()
        .name ("Sign Up button")
        .android (accessibilityId ("button-SIGN UP"))
        .ios (accessibilityId ("button-SIGN UP"))
        .build ();
    private final Locator signUpTab       = Locator.buildLocator ()
        .name ("Sign Up Tab")
        .android (accessibilityId ("button-sign-up-container"))
        .ios (accessibilityId ("button-sign-up-container"))
        .build ();

    private SignUpPage () {
        // Page class.
    }
}
