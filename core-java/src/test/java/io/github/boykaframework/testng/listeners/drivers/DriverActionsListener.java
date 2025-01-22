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

package io.github.boykaframework.testng.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import java.time.Duration;

import io.github.boykaframework.actions.interfaces.listeners.drivers.IDriverActionsListener;

public class DriverActionsListener implements IDriverActionsListener {
    @Override
    public void onExecuteScript (final String script, final Object... args) {
        step (format ("Executing Script [{0}] with args [{1}]...", script, args));
    }

    @Override
    public void onPause (final Duration time) {
        step (format ("Pausing till [{0}]...", time));
    }

    @Override
    public void onSaveLogs () {
        step ("Saving all the available logs...");
    }

    @Override
    public void onWaitUntil () {
        step ("Waiting until a specific conditions...");
    }
}
