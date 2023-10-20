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

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.enums.DeviceType.CLOUD;
import static com.github.wasiqb.boyka.enums.ListenerType.DEVICE_ACTION;
import static com.github.wasiqb.boyka.enums.Message.NO_KEYBOARD_ERROR;
import static com.github.wasiqb.boyka.enums.Message.RECORDING_NOT_SUPPORTED;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.enums.PlatformType.IOS;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static com.github.wasiqb.boyka.utils.VideoUtil.saveVideo;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.device.IDeviceActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.device.IDeviceActionsListener;
import com.github.wasiqb.boyka.config.ui.mobile.device.VideoSetting;
import com.github.wasiqb.boyka.enums.PlatformType;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.android.AndroidStopScreenRecordingOptions;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.ios.IOSStopScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import org.apache.logging.log4j.Logger;

/**
 * Handle all device specific actions.
 *
 * @author Wasiq Bhamla
 * @since 31-May-2023
 */
public class DeviceActions implements IDeviceActions {
    private static final IDeviceActions DEVICE_ACTIONS = new DeviceActions ();
    private static final Logger         LOGGER         = getLogger ();

    /**
     * Handles all device specific actions.
     *
     * @return {@link IDeviceActions} instance object
     */
    public static IDeviceActions onDevice () {
        return DEVICE_ACTIONS;
    }

    private final IDeviceActionsListener listener;

    protected DeviceActions () {
        this.listener = getSession ().getListener (DEVICE_ACTION);
    }

    @Override
    public void hideKeyboard () {
        LOGGER.info ("Hiding the visible keyboard...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onHideKeyboard);
        checkKeyboardSupported ();
        if (isKeyboardVisible ()) {
            performDriverAction (HidesKeyboard::hideKeyboard);
        }
    }

    @Override
    public boolean isKeyboardVisible () {
        LOGGER.info ("Checking if keyboard is visible...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onIsKeyboardVisible);
        checkKeyboardSupported ();
        return getDriverAttribute (HasOnScreenKeyboard::isKeyboardShown, false);
    }

    @Override
    public void startRecording () {
        final var platform = getSession ().getPlatformType ();
        checkVideoSupported (platform);
        final var mobileSetting = getSession ().getMobileSetting ()
            .getDevice ();
        final var setting = mobileSetting.getVideo ();
        if (setting.isEnabled () && mobileSetting.getType () != CLOUD) {
            final var screen = (CanRecordScreen) getSession ().getDriver ();
            startRecording (screen, setting, platform);
        }
    }

    @Override
    public void stopRecording () {
        final var platform = getSession ().getPlatformType ();
        checkVideoSupported (platform);
        final var mobileSetting = getSession ().getMobileSetting ()
            .getDevice ();
        final var setting = mobileSetting.getVideo ();
        if (setting.isEnabled () && mobileSetting.getType () != CLOUD) {
            final var screen = (CanRecordScreen) getSession ().getDriver ();
            final var content = stopRecording (screen, platform);
            saveVideo (content);
        }
    }

    private void checkKeyboardSupported () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
    }

    private void checkVideoSupported (final PlatformType platform) {
        if (platform != ANDROID && platform != IOS) {
            throwError (RECORDING_NOT_SUPPORTED, platform);
        }
    }

    private AndroidStartScreenRecordingOptions getAndroidRecordOptions (final VideoSetting setting) {
        final var androidSetting = setting.getAndroid ();
        final var options = AndroidStartScreenRecordingOptions.startScreenRecordingOptions ();
        if (!isNull (androidSetting)) {
            options.withBitRate (androidSetting.getBitRate () * 100000);
        }
        options.withVideoSize (setting.getSize ())
            .withTimeLimit (ofSeconds (setting.getTimeLimit ()));
        return options;
    }

    private IOSStartScreenRecordingOptions getIOSRecordOptions (final VideoSetting setting) {
        final var iosSetting = setting.getIos ();
        final var options = IOSStartScreenRecordingOptions.startScreenRecordingOptions ();
        if (!isNull (iosSetting)) {
            options.withFps (iosSetting.getFps ())
                .withVideoQuality (iosSetting.getQuality ())
                .withVideoType (iosSetting.getCodec ());
        }
        options.withTimeLimit (ofSeconds (setting.getTimeLimit ()));
        if (!isNull (setting.getSize ())) {
            options.withVideoScale (setting.getSize ());
        }
        return options;
    }

    private void startRecording (final CanRecordScreen screen, final VideoSetting setting,
        final PlatformType platform) {
        if (platform == ANDROID) {
            final var options = getAndroidRecordOptions (setting);
            screen.startRecordingScreen (options);
        } else {
            final var options = getIOSRecordOptions (setting);
            screen.startRecordingScreen (options);
        }
    }

    private String stopRecording (final CanRecordScreen screen, final PlatformType platform) {
        final String content;
        if (platform == ANDROID) {
            final var options = AndroidStopScreenRecordingOptions.stopScreenRecordingOptions ();
            content = screen.stopRecordingScreen (options);
        } else {
            final var options = IOSStopScreenRecordingOptions.stopScreenRecordingOptions ();
            content = screen.stopRecordingScreen (options);
        }
        return content;
    }
}
