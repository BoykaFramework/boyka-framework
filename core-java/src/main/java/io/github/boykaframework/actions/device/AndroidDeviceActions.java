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

package io.github.boykaframework.actions.device;

import static com.google.common.truth.Truth.assertWithMessage;
import static io.github.boykaframework.actions.CommonActions.getDriverAttribute;
import static io.github.boykaframework.actions.CommonActions.performDriverAction;
import static io.github.boykaframework.enums.ListenerType.ANDROID_DEVICE_ACTION;
import static io.github.boykaframework.enums.Message.ACTION_NOT_SUPPORTED_ON_PLATFORM;
import static io.github.boykaframework.enums.PlatformType.ANDROID;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.File;
import java.io.IOException;

import com.google.common.truth.StringSubject;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.HasNotifications;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.clipboard.HasClipboard;
import io.github.boykaframework.actions.interfaces.device.IAndroidDeviceActions;
import io.github.boykaframework.actions.interfaces.listeners.device.IAndroidDeviceActionsListener;
import org.apache.logging.log4j.Logger;

/**
 * Handles all Android device specific actions.
 *
 * @author Wasiq Bhamla
 * @since 06-Jun-2023
 */
public class AndroidDeviceActions extends DeviceActions implements IAndroidDeviceActions {
    private static final IAndroidDeviceActions DEVICE_ACTIONS = new AndroidDeviceActions ();
    private static final Logger                LOGGER         = getLogger ();

    /**
     * Handles Android device specific actions.
     *
     * @return {@link IAndroidDeviceActions} instance
     */
    public static IAndroidDeviceActions onAndroidDevice () {
        return DEVICE_ACTIONS;
    }

    private final IAndroidDeviceActionsListener listener;

    private AndroidDeviceActions () {
        this.listener = getSession ().getListener (ANDROID_DEVICE_ACTION);
    }

    @Override
    public String getClipboardText () {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Getting the Clipboard text from Android device...");
        ofNullable (this.listener).ifPresent (IAndroidDeviceActionsListener::onGetClipboardText);
        return getClipboard ();
    }

    @Override
    public void openNotification () {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Opening Notification panel on the Android device...");
        ofNullable (this.listener).ifPresent (IAndroidDeviceActionsListener::onOpenNotification);
        performDriverAction (HasNotifications::openNotifications);
        LOGGER.traceExit ();
    }

    @Override
    public void pressKey (final AndroidKey key) {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Pressing key [{}] on Android device...", key);
        ofNullable (this.listener).ifPresent (l -> l.onPressKey (key));
        performDriverAction ((AndroidDriver d) -> d.pressKey (new KeyEvent ().withKey (key)));
        LOGGER.traceExit ();
    }

    @Override
    public byte[] pullFile (final String targetFile) {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Pulling file [{}] from Android device...", targetFile);
        ofNullable (this.listener).ifPresent (l -> l.onPullFile (targetFile));
        return getDriverAttribute ((final AndroidDriver d) -> d.pullFile (targetFile), null);
    }

    @Override
    public boolean putFile (final File sourceFile, final String destinationPath) {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Putting file [{}] on Android device path [{}]...", sourceFile.getName (), destinationPath);
        ofNullable (this.listener).ifPresent (l -> l.onPutFile (sourceFile, destinationPath));
        return getDriverAttribute ((final AndroidDriver d) -> {
            try {
                d.pushFile (destinationPath, sourceFile);
                return true;
            } catch (final IOException e) {
                return false;
            }
        }, false);
    }

    @Override
    public void setClipboardText (final String text) {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Setting the Clipboard text [{}] on the Android device...", text);
        ofNullable (this.listener).ifPresent (l -> l.onSetClipboardText (text));
        performDriverAction ((AndroidDriver d) -> d.setClipboardText (text));
        LOGGER.traceExit ();
    }

    @Override
    public StringSubject verifyClipboardText () {
        LOGGER.traceEntry ();
        isActionSupported ();
        LOGGER.info ("Verifying the Clipboard text from Android device...");
        ofNullable (this.listener).ifPresent (IAndroidDeviceActionsListener::onVerifyClipboardText);
        return assertWithMessage ("Clipboard Text").that (getClipboard ());
    }

    private String getClipboard () {
        return getDriverAttribute (HasClipboard::getClipboardText, null);
    }

    private void isActionSupported () {
        final var platform = getSession ().getPlatformType ();
        if (platform != ANDROID) {
            throwError (ACTION_NOT_SUPPORTED_ON_PLATFORM, "Press key", platform);
        }
    }
}
