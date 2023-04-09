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

/**
 * Handle all clickable actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IClickableActionsListener extends BoykaListener {
    /**
     * Handle click method.
     */
    default void onClick () {
        // not implemented.
    }

    /**
     * Handle click and hold method.
     */
    default void onClickAndHold () {
        // not implemented.
    }

    /**
     * Handle double click method.
     */
    default void onDoubleClick () {
        // not implemented.
    }

    /**
     * Handle drag to the target element.
     *
     * @param destination target element
     */
    default void onDragTo (final Locator destination) {
        // not implemented.
    }

    /**
     * Handle hover method.
     */
    default void onHover () {
        // not implemented.
    }

    /**
     * Handle right click method.
     */
    default void onRightClick () {
        // not implemented.
    }

    /**
     * Handle submit method.
     */
    default void onSubmit () {
        // not implemented.
    }
}
