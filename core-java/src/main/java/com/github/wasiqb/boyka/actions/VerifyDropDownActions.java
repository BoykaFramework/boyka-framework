/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.DropDownActions.selectedItem;
import static com.github.wasiqb.boyka.actions.DropDownActions.selectedItems;
import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.builders.Locator;
import com.google.common.truth.IterableSubject;
import com.google.common.truth.StringSubject;

/**
 * Verify dropdown actions.
 *
 * @author Wasiq Bhamla
 * @since 30-Jul-2022
 */
public final class VerifyDropDownActions {
    /**
     * Verify selected item.
     *
     * @param locator {@link Locator} of dropdown
     *
     * @return {@link StringSubject} of selected item
     */
    public static StringSubject verifySelectedItem (final Locator locator) {
        return assertThat (selectedItem (locator));
    }

    /**
     * Verify selected items.
     *
     * @param locator {@link Locator} of dropdown
     *
     * @return {@link IterableSubject} of selected items
     */
    public static IterableSubject verifySelectedItems (final Locator locator) {
        return assertThat (selectedItems (locator));
    }

    private VerifyDropDownActions () {
        // Utility class.
    }
}
