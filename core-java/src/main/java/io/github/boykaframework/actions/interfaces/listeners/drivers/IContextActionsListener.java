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

/**
 * Handle Context actions related events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IContextActionsListener extends BoykaListener {
    /**
     * Handle contexts method.
     */
    default void onContexts () {
        // not implemented.
    }

    /**
     * Handle current context method.
     */
    default void onCurrentContext () {
        // not implemented.
    }

    /**
     * Handle switch to native view method.
     */
    default void onSwitchToNative () {
        // not implemented.
    }

    /**
     * Handle switch to web view method.
     *
     * @param name Name of WebView
     */
    default void onSwitchToWebView (final String name) {
        // not implemented.
    }

    /**
     * Handle switch to web view method.
     */
    default void onSwitchToWebView () {
        // not implemented.
    }
}
