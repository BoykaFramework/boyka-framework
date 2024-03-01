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
import io.github.boykaframework.enums.SwipeDirection;

/**
 * Handle all single finger actions event.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IFingerActionsListener extends BoykaListener {
    /**
     * Handle drag to method.
     *
     * @param source Source element to drag.
     * @param destination target element to drop to
     */
    default void onDragTo (final Locator source, final Locator destination) {
        // not implemented.
    }

    /**
     * Handle swipe method.
     *
     * @param locator Locator of the element.
     * @param direction Swipe direction
     */
    default void onSwipe (final Locator locator, final SwipeDirection direction) {
        // not implemented.
    }

    /**
     * Handle swipe till method.
     *
     * @param locator Locator of the element.
     * @param direction swipe direction
     */
    default void onSwipeTill (final Locator locator, final SwipeDirection direction) {
        // not implemented.
    }

    /**
     * Handle tap method.
     *
     * @param locator Locator of the element.
     */
    default void onTap (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle Tap and Hold.
     *
     * @param locator Locator of the element.
     */
    default void onTapAndHold (final Locator locator) {
        // not implemented.
    }
}
