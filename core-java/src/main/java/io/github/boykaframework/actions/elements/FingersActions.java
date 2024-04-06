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

import static io.github.boykaframework.actions.CommonActions.getDriverAttribute;
import static io.github.boykaframework.actions.CommonActions.performMobileGestures;
import static io.github.boykaframework.enums.ListenerType.FINGERS_ACTION;
import static io.github.boykaframework.enums.SwipeDirection.LEFT;
import static io.github.boykaframework.enums.SwipeDirection.RIGHT;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.util.Arrays.asList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.github.boykaframework.actions.interfaces.elements.IFingersActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IFingersActionsListener;
import io.github.boykaframework.builders.Locator;
import org.apache.logging.log4j.Logger;

/**
 * Handles all multi-fingers related actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public class FingersActions extends FingerActions implements IFingersActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all multi-fingers related actions
     *
     * @param locator Locator of the element
     *
     * @return {@link IFingersActions} instance object
     */
    public static IFingersActions withFingers (final Locator locator) {
        return new FingersActions (locator);
    }

    private final IFingersActionsListener listener;

    FingersActions (final Locator locator) {
        super (locator);
        this.listener = getSession ().getListener (FINGERS_ACTION);
    }

    @Override
    public void zoomIn () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming in on the element [{}].", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomIn (this.locator));
        final var finger1 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.locator)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.locator)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }

    @Override
    public void zoomOut () {
        LOGGER.traceEntry ();
        LOGGER.info ("Zooming out on the element [{}].", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onZoomOut (this.locator));
        final var finger1 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (LEFT)
            .name ("Finger 1")
            .sourceElement (this.locator)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        final var finger2 = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (RIGHT)
            .name ("Finger 2")
            .sourceElement (this.locator)
            .reverse (true)
            .offset (10)
            .build ()
            .swipe (), null);
        performMobileGestures (asList (finger1, finger2));
        LOGGER.traceExit ();
    }
}
