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

package com.github.wasiqb.boyka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Swipe direction
 *
 * @author Wasiq Bhamla
 * @since 26-Oct-2022
 */
@Getter
@AllArgsConstructor
public enum SwipeDirection {
    /**
     * Down direction: Finger moving from bottom to top
     */
    DOWN (0, 1),
    /**
     * Left direction: Finger moving from left to right
     */
    LEFT (-1, 0),
    /**
     * Right direction: Finger moving from right to left
     */
    RIGHT (1, 0),
    /**
     * Up direction: Finger moving from top to bottom
     */
    UP (0, -1);

    private final int x;
    private final int y;
}
