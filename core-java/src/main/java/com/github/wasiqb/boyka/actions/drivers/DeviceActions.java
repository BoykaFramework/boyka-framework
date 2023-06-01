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

package com.github.wasiqb.boyka.actions.drivers;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static com.github.wasiqb.boyka.enums.ListenerType.DEVICE_ACTION;
import static com.github.wasiqb.boyka.enums.Message.NO_KEYBOARD_ERROR;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IDeviceActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IDeviceActionsListener;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.HidesKeyboard;
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

    private DeviceActions () {
        this.listener = getSession ().getListener (DEVICE_ACTION);
    }

    @Override
    public void hideKeyboard () {
        LOGGER.info ("Hiding the visible keyboard...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onHideKeyboard);
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        if (isKeyboardVisible ()) {
            performDriverAction (HidesKeyboard::hideKeyboard);
        }
    }

    @Override
    public boolean isKeyboardVisible () {
        LOGGER.info ("Checking if keyboard is visible...");
        ofNullable (this.listener).ifPresent (IDeviceActionsListener::onIsKeyboardVisible);
        final var platform = getSession ().getPlatformType ();
        if (platform == WEB) {
            throwError (NO_KEYBOARD_ERROR);
        }
        return getDriverAttribute (HasOnScreenKeyboard::isKeyboardShown, false);
    }
}
