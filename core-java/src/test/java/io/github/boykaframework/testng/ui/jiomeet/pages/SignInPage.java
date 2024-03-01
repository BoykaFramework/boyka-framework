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

package io.github.boykaframework.testng.ui.jiomeet.pages;

import static org.openqa.selenium.By.id;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

@Getter
public class SignInPage {
    private static final SignInPage SIGN_IN_PAGE = new SignInPage ();

    public static SignInPage signInPage () {
        return SIGN_IN_PAGE;
    }

    private final Locator email         = Locator.buildLocator ()
        .name ("Email")
        .web (id ("username"))
        .build ();
    private final Locator password      = Locator.buildLocator ()
        .name ("Password")
        .web (id ("password"))
        .build ();
    private final Locator proceedButton = Locator.buildLocator ()
        .name ("Proceed Button")
        .web (id ("proceedButton"))
        .build ();
    private final Locator signInButton  = Locator.buildLocator ()
        .name ("Sign In Button")
        .web (id ("signinButton"))
        .build ();

    private SignInPage () {
        // Utility class.
    }
}
