/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.enums;

import static io.appium.java_client.remote.AutomationName.ANDROID_UIAUTOMATOR2;
import static io.appium.java_client.remote.AutomationName.IOS_XCUI_TEST;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Automation types for Mobile.
 *
 * @author Wasiq Bhamla
 * @since 06-Sept-2022
 */
@AllArgsConstructor
@Getter
public enum AutomationType {
    /**
     * Android Ui Automator 2 driver.
     */
    UI_AUTOMATOR ("uiautomator2", ANDROID_UIAUTOMATOR2),
    /**
     * iOS XCUITest driver.
     */
    XCUI ("xcuitest", IOS_XCUI_TEST);

    private final String driverName;
    private final String name;
}
