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

package io.github.boykaframework.testng.ui.excalidraw.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Excalidraw Page.
 *
 * @author Wasiq Bhamla
 * @since 10-Jan-2025
 */
@Getter
public class HomePage {
    private static final HomePage HOME_PAGE = new HomePage ();

    /**
     * Home page instance.
     *
     * @return Home page
     */
    public static HomePage homePage () {
        return HOME_PAGE;
    }

    private final Locator canvas     = Locator.buildLocator ()
        .name ("Canvas")
        .web (tagName ("canvas"))
        .build ();
    private final Locator drawButton = Locator.buildLocator ()
        .name ("Draw Button")
        .web (cssSelector ("input[data-testid=\"toolbar-freedraw\"]"))
        .build ();

    private HomePage () {
        // Singleton class.
    }
}
