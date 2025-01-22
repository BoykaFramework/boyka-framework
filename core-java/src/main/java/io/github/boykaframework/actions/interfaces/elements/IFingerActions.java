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

package io.github.boykaframework.actions.interfaces.elements;

import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.SwipeDirection;

/**
 * All single finger actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public interface IFingerActions extends IElementActions {
    /**
     * Double tap on an element.
     */
    void doubleTap ();

    /**
     * Drag one element to another element.
     *
     * @param destination Target element
     */
    void dragTo (final Locator destination);

    /**
     * Long press on element for a particular duration.
     *
     * @param millis Duration of long press.
     */
    void longPress (long millis);

    /**
     * Long press on an element for 500 ms.
     */
    void longPress ();

    /**
     * Swipe on the Mobile screen starting from center of the screen to the direction mentioned
     *
     * @param direction Direction to swipe in.
     */
    void swipe (SwipeDirection direction);

    /**
     * Swipe until element is displayed in the mentioned direction.
     *
     * @param direction Direction to swipe in
     */
    void swipeTill (SwipeDirection direction);

    /**
     * Taps on an element.
     */
    void tap ();
}
