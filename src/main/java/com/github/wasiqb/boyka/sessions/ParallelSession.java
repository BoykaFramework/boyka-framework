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

package com.github.wasiqb.boyka.sessions;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import org.openqa.selenium.WebDriver;

public final class ParallelSession {
    private static final ThreadLocal<DriverSession<? extends WebDriver>> SESSION = new ThreadLocal<> ();

    public static void clearSession () {
        SESSION.remove ();
    }

    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> DriverSession<D> getSession () {
        return (DriverSession<D>) SESSION.get ();
    }

    public static <D extends WebDriver> void setDriver (final ApplicationType applicationType, final D driver,
        final FrameworkSetting setting) {
        SESSION.set (new DriverSession<> (applicationType, driver, setting));
    }

    private ParallelSession () {
        // Utility class
    }
}
