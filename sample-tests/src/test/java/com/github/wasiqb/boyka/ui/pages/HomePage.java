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

import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Home page objects
 *
 * @author Faisal Khatri
 * @since 31/07/2022
 */
@Getter
public class HomePage {
    private static final HomePage INSTANCE = new HomePage ();

    public static HomePage homePage () {
        return INSTANCE;
    }

    private final Locator loginLink         = Locator.buildLocator ()
        .web (By.linkText ("Login"))
        .name ("Login")
        .build ();
    private final Locator openMyAccountMenu = Locator.buildLocator ()
        .web (By.linkText ("My account"))
        .name ("Open My Account")
        .build ();
    private final Locator registerLink      = Locator.buildLocator ()
        .web (By.linkText ("Register"))
        .name ("Register")
        .build ();
    private final Locator shopByCategory    = Locator.buildLocator ()
        .web (By.linkText ("Shop by Category"))
        .name ("Show By Category")
        .build ();

    public Locator selectCategory (final String linkName) {
        return Locator.buildLocator ()
            .web (By.linkText (linkName))
            .name (format ("Select Category {0}", linkName))
            .build ();
    }
}
