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

package com.github.wasiqb.boyka.testng.web.saucedemo.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Login page object for Sauce demo application.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
@Getter
public class LoginPage {
    /**
     * Login page object.
     *
     * @return {@link LoginPage}
     */
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginBox    = buildLocator ().web (id ("login_button_container"))
        .build ();
    private final Locator loginButton = buildLocator ().web (id ("login-button"))
        .parent (this.loginBox)
        .build ();
    private final Locator password    = buildLocator ().web (id ("password"))
        .parent (this.loginBox)
        .build ();
    private final Locator username    = buildLocator ().web (id ("user-name"))
        .parent (this.loginBox)
        .build ();

    private LoginPage () {
        // Avoid explicit class initialisation.
    }
}
