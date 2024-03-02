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

package io.github.boykaframework.testng.ui.theinternet.pages;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.partialLinkText;
import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Login page object
 *
 * @author Wasiq Bhamla
 * @since 16-Sept-2023
 */
@Getter
public class LoginPage {
    private static final LoginPage LOGIN_PAGE = new LoginPage ();

    /**
     * Login page instance.
     *
     * @return {@link LoginPage}
     */
    public static LoginPage loginPage () {
        return LOGIN_PAGE;
    }

    private final Locator login    = Locator.buildLocator ()
        .name ("Login Button")
        .web (tagName ("button"))
        .build ();
    private final Locator logout   = Locator.buildLocator ()
        .name ("Logout Button")
        .web (partialLinkText ("Logout"))
        .build ();
    private final Locator message  = Locator.buildLocator ()
        .name ("Message Label")
        .web (id ("flash"))
        .build ();
    private final Locator password = Locator.buildLocator ()
        .name ("Password")
        .web (id ("password"))
        .build ();
    private final Locator userName = Locator.buildLocator ()
        .name ("User Name")
        .web (id ("username"))
        .build ();

    private LoginPage () {
        // Utility Class.
    }
}
