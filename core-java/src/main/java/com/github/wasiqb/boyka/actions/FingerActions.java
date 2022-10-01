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

package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.getElementAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static java.time.Duration.ofMillis;
import static java.util.Collections.singletonList;
import static org.openqa.selenium.interactions.PointerInput.Kind.TOUCH;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import com.github.wasiqb.boyka.builders.Locator;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/**
 * Finger specific gestures.
 *
 * @author Wasiq Bhamla
 * @since 30-Sept-2022
 */
public class FingerActions {
    public static void tapOn (final Locator locator) {
        final var tapAction = getElementAttribute (element -> {
            final var finger = new PointerInput (TOUCH, "Finger");
            final var sequence = new Sequence (finger, 0);
            final var start = getElementCenter (element);
            sequence.addAction (finger.createPointerMove (ofMillis (100), viewport (), start.getX (), start.getY ()));
            sequence.addAction (finger.createPointerDown (LEFT.asArg ()));
            sequence.addAction (finger.createPointerUp (LEFT.asArg ()));
            //            sequence.addAction (finger.createPointerMove (ofMillis (100), viewport (), 0, 0));
            return sequence;
        }, locator);
        performDriverAction (driver -> ((AppiumDriver) driver).perform (singletonList (tapAction)));
    }

    private static Point getElementCenter (final WebElement element) {
        final var location = element.getLocation ();
        final var size = element.getSize ();
        final var x = location.getX () + (size.getWidth () / 2);
        final var y = location.getY () + (size.getHeight () / 2);
        return new Point (x, y);
    }

    private FingerActions () {
        // Utility class,
    }
}
