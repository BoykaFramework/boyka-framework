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

import java.util.List;

import io.github.boykaframework.actions.elements.MouseAction;

/**
 * All Clickable actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public interface IClickableActions extends IFingersActions {
    /**
     * Click on element
     */
    void click ();

    /**
     * LongPress on element
     */
    void clickAndHold ();

    /**
     * DoubleClick on element
     */
    void doubleClick ();

    /**
     * Draw shapes based on the list of points.
     *
     * @param actions Mouse actions for composing the draw action.
     */
    void draw (List<MouseAction> actions);

    /**
     * Hover on element
     */
    void hover ();

    /**
     * Presses the back button on Mouse.
     */
    void pressBackButton ();

    /**
     * Presses the forward button on Mouse.
     */
    void pressForwardButton ();

    /**
     * RightClick on element
     */
    void rightClick ();

    /**
     * Scroll to element.
     */
    void scrollToElement ();

    /**
     * Submit the element.
     */
    void submit ();
}
