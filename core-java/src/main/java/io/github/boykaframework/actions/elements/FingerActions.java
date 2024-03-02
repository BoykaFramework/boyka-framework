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

package io.github.boykaframework.actions.elements;

import static java.time.Duration.ZERO;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.github.boykaframework.actions.CommonActions;
import io.github.boykaframework.actions.interfaces.elements.IFingerActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IFingerActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.ListenerType;
import io.github.boykaframework.enums.Message;
import io.github.boykaframework.enums.SwipeDirection;
import io.github.boykaframework.manager.ParallelSession;
import io.github.boykaframework.utils.ErrorHandler;
import org.apache.logging.log4j.Logger;

/**
 * Handles all single finger related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public class FingerActions extends ElementActions implements IFingerActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Perform single finger actions on element
     *
     * @param locator Locator of the element
     *
     * @return Finger actions instance
     */
    public static IFingerActions withFinger (final Locator locator) {
        return new FingerActions (locator);
    }

    /**
     * Perform single finger actions on element
     *
     * @return Finger actions instance
     */
    public static IFingerActions withFinger () {
        return new FingerActions ();
    }

    private final IFingerActionsListener listener;

    FingerActions () {
        this (null);
    }

    FingerActions (final Locator locator) {
        super (locator);
        this.listener = ParallelSession.getSession ()
            .getListener (ListenerType.FINGER_ACTION);
    }

    @Override
    public void dragTo (final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Dragging [{}] to [{}] on Mobile devices.", this.locator.getName (), destination.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDragTo (this.locator, destination));
        final var dragSequence = CommonActions.getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .sourceElement (this.locator)
            .pause (ofMillis (600))
            .build ()
            .dragTo (destination), null);
        CommonActions.performMobileGestures (singletonList (dragSequence));
        LOGGER.traceExit ();
    }

    @Override
    public void swipe (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping {} on Mobile devices.", direction);
        ofNullable (this.listener).ifPresent (l -> l.onSwipe (this.locator, direction));
        final var swipeUpSequence = CommonActions.getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (direction)
            .sourceElement (this.locator)
            .build ()
            .swipe (), null);
        CommonActions.performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }

    @Override
    public void swipeTill (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping till the element in the [{}] direction...", direction);
        ofNullable (this.listener).ifPresent (l -> l.onSwipeTill (this.locator, direction));
        final var maxSwipe = this.swipeSetting.getMaxSwipeUntilFound ();
        var swipeCounts = 0;
        final var element = onElement (this.locator);
        final var finger = withFinger ();
        while (!element.isDisplayed () && swipeCounts++ < maxSwipe) {
            finger.swipe (direction);
        }
        if (!element.isDisplayed ()) {
            ErrorHandler.throwError (Message.ELEMENT_NOT_FOUND, this.locator.getName (), ParallelSession.getSession ()
                .getPlatformType ());
        }
        LOGGER.traceExit ();
    }

    @Override
    public void tap () {
        LOGGER.traceEntry ();
        LOGGER.info ("Tapping on the element...");
        ofNullable (this.listener).ifPresent (l -> l.onTap (this.locator));
        final var sequences = CommonActions.getElementAttribute (element -> FingerGestureBuilder.init ()
            .sourceElement (this.locator)
            .pause (ZERO)
            .build ()
            .tapOn (), this.locator, null);
        CommonActions.performMobileGestures (singletonList (sequences));
        LOGGER.traceExit ();
    }
}
