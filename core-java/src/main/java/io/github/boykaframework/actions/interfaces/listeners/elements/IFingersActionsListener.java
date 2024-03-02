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

package io.github.boykaframework.actions.interfaces.listeners.elements;

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;
import io.github.boykaframework.builders.Locator;

/**
 * Handles all multi-fingers actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IFingersActionsListener extends BoykaListener {
    /**
     * Handle zoom in method.
     *
     * @param locator Locator of the element.
     */
    default void onZoomIn (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle zoom out method.
     *
     * @param locator Locator of the element.
     */
    default void onZoomOut (final Locator locator) {
        // not implemented.
    }
}
