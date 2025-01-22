/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.ui.theinternet.pages;

import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Nested Frames pages.
 *
 * @author Wasiq Bhamla
 * @since 25-Jul-2022
 */
@Getter
public class NestedFramePage {
    /**
     * Nested Frames page.
     *
     * @return {@link NestedFramePage}
     */
    public static NestedFramePage nestedFramePage () {
        return new NestedFramePage ();
    }

    private final Locator body        = Locator.buildLocator ()
        .web (tagName ("body"))
        .name ("Body")
        .build ();
    private final String  frameBottom = "frame-bottom";
    private final String  frameLeft   = "frame-left";
    private final String  frameMiddle = "frame-middle";
    private final String  frameRight  = "frame-right";
    private final String  frameTop    = "frame-top";
}
