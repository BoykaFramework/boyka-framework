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

import static com.github.wasiqb.boyka.enums.Message.APP_TYPE_NOT_SUPPORT_DRIVERS;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.function.Predicate;

import com.github.wasiqb.boyka.exception.FrameworkError;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.Logger;
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
    private static final Logger LOGGER = getLogger ();

    private By                    android;
    private Predicate<WebElement> filter;
    private int                   index;
    private By                    ios;
    private Locator               parent;
    private By                    web;

    /**
     * Returns locator specific to application type.
     *
     * @return {@link By}
     */
    public By getLocator () {
        LOGGER.traceEntry ();
        final By result;
        switch (getSession ().getApplicationType ()) {
            case ANDROID:
                result = this.android;
                break;
            case IOS:
                result = this.ios;
                break;
            case WEB:
                result = this.web;
                break;
            case API:
            default:
                throw new FrameworkError (APP_TYPE_NOT_SUPPORT_DRIVERS.getMessageText ());
        }
        return LOGGER.traceExit (result);
    }
}
