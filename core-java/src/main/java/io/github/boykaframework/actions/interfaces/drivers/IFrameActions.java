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

package io.github.boykaframework.actions.interfaces.drivers;

/**
 * All frames related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface IFrameActions {
    /**
     * Switch to an iFrame.
     *
     * @param frameNameOrId Name / ID of the Iframe.
     */
    void switchTo (final String frameNameOrId);

    /**
     * Switch to an iFrame.
     *
     * @param index Index of the frame.
     */
    void switchTo (int index);

    /**
     * Switch to Parent Frame.
     */
    void switchToParent ();
}
