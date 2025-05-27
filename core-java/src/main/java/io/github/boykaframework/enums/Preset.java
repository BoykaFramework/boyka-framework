/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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
 * Gets MacOS Video recording preset
 *
 * @author Wasiq Bhamla
 * @since 24-May-2025
 */
@Getter
@AllArgsConstructor
public enum Preset {
    /**
     * Fast
     */
    FAST ("fast"),
    /**
     * Faster
     */
    FASTER ("faster"),
    /**
     * Medium
     */
    MEDIUM ("medium"),
    /**
     * Slow
     */
    SLOW ("slow"),
    /**
     * Slower
     */
    SLOWER ("slower"),
    /**
     * Superfast
     */
    SUPER_FAST ("superfast"),
    /**
     * Ultrafast
     */
    ULTRA_FAST ("ultrafast"),
    /**
     * Very fast
     */
    VERY_FAST ("veryfast"),
    /**
     * Very slow
     */
    VERY_SLOW ("veryslow");

    private final String name;
}
