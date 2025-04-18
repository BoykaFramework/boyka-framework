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

package io.github.boykaframework.actions.interfaces.device;

import java.io.File;

import com.google.common.truth.StringSubject;
import io.appium.java_client.android.nativekey.AndroidKey;

/**
 * Handles all Android device specific actions.
 *
 * @author Wasiq Bhamla
 * @since 03-Jun-2023
 */
public interface IAndroidDeviceActions extends IDeviceActions {
    /**
     * Gets the current clipboard text.
     *
     * @return Text in the clipboard.
     */
    String getClipboardText ();

    /**
     * Open notification panel.
     */
    void openNotification ();

    /**
     * Press Android key on the device.
     *
     * @param key Android key
     */
    void pressKey (AndroidKey key);

    /**
     * Gets the file from the device.
     *
     * @param targetPath File path on the remote device.
     *
     * @return Base 64 encoded file content.
     */
    byte[] pullFile (String targetPath);

    /**
     * Puts file from machine to device.
     *
     * @param sourceFile File to put on device.
     * @param destinationPath Remote device path where file should be put.
     *
     * @return true, if file successfully put on the device, else false.
     */
    boolean putFile (File sourceFile, String destinationPath);

    /**
     * Sets the clipboard text on the device
     *
     * @param text Text to set in the clipboard.
     */
    void setClipboardText (String text);

    /**
     * Verifies clipboard text on the device.
     *
     * @return String subject.
     */
    StringSubject verifyClipboardText ();
}
