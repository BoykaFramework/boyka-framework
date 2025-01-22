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

package io.github.boykaframework.actions.interfaces.listeners.data;

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;

/**
 * Test Data action listener.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
public interface ITestDataActionsListener extends BoykaListener {
    /**
     * Handle `get` method from TestDataAction
     *
     * @param dataClass Test data class
     * @param <T> Type of test data class
     */
    default <T> void onGet (final Class<T> dataClass) {
        // not implemented
    }

    /**
     * Handle row method from TestDataAction
     *
     * @param index Row index
     */
    default void onRow (final int index) {
        // not implemented
    }

    /**
     * Handle row method from TestDataAction
     */
    default void onRows () {
        // not implemented
    }
}
