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

package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.enums.SwipeDirection.DOWN;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.util.List;
import java.util.function.BiFunction;

import com.github.wasiqb.boyka.enums.SwipeDirection;
import lombok.Builder;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/**
 * Class handling all the finger gestures for Mobile.
 *
 * @author Wasiq Bhamla
 * @since 26-Oct-2022
 */
@Builder (builderMethodName = "composeGesture")
public final class FingerGestureUtils {
    @Builder.Default
    private SwipeDirection direction = DOWN;
    @Builder.Default
    private int            distance  = 20;
    private WebElement     element;
    @Builder.Default
    private int            index     = 0;
    @Builder.Default
    private String         name      = "Finger";

    /**
     * Tap on element
     *
     * @return Sequence of tap action
     */
    public List<Sequence> tapOn () {
        return composeSequence ((finger, steps) -> {
            final var start = getElementCenter ();
            steps.addAction (finger.createPointerMove (ofMillis (100), viewport (), start.getX (), start.getY ()));
            steps.addAction (finger.createPointerDown (LEFT.asArg ()));
            steps.addAction (finger.createPointerUp (LEFT.asArg ()));
            return singletonList (steps);
        });
    }

    private List<Sequence> composeSequence (final BiFunction<PointerInput, Sequence, List<Sequence>> steps) {
        final var finger = new PointerInput (TOUCH, this.name);
        final var sequence = new Sequence (finger, this.index);
        return steps.apply (finger, sequence);
    }

    private Point getElementCenter () {
        final var location = this.element.getLocation ();
        final var size = this.element.getSize ();
        final var x = location.getX () + (size.getWidth () / 2);
        final var y = location.getY () + (size.getHeight () / 2);
        return new Point (x, y);
    }
}
