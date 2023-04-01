/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.actions.elements;

import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.actions.elements.ElementFinder.find;
import static com.github.wasiqb.boyka.enums.Message.ELEMENT_CANNOT_BE_NULL;
import static com.github.wasiqb.boyka.enums.Message.INVALID_SWIPE_DISTANCE;
import static com.github.wasiqb.boyka.enums.SwipeDirection.DOWN;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.requireNonNull;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.time.Duration;
import java.util.function.BiFunction;

import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.enums.SwipeDirection;
import com.github.wasiqb.boyka.enums.WaitStrategy;
import lombok.Builder;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/**
 * Class handling all the finger gestures for Mobile.
 *
 * @author Wasiq Bhamla
 * @since 26-Oct-2022
 */
@Builder (builderMethodName = "init")
final class FingerGestureBuilder {
    private static final Dimension SCREEN_SIZE = onWindow ().viewportSize ();

    @Builder.Default
    private       SwipeDirection      direction = DOWN;
    private       Locator             element;
    @Builder.Default
    private       int                 index     = 0;
    @Builder.Default
    private       String              name      = "Finger";
    private final Duration            stepWait  = ofMillis (600);
    private       Locator             targetElement;
    private final PointerInput.Origin viewport  = viewport ();

    Sequence swipe () {
        return composeSequence ((finger, steps) -> {
            final var start = getSwipeStartPosition ();
            final var end = getSwipeEndPosition ();
            steps.addAction (finger.createPointerMove (ZERO, this.viewport, start.getX (), start.getY ()));
            steps.addAction (finger.createPointerDown (LEFT.asArg ()));
            steps.addAction (finger.createPointerMove (this.stepWait, this.viewport, end.getX (), end.getY ()));
            steps.addAction (finger.createPointerUp (LEFT.asArg ()));
            return steps;
        });
    }

    Sequence tapOn () {
        return composeSequence ((finger, steps) -> {
            final var start = getElementCenter (this.element);
            steps.addAction (finger.createPointerMove (this.stepWait, this.viewport, start.getX (), start.getY ()));
            steps.addAction (finger.createPointerDown (LEFT.asArg ()));
            steps.addAction (finger.createPointerUp (LEFT.asArg ()));
            return steps;
        });
    }

    private Sequence composeSequence (final BiFunction<PointerInput, Sequence, Sequence> steps) {
        final var finger = new PointerInput (TOUCH, this.name);
        final var sequence = new Sequence (finger, this.index);
        return steps.apply (finger, sequence);
    }

    private Point getElementCenter (final Locator element) {
        final var webElement = find (requireNonNull (element, ELEMENT_CANNOT_BE_NULL), WaitStrategy.CLICKABLE);
        final var location = webElement.getLocation ();
        final var size = webElement.getSize ();
        final var x = location.getX () + (size.getWidth () / 2);
        final var y = location.getY () + (size.getHeight () / 2);
        return new Point (x, y);
    }

    private Point getScreenCenter () {
        final var x = SCREEN_SIZE.getWidth () / 2;
        final var y = SCREEN_SIZE.getHeight () / 2;
        return new Point (x, y);
    }

    private Point getSwipeEndPosition () {
        if (this.targetElement != null) {
            return getElementCenter (this.targetElement);
        }
        final var distance = getSession ().getMobileSetting ()
            .getDevice ()
            .getSwipe ()
            .getDistance ();
        if (distance <= 0 || distance >= 100) {
            throwError (INVALID_SWIPE_DISTANCE);
        }
        var location = new Point (0, 0);
        if (this.element != null) {
            final var webElement = find (this.element, WaitStrategy.CLICKABLE);
            location = webElement.getLocation ();
        }
        final var start = getSwipeStartPosition ();
        final var x = start.getX () + location.getX () + ((start.getX () * this.direction.getX () * distance) / 100);
        final var y = start.getY () + location.getY () + ((start.getY () * this.direction.getY () * distance) / 100);

        return new Point (x, y);
    }

    private Point getSwipeStartPosition () {
        if (this.element != null) {
            return getElementCenter (this.element);
        }
        return getScreenCenter ();
    }
}
