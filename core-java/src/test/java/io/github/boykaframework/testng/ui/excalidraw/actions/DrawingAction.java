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

package io.github.boykaframework.testng.ui.excalidraw.actions;

import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.MOVE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PAUSE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PRESS;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.RELEASE;
import static io.github.boykaframework.testng.ui.excalidraw.pages.HomePage.homePage;
import static java.time.Duration.ofMillis;

import java.util.ArrayList;

import io.github.boykaframework.actions.elements.MouseAction;
import org.openqa.selenium.Point;

/**
 * Handles drawing actions.
 *
 * @author Wasiq Bhamla
 * @since 11-Jan-2025
 */
public final class DrawingAction {
    /**
     * Draw a Circle.
     *
     * @param centerX Center on the x-axis
     * @param centerY Center on the y-axis
     * @param radius Radius of the circle
     */
    public static void drawCircle (final int centerX, final int centerY, final int radius) {
        drawCircle (centerX, centerY, radius, 0, 360);
    }

    /**
     * Draw an Arc.
     *
     * @param centerX Center on the x-axis
     * @param centerY Center on the y-axis
     * @param radius Radius of the circle
     */
    public static void drawSmile (final int centerX, final int centerY, final int radius) {
        drawCircle (centerX, centerY, radius, 30, 150);
    }

    private static void drawCircle (final int centerX, final int centerY, final int radius, final int startIndex,
        final int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            final double angle = Math.toRadians (i);
            final int x = (int) (centerX + radius * Math.cos (angle));
            final int y = (int) (centerY + radius * Math.sin (angle));

            final var steps = new ArrayList<MouseAction> ();
            steps.add (MouseAction.composeAction ()
                .actionType (MOVE)
                .duration (ofMillis (5))
                .location (new Point (x, y))
                .compose ());
            steps.add (MouseAction.composeAction ()
                .actionType (PRESS)
                .compose ());
            steps.add (MouseAction.composeAction ()
                .actionType (PAUSE)
                .duration (ofMillis (10))
                .compose ());
            steps.add (MouseAction.composeAction ()
                .actionType (RELEASE)
                .compose ());

            withMouse (homePage ().getCanvas ()).draw (steps);
        }
    }

    private DrawingAction () {
        // Utility class.
    }
}
