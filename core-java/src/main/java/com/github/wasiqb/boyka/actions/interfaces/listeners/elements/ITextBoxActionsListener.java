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
 * Handle all text box action events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface ITextBoxActionsListener extends BoykaListener {
    /**
     * Handle enter text method.
     *
     * @param locator Locator of the element.
     * @param text Text to enter into text box
     */
    default void onEnterText (final Locator locator, final String text) {
        // not implemented.
    }
}
