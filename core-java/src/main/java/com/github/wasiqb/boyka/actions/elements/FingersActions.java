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

import static com.github.wasiqb.boyka.enums.ListenerType.FINGERS_ACTION;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static java.util.Optional.ofNullable;

import com.github.wasiqb.boyka.actions.interfaces.elements.IFingersActions;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IFingersActionsListener;
import com.github.wasiqb.boyka.builders.Locator;

/**
 * Handles all multi-fingers related actions
 *
 * @author Wasiq Bhamla
 * @since 15-Feb-2023
 */
public class FingersActions extends FingerActions implements IFingersActions {
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
        this.listener = getSession ().getSetting ()
            .getListener (FINGERS_ACTION);
    }

    @Override
    public void zoomIn () {
        ofNullable (this.listener).ifPresent (IFingersActionsListener::onZoomIn);
        // TODO: need to implement.
    }

    @Override
    public void zoomOut () {
        ofNullable (this.listener).ifPresent (IFingersActionsListener::onZoomOut);
        // TODO: need to implement.
    }
}
