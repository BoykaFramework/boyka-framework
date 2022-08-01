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

package com.github.wasiqb.boyka.testng.web.theinternet.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Double click page.
 *
 * @author Wasiq Bhamla
 * @since 27-Jul-2022
 */
@Getter
public class DoubleClickPage {
    /**
     * Double click page instance.
     */
    public static DoubleClickPage doubleClickPage () {
        return new DoubleClickPage ();
    }

    private final Locator doubleClick = buildLocator ().web (id ("double-click"))
        .build ();
}
