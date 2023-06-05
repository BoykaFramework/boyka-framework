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

package com.github.wasiqb.boyka.actions.device;

import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.enums.ListenerType.ANDROID_DEVICE_ACTION;
import static com.github.wasiqb.boyka.enums.Message.ACTION_NOT_SUPPORTED_ON_PLATFORM;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.device.IAndroidDeviceActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.device.IAndroidDeviceActionsListener;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.apache.logging.log4j.Logger;

public class AndroidDeviceActions extends DeviceActions implements IAndroidDeviceActions {
    private static final IAndroidDeviceActions DEVICE_ACTIONS = new AndroidDeviceActions ();
    private static final Logger                LOGGER         = getLogger ();

    public static IAndroidDeviceActions onAndroidDevice () {
        return DEVICE_ACTIONS;
    }

    private final IAndroidDeviceActionsListener listener;

    private AndroidDeviceActions () {
        this.listener = getSession ().getListener (ANDROID_DEVICE_ACTION);
    }

    @Override
    public void pressKey (final AndroidKey key) {
        LOGGER.traceEntry ();
        final var platform = getSession ().getPlatformType ();
        if (platform != ANDROID) {
            throwError (ACTION_NOT_SUPPORTED_ON_PLATFORM, "Press key", platform);
        }
        LOGGER.info ("Pressing key [{}] on Android device...", key);
        ofNullable (this.listener).ifPresent (l -> l.onPressKey (key));
        performDriverAction ((AndroidDriver d) -> d.pressKey (new KeyEvent ().withKey (key)));
        LOGGER.traceExit ();
    }
}
