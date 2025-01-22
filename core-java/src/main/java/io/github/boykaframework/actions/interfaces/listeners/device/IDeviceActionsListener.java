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

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;

/**
 * Handles all the common device specific events.
 *
 * @author Wasiq Bhamla
 * @since 03-Jun-2023
 */
public interface IDeviceActionsListener extends BoykaListener {
    /**
     * Handle hide keyboard event.
     */
    default void onHideKeyboard () {
        // not implemented.
    }

    /**
     * Handle is keyboard visible event.
     */
    default void onIsKeyboardVisible () {
        // not implemented.
    }

    /**
     * Handle start recording event.
     */
    default void onStartRecording () {
        // not implemented.
    }

    /**
     * Handle stop recording event.
     */
    default void onStopRecording () {
        // not implemented.
    }
}
