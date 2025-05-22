/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

package io.github.boykaframework.testng.ui.lang.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.partialLinkText;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * WDIO Home page
 *
 * @author Wasiq Bhamla
 * @since 19-Mar-2025
 */
@Getter
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Gets the Home page instead.
     *
     * @return Home page
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator languageSelector = Locator.buildLocator ()
        .name ("Language Selector")
        .web (cssSelector ("nav div.dropdown:nth-child(2)"))
        .build ();
    private final Locator title            = Locator.buildLocator ()
        .name ("Page Title")
        .web (cssSelector ("header h1 + p"))
        .build ();

    private HomePage () {
        // Singleton class.
    }

    /**
     * Gets the language option.
     *
     * @param language Target language
     *
     * @return Locator of the Language option
     */
    public Locator getLanguageOption (final String language) {
        return Locator.buildLocator ()
            .name (language)
            .web (partialLinkText (language))
            .build ();
    }
}
