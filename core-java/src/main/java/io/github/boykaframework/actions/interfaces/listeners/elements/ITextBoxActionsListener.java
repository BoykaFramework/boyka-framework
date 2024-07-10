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

    /**
     * Handles input element focus method.
     *
     * @param locator Locator of the element.
     */
    default void onFocus (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles the method `inputValue`
     *
     * @param locator Locator of the element.
     */
    default void onInputValue (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles the method `verifyInputValue`
     *
     * @param locator Locator of the element.
     */
    default void onVerifyInputValue (final Locator locator) {
        // not implemented.
    }
}
