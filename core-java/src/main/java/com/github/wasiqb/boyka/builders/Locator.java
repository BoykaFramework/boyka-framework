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

package com.github.wasiqb.boyka.builders;

import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;

import java.util.function.Predicate;

import com.github.wasiqb.boyka.enums.ApplicationType;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * UI application locator.
 *
 * @author Wasiq Bhamla
 * @since 25-Feb-2022
 */
@ToString
@Getter
@Builder (builderMethodName = "buildLocator")
public class Locator {
    private By                    android;
    private Predicate<WebElement> filter;
    private int                   index;
    private By                    ios;
    @NotNull
    private String                name;
    private Locator               parent;
    private By                    web;

    /**
     * Gets the platform specific locator
     *
     * @return Locator for the element
     */
    public By getLocator () {
        switch (getSession ().getPlatformType ()) {
            case ANDROID:
                return getMobileOrWebLocator (this.android);
            case IOS:
                return getMobileOrWebLocator (this.ios);
            case WEB:
            default:
                return this.web;
        }
    }

    private By getMobileOrWebLocator (final By locator) {
        if (getSession ().getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == ApplicationType.WEB) {
            return this.web;
        }
        return locator;
    }
}
