/*
 * MIT License
 *
 * Copyright (c) 2022, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.ui.pages.theinternet;

import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.By.tagName;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Multi window page.
 *
 * @author Wasiq Bhamla
 * @since 16-Jul-2022
 */
@Getter
public class MultiWindowPage {
    /**
     * Multi window page instance.
     *
     * @return {@link MultiWindowPage}
     */
    public static MultiWindowPage multiWindowPage () {
        return new MultiWindowPage ();
    }

    private final Locator clickHere = Locator.buildLocator ()
        .web (linkText ("Click Here"))
        .build ();
    private final Locator title     = Locator.buildLocator ()
        .web (tagName ("h3"))
        .build ();
}
