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

package io.github.boykaframework.actions.interfaces.listeners.api;

import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;
import io.github.boykaframework.builders.ApiResponse;

/**
 * API Actions listener.
 *
 * @author Wasiq Bhamla
 * @since 06-Apr-2023
 */
public interface IApiActionsListener extends BoykaListener {
    /**
     * Handle API `execute` method.
     *
     * @param response {@link ApiResponse} instance
     */
    default void onExecute (final ApiResponse response) {
        // not implemented
    }
}
