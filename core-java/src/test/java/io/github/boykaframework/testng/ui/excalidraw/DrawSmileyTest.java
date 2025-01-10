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

package io.github.boykaframework.testng.ui.excalidraw;

import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.MOVE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PAUSE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PRESSED;
import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.excalidraw.pages.HomePage.homePage;
import static java.time.Duration.ofMillis;

import java.util.ArrayList;

import io.github.boykaframework.actions.elements.MouseAction;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test drawing a diagram.
 *
 * @author Wasiq Bhamla
 * @since 10-Jan-2025
 */
public class DrawSmileyTest {
    @BeforeTest
    public void setupTest () {
        createSession (WEB, "test_draw");
    }

    @AfterTest
    public void tearDownTest () {
        clearSession ();
    }

    @Test
    public void testDrawSmiley () {
        withDriver ().pressKeys (Keys.chord ("p"));
        drawSmiley ();
    }

    private void drawSmiley () {
        final var size = onElement (homePage ().getCanvas ()).getSize ();
        final var location = onElement (homePage ().getCanvas ()).getLocation ();
        final var centerX = location.getX () + (size.getWidth () / 2);
        final var centerY = location.getY () + (size.getHeight () / 2);

        final var steps = new ArrayList<MouseAction> ();
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX + 60, centerY - 91))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (PRESSED)
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (PAUSE)
            .duration (ofMillis (10))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX - 77, centerY + 46))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX + 44, centerY + 48))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX - 44, centerY + 136))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX - 10, centerY + 69))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (100))
            .location (new Point (centerX - 99, centerY + 68))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (PAUSE)
            .duration (ofMillis (1000))
            .compose ());
        steps.add (MouseAction.composeAction ()
            .actionType (MOVE)
            .duration (ofMillis (10))
            .location (new Point (centerX - 99, centerY + 68))
            .compose ());

        withMouse (homePage ().getCanvas ()).draw (steps);
    }
}
