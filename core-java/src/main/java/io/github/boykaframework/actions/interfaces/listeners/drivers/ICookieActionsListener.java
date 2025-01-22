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

package io.github.boykaframework.actions.interfaces.listeners.drivers;

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;

/**
 * Handle all cookies action events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface ICookieActionsListener extends BoykaListener {
    /**
     * Handle cookie method.
     *
     * @param name Cookie name
     */
    default void onCookie (final String name) {
        // not implemented.
    }

    /**
     * Handle cookies method.
     */
    default void onCookies () {
        // not implemented.
    }

    /**
     * Handle delete cookie method.
     *
     * @param name Cookie name
     */
    default void onDelete (final String name) {
        // not implemented.
    }

    /**
     * Handle delete all method.
     */
    default void onDeleteAll () {
        // not implemented.
    }
}
