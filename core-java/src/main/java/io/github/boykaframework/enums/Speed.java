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

package io.github.boykaframework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * UI execution speed.
 *
 * @author Wasiq Bhamla
 * @since 27-Mar-2024
 */
@AllArgsConstructor
@Getter
public enum Speed {
    /**
     * Fast speed with no delay.
     */
    FAST (0),
    /**
     * Normal means, with delay of 200ms between each action.
     */
    NORMAL (200),
    /**
     * Slow means, with delay of 500ms between each action.
     */
    SLOW (500);

    private final int delay;
}
