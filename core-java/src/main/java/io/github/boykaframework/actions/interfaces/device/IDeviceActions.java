/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

/**
 * Handle all device specific actions.
 *
 * @author Wasiq Bhamla
 * @since 31-May-2023
 */
public interface IDeviceActions {
    /**
     * Hides the keyboard if visible.
     */
    void hideKeyboard ();

    /**
     * Gets the keyboard state whether it is visible or not.
     *
     * @return true, if visible.
     */
    boolean isKeyboardVisible ();

    /**
     * Starts video recording.
     */
    void startRecording ();

    /**
     * Stops video recording.
     */
    void stopRecording ();
}
