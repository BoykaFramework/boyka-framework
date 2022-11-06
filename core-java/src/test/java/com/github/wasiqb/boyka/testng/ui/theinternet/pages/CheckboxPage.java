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

package com.github.wasiqb.boyka.testng.ui.theinternet.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.cssSelector;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Checkbox page.
 *
 * @author Wasiq Bhamla
 * @since 05-Aug-2022
 */
@Getter
public class CheckboxPage {
    private static final CheckboxPage CHECKBOX_PAGE = new CheckboxPage ();

    /**
     * Checkbox page instance.
     *
     * @return {@link CheckboxPage}
     */
    public static CheckboxPage checkboxPage () {
        return CHECKBOX_PAGE;
    }

    private final Locator option1 = buildLocator ().web (cssSelector ("form#checkboxes input"))
        .name ("Option 1")
        .build ();
    private final Locator option2 = buildLocator ().web (cssSelector ("form#checkboxes input"))
        .name ("Option 2")
        .index (1)
        .build ();
}
