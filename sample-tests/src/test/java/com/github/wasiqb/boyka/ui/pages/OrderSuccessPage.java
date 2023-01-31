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

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Order success page objects
 *
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
@Getter
public class OrderSuccessPage {
    private static final OrderSuccessPage INSTANCE = new OrderSuccessPage ();

    public static OrderSuccessPage orderSuccessPage () {
        return INSTANCE;
    }

    private final Locator continueBtn = Locator.buildLocator ()
        .name ("Continue Button")
        .web (By.cssSelector ("#content > div > a"))
        .build ();

    private final Locator successMessage = Locator.buildLocator ()
        .name ("Success Message")
        .web (By.tagName ("h1"))
        .build ();
}
