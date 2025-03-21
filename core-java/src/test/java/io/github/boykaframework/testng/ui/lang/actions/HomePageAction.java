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

package io.github.boykaframework.testng.ui.lang.actions;

import static io.github.boykaframework.actions.data.LanguageAction.withLanguage;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.testng.ui.lang.pages.HomePage.homePage;

import io.github.boykaframework.testng.ui.lang.data.LocaleData;

/**
 * Home page specific actions
 *
 * @author Wasiq Bhamla
 * @since 19-Mar-2025
 */
public final class HomePageAction {
    /**
     * Handle Home page actions.
     *
     * @param language Target language
     */
    public static void verifyPageTitle (final String language) {
        withMouse (homePage ().getLanguageSelector ()).hover ();
        withMouse (homePage ().getLanguageOption (language)).click ();

        final var expectedTitle = withLanguage ().getLanguage (LocaleData.class)
            .getTitle ();
        onElement (homePage ().getTitle ()).verifyText ()
            .isEqualTo (expectedTitle);
    }

    private HomePageAction () {
        // Utility class.
    }
}
