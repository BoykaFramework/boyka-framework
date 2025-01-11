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

package io.github.boykaframework.actions.elements;

import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;

import java.time.Duration;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;

/**
 * Handles mouse action details.
 *
 * @author Wasiq Bhamla
 * @since 06-Jan-2024
 */
@Getter
@Builder (builderMethodName = "composeAction", buildMethodName = "compose")
public class MouseAction {
    /**
     * Different Mouse action types.
     */
    public enum ActionType {
        /**
         * Pointer move.
         */
        MOVE,
        /**
         * Pause for the specified duration.
         */
        PAUSE,
        /**
         * Pointer down.
         */
        PRESS,
        /**
         * Pointer up.
         */
        RELEASE
    }

    private ActionType               actionType;
    @Builder.Default
    private PointerInput.MouseButton button = LEFT;
    private Duration                 duration;
    private Point                    location;
}
