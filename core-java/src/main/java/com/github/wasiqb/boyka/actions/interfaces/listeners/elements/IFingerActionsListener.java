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

package com.github.wasiqb.boyka.actions.interfaces.listeners.elements;

import com.github.wasiqb.boyka.actions.interfaces.listeners.BoykaListener;
import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.enums.SwipeDirection;

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
     * @param destination target element to drop to
     */
    default void onDragTo (final Locator destination) {
        // not implemented.
    }

    /**
     * Handle swipe method.
     *
     * @param direction Swipe direction
     */
    default void onSwipe (final SwipeDirection direction) {
        // not implemented.
    }

    /**
     * Handle swipe till method.
     *
     * @param direction swipe direction
     */
    default void onSwipeTill (final SwipeDirection direction) {
        // not implemented.
    }

    /**
     * Handle tap method.
     */
    default void onTap () {
        // not implemented.
    }
}
