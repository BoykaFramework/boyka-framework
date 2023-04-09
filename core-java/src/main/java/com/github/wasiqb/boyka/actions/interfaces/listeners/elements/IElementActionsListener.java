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

/**
 * Handle all element actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IElementActionsListener extends BoykaListener {
    /**
     * Handle clear method.
     */
    default void onClear () {
        // not implemented.
    }

    /**
     * Handle get attribute method.
     *
     * @param attribute Attribute name
     */
    default void onGetAttribute (final String attribute) {
        // not implemented.
    }

    /**
     * Handle get style method.
     *
     * @param styleName Style name
     */
    default void onGetStyle (final String styleName) {
        // not implemented.
    }

    /**
     * Handle get text method.
     */
    default void onGetText () {
        // not implemented.
    }

    /**
     * Handle is displayed method.
     */
    default void onIsDisplayed () {
        // not implemented.
    }

    /**
     * Handle is enabled method.
     */
    default void onIsEnabled () {
        // not implemented.
    }

    /**
     * Handle is selected method.
     */
    default void onIsSelected () {
        // not implemented.
    }

    /**
     * Handle scroll into view method.
     */
    default void onScrollIntoView () {
        // not implemented.
    }

    /**
     * Handle verify attribute method.
     *
     * @param attribute attribute name
     */
    default void onVerifyAttribute (final String attribute) {
        // not implemented.
    }

    /**
     * Handle verify is displayed method.
     */
    default void onVerifyIsDisplayed () {
        // not implemented.
    }

    /**
     * Handle verify is enabled method.
     */
    default void onVerifyIsEnabled () {
        // not implemented.
    }

    /**
     * Handle verify is selected method.
     */
    default void onVerifyIsSelected () {
        // not implemented.
    }

    /**
     * Handle verify style method.
     *
     * @param styleName Style name
     */
    default void onVerifyStyle (final String styleName) {
        // not implemented.
    }

    /**
     * Handle verify text method.
     */
    default void onVerifyText () {
        // not implemented.
    }
}
