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

package io.github.boykaframework.testng.ui.wdio.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Swipe Page.
 *
 * @author Wasiq Bhamla
 * @since 19-Dec-2024
 */
@Getter
public class SwipePage {
    private static final SwipePage SWIPE_PAGE = new SwipePage ();

    /**
     * Swipe page instance.
     *
     * @return Instance
     */
    public static SwipePage swipePage () {
        return SWIPE_PAGE;
    }

    private final Locator carousal    = Locator.buildLocator ()
        .name ("Carousel")
        .android (accessibilityId ("Carousel"))
        .build ();
    private final Locator logo        = Locator.buildLocator ()
        .name ("Hidden Logo")
        .android (accessibilityId ("WebdriverIO logo"))
        .build ();
    private final Locator swipeScreen = Locator.buildLocator ()
        .name ("Swipe screen")
        .android (accessibilityId ("Swipe-screen"))
        .build ();

    private SwipePage () {
        // Singleton class.
    }
}
