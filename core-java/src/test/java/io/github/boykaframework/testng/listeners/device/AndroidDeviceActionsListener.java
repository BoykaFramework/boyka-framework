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

package io.github.boykaframework.testng.listeners.device;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.github.boykaframework.actions.interfaces.listeners.device.IAndroidDeviceActionsListener;

public class AndroidDeviceActionsListener implements IAndroidDeviceActionsListener {
    @Override
    public void onPressKey (final AndroidKey key) {
        step (format ("Pressing [{0}] key on the device...", key));
    }
}
