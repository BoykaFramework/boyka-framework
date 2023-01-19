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

package com.github.wasiqb.boyka.testng.ui.saucedemo.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Drawing page screen.
 *
 * @author Wasiq Bhamla
 * @since 05-Nov-2022
 */
@Getter
public class DrawingPage {
    /**
     * Drawing page instance
     *
     * @return instance of the page
     */
    public static DrawingPage drawingPage () {
        return new DrawingPage ();
    }

    private final Locator clearButton = Locator.buildLocator ()
        .name ("Clear Button")
        .android (accessibilityId ("test-CLEAR"))
        .ios (accessibilityId ("test-CLEAR"))
        .build ();

    private DrawingPage () {
        // Utility class.
    }
}
