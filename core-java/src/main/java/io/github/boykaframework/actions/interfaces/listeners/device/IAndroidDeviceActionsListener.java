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

package io.github.boykaframework.actions.interfaces.listeners.device;

import java.io.File;

import io.appium.java_client.android.nativekey.AndroidKey;

/**
 * Handles all Android device specific events.
 *
 * @author Wasiq Bhamla
 * @since 03-Jun-2023
 */
public interface IAndroidDeviceActionsListener extends IDeviceActionsListener {
    /**
     * Handles get clipboard text method.
     */
    default void onGetClipboardText () {
        // not implemented.
    }

    /**
     * Handles open notification method.
     */
    default void onOpenNotification () {
        // not implemented.
    }

    /**
     * Handle key press event.
     *
     * @param key Android key
     */
    default void onPressKey (final AndroidKey key) {
        // not implemented.
    }

    /**
     * Handles pull file method.
     *
     * @param targetPath Remote device file path.
     */
    default void onPullFile (final String targetPath) {
        // not implemented.
    }

    /**
     * Handles put file method.
     *
     * @param sourceFile Source file.
     * @param destinationPath Device target path.
     */
    default void onPutFile (final File sourceFile, final String destinationPath) {
        // not implemented.
    }

    /**
     * Handles set clipboard text method.
     *
     * @param text Text to set in clipboard.
     */
    default void onSetClipboardText (final String text) {
        // not implemented.
    }

    /**
     * Handles verify clipboard text method.
     */
    default void onVerifyClipboardText () {
        // not implemented.
    }
}
