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

import static io.github.boykaframework.actions.CommonActions.getDriverAttribute;
import static io.github.boykaframework.actions.CommonActions.performDriverAction;
import static io.github.boykaframework.enums.ListenerType.DEVICE_ACTION;
import static io.github.boykaframework.enums.Message.NO_KEYBOARD_ERROR;
import static io.github.boykaframework.enums.PlatformType.MAC;
import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
import io.github.boykaframework.actions.interfaces.device.IDeviceActions;
import io.github.boykaframework.actions.interfaces.listeners.device.IDeviceActionsListener;
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
    private final VideoRecordAction      recordAction;

    /**
     * Initializes device actions.
     */
    protected DeviceActions () {
        this.listener = getSession ().getListener (DEVICE_ACTION);
        this.recordAction = getRecordAction ();
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
        LOGGER.info ("Starting video recording...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onStartRecording);
        this.recordAction.startRecording ();
    }

    @Override
    public void stopRecording () {
        LOGGER.info ("Stopping video recording...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onStopRecording);
        this.recordAction.stopRecording ();
    }

    private void checkKeyboardSupported () {
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB || platform == MAC) {
            throwError (NO_KEYBOARD_ERROR);
        }
    }

    private VideoRecordAction getRecordAction () {
        final var platform = getSession ().getPlatformType ();
        return switch (platform) {
            case ANDROID -> new AndroidRecordAction ();
            case IOS -> new IOSRecordAction ();
            case MAC -> new MacRecordAction ();
            default -> new WindowsRecordAction ();
        };
    }
}
