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
 * Handle all element actions events.
 *
 * @author Wasiq Bhamla
 * @since 09-Apr-2023
 */
public interface IElementActionsListener extends BoykaListener {
    /**
     * Handle clear method.
     *
     * @param locator Locator of the element.
     */
    default void onClear (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle get attribute method.
     *
     * @param locator Locator of the element.
     * @param attribute Attribute name
     */
    default void onGetAttribute (final Locator locator, final String attribute) {
        // not implemented.
    }

    /**
     * Handles get element method.
     *
     * @param locator locator of the element.
     */
    default void onGetElement (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles get location method
     *
     * @param locator locator of the element.
     */
    default void onGetLocation (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles getProperty method.
     *
     * @param property property name
     */
    default void onGetProperty (final String property) {
        // not implemented.
    }

    /**
     * Handles get size method.
     *
     * @param locator locator of the element.
     */
    default void onGetSize (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle get style method.
     *
     * @param locator Locator of the element.
     * @param styleName Style name
     */
    default void onGetStyle (final Locator locator, final String styleName) {
        // not implemented.
    }

    /**
     * Handle get text method.
     *
     * @param locator Locator of the element.
     */
    default void onGetText (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is displayed method.
     *
     * @param locator Locator of the element.
     */
    default void onIsDisplayed (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is enabled method.
     *
     * @param locator Locator of the element.
     */
    default void onIsEnabled (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle is selected method.
     *
     * @param locator Locator of the element.
     */
    default void onIsSelected (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles itemList method.
     */
    default void onItemList () {
        // not implemented.
    }

    /**
     * Handle scroll into view method.
     *
     * @param locator Locator of the element.
     */
    default void onScrollIntoView (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify attribute method.
     *
     * @param locator Locator of the element.
     * @param attribute attribute name
     */
    default void onVerifyAttribute (final Locator locator, final String attribute) {
        // not implemented.
    }

    /**
     * Handle verify is displayed method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsDisplayed (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify is enabled method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsEnabled (final Locator locator) {
        // not implemented.
    }

    /**
     * Handle verify is selected method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyIsSelected (final Locator locator) {
        // not implemented.
    }

    /**
     * Handles verifyItems method.
     */
    default void onVerifyItems () {
        // not implemented.
    }

    /**
     * Handles verifyProperty value.
     *
     * @param property Property name
     */
    default void onVerifyProperty (final String property) {
        // not implemented.
    }

    /**
     * Handle verify style method.
     *
     * @param locator Locator of the element.
     * @param styleName Style name
     */
    default void onVerifyStyle (final Locator locator, final String styleName) {
        // not implemented.
    }

    /**
     * Handle verify text method.
     *
     * @param locator Locator of the element.
     */
    default void onVerifyText (final Locator locator) {
        // not implemented.
    }
}
