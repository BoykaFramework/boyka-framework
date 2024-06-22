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

package io.github.boykaframework.actions.interfaces.listeners.drivers;

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;
import org.openqa.selenium.WindowType;

/**
 * Handle windows action events.
 *
 * @author Wasiq Bhamla
 * @since 29-Apr-2023
 */
public interface IWindowActionsListener extends BoykaListener {
    /**
     * Handle close method.
     */
    default void onClose () {
        // not implemented.
    }

    /**
     * Handle get current handle method.
     */
    default void onCurrentHandle () {
        // not implemented.
    }

    /**
     * Handles fullscreen method.
     */
    default void onFullScreen () {
        // not implemented.
    }

    /**
     * Handles get title method.
     */
    default void onGetTitle () {
        // not implemented.
    }

    /**
     * Handles get window handles method.
     */
    default void onHandles () {
        // not implemented.
    }

    /**
     * Handles maximize method.
     */
    default void onMaximize () {
        // not implemented.
    }

    /**
     * Handles minimize method.
     */
    default void onMinimize () {
        // not implemented.
    }

    /**
     * Handles switch to window method.
     *
     * @param nameOfHandle Name / Window handle
     */
    default void onSwitchTo (final String nameOfHandle) {
        // not implemented.
    }

    /**
     * Handle switch to default window method.
     */
    default void onSwitchToDefault () {
        // not implemented.
    }

    /**
     * Handles switch to window method.
     *
     * @param windowType Type of Window
     */
    default void onSwitchToNew (final WindowType windowType) {
        // not implemented.
    }

    /**
     * Handles take screenshot method.
     *
     * @param fileName Screenshot file name
     */
    default void onTakeScreenshot (final String fileName) {
        // not implemented.
    }

    /**
     * Handles verify title method.
     */
    default void onVerifyTitle () {
        // not implemented.
    }

    /**
     * Handles viewport size method.
     */
    default void onViewportSize () {
        // not implemented.
    }
}
