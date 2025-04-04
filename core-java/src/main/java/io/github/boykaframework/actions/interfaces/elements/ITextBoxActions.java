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

import com.google.common.truth.StringSubject;

/**
 * All text box related actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public interface ITextBoxActions extends IClickableActions {
    /**
     * Enter text in text field.
     *
     * @param text text to enter
     */
    void enterText (final String text);

    /**
     * Focus the element.
     */
    void focus ();

    /**
     * Gets the input value from the textbox.
     *
     * @return Input value
     */
    String inputValue ();

    /**
     * Verifies the value entered in textbox.
     *
     * @return String subject
     */
    StringSubject verifyInputValue ();
}
