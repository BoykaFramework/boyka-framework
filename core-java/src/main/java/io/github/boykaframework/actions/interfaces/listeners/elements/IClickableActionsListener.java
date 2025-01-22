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

package io.github.boykaframework.actions.interfaces.listeners.elements;

import java.util.List;

import io.github.boykaframework.actions.elements.MouseAction;
import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;
import io.github.boykaframework.builders.Locator;

/**
 * Handle all clickable actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IClickableActionsListener extends BoykaListener {
    /**
     * Handle click method
     *
     * @param locator Locator of the element.
     */
    default void onClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle click and hold method.
     *
     * @param locator Locator of the element.
     */
    default void onClickAndHold (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle double click method.
     *
     * @param locator Locator of the element.
     */
    default void onDoubleClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle drag to the target element.
     *
     * @param source source element.
     * @param destination target element
     */
    default void onDragTo (final Locator source, final Locator destination) {
        // not implemented.
    }

    /**
     * Handles draw method.
     *
     * @param actions Mouse actions to perform for drawing.
     */
    default void onDraw (final List<MouseAction> actions) {
        // not implemented.
    }

    /**
     * Handle hover method.
     *
     * @param locator Locator of the element.
     */
    default void onHover (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles the pressBackButton method.
     */
    default void onPressBackButton () {
        // not implemented.
    }

    /**
     * Handles the pressForwardButton method.
     */
    default void onPressForwardButton () {
        // not implemented.
    }

    /**
     * Handle right click method.
     *
     * @param locator Locator of the element.
     */
    default void onRightClick (final Locator locator) {
        // not implemented.
    }

    /**
     * Scroll to the element.
     *
     * @param locator locator of the element.
     */
    default void onScrollToElement (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle submit method.
     *
     * @param locator Locator of the element.
     */
    default void onSubmit (final Locator locator) {
        // not implemented.
    }
}
