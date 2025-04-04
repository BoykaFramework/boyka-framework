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
 * Handle navigate actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface INavigateActionsListener extends BoykaListener {
    /**
     * Handle back method.
     */
    default void onBack () {
        // not implemented.
    }

    /**
     * Handle forward method.
     */
    default void onForward () {
        // not implemented.
    }

    /**
     * Handle get url method.
     */
    default void onGetUrl () {
        // not implemented.
    }

    /**
     * Handle refresh method.
     */
    default void onRefresh () {
        // not implemented.
    }

    /**
     * handle `to` method.
     *
     * @param url URL to navigate to
     */
    default void onTo (final String url) {
        // not implemented.
    }

    /**
     * Handle event of `toBaseUrl` method.
     */
    default void onToBaseUrl () {
        // not implemented.
    }

    /**
     * Handle verify URL method.
     */
    default void onVerifyUrl () {
        // not implemented.
    }
}
