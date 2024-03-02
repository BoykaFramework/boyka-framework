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

package io.github.boykaframework.actions.interfaces.elements;

import java.util.List;

import com.google.common.truth.IterableSubject;
import com.google.common.truth.StringSubject;

/**
 * All drop down related actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public interface IDropDownActions extends IClickableActions {
    /**
     * Deselects all the selected values.
     */
    void deselectAll ();

    /**
     * Deselects the option from the dropdown based on its index.
     *
     * @param index index of the option to deselect
     */
    void deselectByIndex (final int index);

    /**
     * Deselects the option from the dropdown based on its visible text.
     *
     * @param text visible text of the option to deselect
     */
    void deselectByText (final String text);

    /**
     * Deselects the option from the dropdown based on its value.
     *
     * @param value value of the option to deselect
     */
    void deselectByValue (final String value);

    /**
     * Selects the value from dropdown based on index.
     *
     * @param index index to be selected
     */
    void selectByIndex (final int index);

    /**
     * Selects the value from dropdown based on visible text.
     *
     * @param text text to be selected
     */
    void selectByText (final String text);

    /**
     * Selects the value from dropdown based on value.
     *
     * @param value value to be selected
     */
    void selectByValue (final String value);

    /**
     * Returns the first selected value from dropdown.
     *
     * @return first selected value
     */
    String selectedItem ();

    /**
     * Gets all the selected values from the dropdown.
     *
     * @return list of selected values
     */
    List<String> selectedItems ();

    /**
     * Verify selected item.
     *
     * @return {@link StringSubject} of selected item
     */
    StringSubject verifySelectedItem ();

    /**
     * Verify selected items.
     *
     * @return {@link IterableSubject} of selected items
     */
    IterableSubject verifySelectedItems ();
}
