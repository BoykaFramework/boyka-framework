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

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;

import com.github.wasiqb.boyka.builders.Locator;

/**
 * Hover mouse page.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
public class HoverPage {
    /**
     * Hover mouse page instance.
     */
    public static HoverPage hoverPage () {
        return new HoverPage ();
    }

    /**
     * User image locator.
     *
     * @param index Image index
     *
     * @return User image locator
     */
    public Locator userImage (final int index) {
        return buildLocator ().web (className ("figure"))
            .index (index)
            .build ();
    }

    /**
     * Username locator.
     *
     * @param index Image index
     *
     * @return Username locator
     */
    public Locator userName (final int index) {
        return buildLocator ().web (cssSelector ("div.figcaption h5"))
            .parent (userImage (index))
            .build ();
    }
}
