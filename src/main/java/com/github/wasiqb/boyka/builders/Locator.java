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

import static com.github.wasiqb.boyka.enums.Messages.APP_TYPE_NOT_SUPPORT_DRIVERS;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;

import com.github.wasiqb.boyka.exception.FrameworkError;
import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * UI application locator.
 *
 * @author Wasiq Bhamla
 * @since 25-Feb-2022
 */
@Getter
@Builder (builderMethodName = "createLocator", buildMethodName = "create")
public class Locator {
    private By      android;
    private By      ios;
    private Locator parent;
    private By      web;

    /**
     * Returns locator specific to application type.
     *
     * @return {@link By}
     */
    public By getLocator () {
        switch (getSession ().getApplicationType ()) {
            case ANDROID:
                return this.android;
            case IOS:
                return this.ios;
            case WEB:
                return this.web;
            case API:
            default:
                throw new FrameworkError (APP_TYPE_NOT_SUPPORT_DRIVERS.getMessage ());
        }
    }
}