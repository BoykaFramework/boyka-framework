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
 * Handle all the dropdown actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IDropDownActionsListener extends BoykaListener {
    /**
     * Handle deselect all items method.
     *
     * @param locator Locator of the element.
     */
    default void onDeselectAll (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle deselect by index method.
     *
     * @param locator Locator of the element.
     * @param index Item index
     */
    default void onDeselectByIndex (final Locator locator, final int index) {
        // not implemented.
    }

    /**
     * Handle deselect by text method.
     *
     * @param locator Locator of the element.
     * @param text Item text
     */
    default void onDeselectByText (final Locator locator, final String text) {
        // not implemented.
    }

    /**
     * Handle deselect by value method.
     *
     * @param locator Locator of the element.
     * @param value Item value
     */
    default void onDeselectByValue (final Locator locator, final String value) {
        // not implemented.
    }

    /**
     * Handle select by index method.
     *
     * @param locator Locator of the element.
     * @param index Item index
     */
    default void onSelectByIndex (final Locator locator, final int index) {
        // not implemented.
    }

    /**
     * Handle select by text method.
     *
     * @param locator Locator of the element.
     * @param text Item text
     */
    default void onSelectByText (final Locator locator, final String text) {
        // not implemented.
    }

    /**
     * Handle select by value method.
     *
     * @param locator Locator of the element.
     * @param value Item value
     */
    default void onSelectByValue (final Locator locator, final String value) {
        // not implemented.
    }

    /**
     * Handle selected item method.
     *
     * @param locator Locator of the element.
     */
    default void onSelectedItem (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle selected items method.
     *
     * @param locator Locator of the element.
     */
    default void onSelectedItems (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify selected item.
     *
     * @param locator Locator of the element.
     */
    default void onVerifySelectedItem (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify selected items.
     *
     * @param locator Locator of the element.
     */
    default void onVerifySelectedItems (final Locator locator) {
        // not implemented.
    }
}
