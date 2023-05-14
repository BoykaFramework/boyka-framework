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

package com.github.wasiqb.boyka.actions.interfaces.listeners.drivers;

import com.github.wasiqb.boyka.actions.interfaces.listeners.BoykaListener;
import org.openqa.selenium.WindowType;

public interface IWindowActionsListener extends BoykaListener {
    default void onClose () {
        // not implemented.
    }

    default void onCurrentHandle () {
        // not implemented.
    }

    default void onFullScreen () {
        // not implemented.
    }

    default void onGetTitle () {
        // not implemented.
    }

    default void onHandles () {
        // not implemented.
    }

    default void onMaximize () {
        // not implemented.
    }

    default void onMinimize () {
        // not implemented.
    }

    default void onSwitchTo (final String nameOfHandle) {
        // not implemented.
    }

    default void onSwitchToDefault () {
        // not implemented.
    }

    default void onSwitchToNew (final WindowType windowType) {
        // not implemented.
    }

    default void onTakeScreenshot (final String fileName) {
        // not implemented.
    }

    default void onVerifyTitle () {
        // not implemented.
    }

    default void onViewportSize () {
        // not implemented.
    }
}
