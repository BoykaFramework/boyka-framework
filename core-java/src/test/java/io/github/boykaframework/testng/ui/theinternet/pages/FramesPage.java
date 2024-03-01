/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

import static org.openqa.selenium.By.linkText;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Frames pages.
 *
 * @author Wasiq Bhamla
 * @since 23-Jul-2022
 */
@Getter
public class FramesPage {
    private static final FramesPage FRAMES_PAGE = new FramesPage ();

    /**
     * Frames page locators.
     *
     * @return {@link FramesPage} instance
     */
    public static FramesPage framesPage () {
        return FRAMES_PAGE;
    }

    private final Locator frame        = Locator.buildLocator ()
        .web (linkText ("iFrame"))
        .name ("Frame")
        .build ();
    private final Locator nestedFrames = Locator.buildLocator ()
        .web (linkText ("Nested Frames"))
        .name ("Nested Frames")
        .build ();
}
