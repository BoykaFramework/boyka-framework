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

/**
 * Listener to handle Alert actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IAlertActionsListener extends BoykaListener {
    /**
     * Handle accept alert.
     */
    default void onAccept () {
        // not implemented.
    }

    /**
     * Handle Accept method for prompt alert.
     *
     * @param text Text to be entered in the Alert prompt
     */
    default void onAccept (final String text) {
        // not implemented.
    }

    /**
     * Handle dismiss alert.
     */
    default void onDismiss () {
        // not implemented.
    }

    /**
     * Handle verify accept alert method with prompt text.
     *
     * @param text Text to be entered in the Alert prompt
     */
    default void onVerifyAccept (final String text) {
        // not implemented.
    }

    /**
     * Handle verify accept alert method.
     */
    default void onVerifyAccept () {
        // not implemented.
    }

    /**
     * Handle dismiss alert method.
     */
    default void onVerifyDismiss () {
        // not implemented.
    }
}
