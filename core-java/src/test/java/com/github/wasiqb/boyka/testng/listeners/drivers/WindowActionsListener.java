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

package com.github.wasiqb.boyka.testng.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IWindowActionsListener;
import org.openqa.selenium.WindowType;

public class WindowActionsListener implements IWindowActionsListener {
    @Override
    public void onClose () {
        step ("Closing the window...");
    }

    @Override
    public void onCurrentHandle () {
        step ("Getting the current page URL...");
    }

    @Override
    public void onFullScreen () {
        step ("Making current window as Full screen...");
    }

    @Override
    public void onGetTitle () {
        step ("Getting the current page title...");
    }

    @Override
    public void onHandles () {
        step ("Getting all the windows handle list...");
    }

    @Override
    public void onMaximize () {
        step ("Maximizing the current window...");
    }

    @Override
    public void onMinimize () {
        step ("Minimizing the current window...");
    }

    @Override
    public void onSwitchTo (final String nameOfHandle) {
        step (format ("Switching to the window of [{0}] handle...", nameOfHandle));
    }

    @Override
    public void onSwitchToDefault () {
        step ("Switching to the default window...");
    }

    @Override
    public void onSwitchToNew (final WindowType windowType) {
        step ("Switching to the new window...");
    }

    @Override
    public void onTakeScreenshot (final String fileName) {
        step ("Taking screenshot of the current page...");
    }

    @Override
    public void onVerifyTitle () {
        step ("Verifying title of the current page...");
    }

    @Override
    public void onViewportSize () {
        step ("Getting screen viewport size...");
    }
}
