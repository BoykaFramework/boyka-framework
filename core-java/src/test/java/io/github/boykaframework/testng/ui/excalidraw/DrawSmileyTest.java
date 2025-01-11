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
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.excalidraw.actions.DrawingAction.drawCircle;
import static io.github.boykaframework.testng.ui.excalidraw.actions.DrawingAction.drawSmile;
import static io.github.boykaframework.testng.ui.excalidraw.pages.HomePage.homePage;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.Keys;
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
        onWindow ().takeScreenshot ();
        withDriver ().pause (ofSeconds (5));
    }

    private void drawSmiley () {
        final var size = onElement (homePage ().getCanvas ()).getSize ();
        final var location = onElement (homePage ().getCanvas ()).getLocation ();
        final var centerX = location.getX () + (size.getWidth () / 2);
        final var centerY = location.getY () + (size.getHeight () / 2);

        // Draw the smiley face
        // 1. Draw the circle (face)
        drawCircle (centerX, centerY, 75);
        // 2. Draw the left eye
        drawCircle (centerX - 20, centerY - 20, 5);
        // 3. Draw the right eye
        drawCircle (centerX + 20, centerY - 20, 5);
        // 4. Draw the smile (arc)
        drawSmile (centerX, centerY + 10, 30);
    }
}
