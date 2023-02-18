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

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.getElementAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performMobileGestures;
import static com.github.wasiqb.boyka.enums.Message.ELEMENT_NOT_FOUND;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Collections.singletonList;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.elements.IFingerActions;
import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.enums.SwipeDirection;
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

    FingerActions () {
        super (null);
    }

    FingerActions (final Locator locator) {
        super (locator);
    }

    @Override
    public void dragTo (final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Dragging [{}] to [{}] on Mobile devices.", this.locator.getName (), destination.getName ());
        final var dragSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .element (this.locator)
            .targetElement (destination)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (dragSequence));
        LOGGER.traceExit ();
    }

    @Override
    public void swipe (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping down on Mobile devices.");
        final var swipeUpSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (direction)
            .element (this.locator)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }

    @Override
    public void swipeTill (final SwipeDirection direction) {
        LOGGER.traceEntry ();
        final var maxSwipe = this.swipeSetting.getMaxSwipeUntilFound ();
        var swipeCounts = 0;
        while (!isDisplayed () && swipeCounts++ < maxSwipe) {
            swipe (direction);
        }
        if (!isDisplayed ()) {
            throwError (ELEMENT_NOT_FOUND, this.locator.getName (), getSession ().getPlatformType ());
        }
        LOGGER.traceExit ();
    }

    @Override
    public void tap () {
        LOGGER.traceEntry ();
        final var sequences = getElementAttribute (element -> FingerGestureBuilder.init ()
            .element (this.locator)
            .build ()
            .tapOn (), this.locator, null);
        performMobileGestures (singletonList (sequences));
        LOGGER.traceExit ();
    }
}
