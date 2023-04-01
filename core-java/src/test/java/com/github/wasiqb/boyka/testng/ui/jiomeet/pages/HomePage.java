/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.ui.jiomeet.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Jio Meet Application.
 *
 * @author Wasiq Bhamla
 * @since 24-Mar-2023
 */
@Getter
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator loader       = Locator.buildLocator ()
        .name ("Loader")
        .web (cssSelector ("div.loaderContainer div.loader"))
        .build ();
    private final Locator signIn       = Locator.buildLocator ()
        .name ("Sign In")
        .web (linkText ("Sign In"))
        .build ();
    private final Locator startMeeting = Locator.buildLocator ()
        .name ("Start Meeting")
        .web (cssSelector ("div.quick-link"))
        .filter (e -> e.getText ()
            .equals ("Start a Meeting"))
        .build ();

    private HomePage () {
        // Utility class.
    }
}
