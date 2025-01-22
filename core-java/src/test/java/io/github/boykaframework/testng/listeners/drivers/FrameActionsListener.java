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

import io.github.boykaframework.actions.interfaces.listeners.drivers.IFrameActionsListener;

public class FrameActionsListener implements IFrameActionsListener {
    @Override
    public void onSwitchTo (final String frameNameOrId) {
        step (format ("Switching to Frame ID [{0}]...", frameNameOrId));
    }

    @Override
    public void onSwitchTo (final int index) {
        step (format ("Switching to Frame on index [{0}]...", index));
    }

    @Override
    public void onSwitchToParent () {
        step ("Switching to Parent Frame...");
    }
}
