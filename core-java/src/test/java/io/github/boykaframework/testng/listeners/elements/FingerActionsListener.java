/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.listeners.elements;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import io.github.boykaframework.actions.interfaces.listeners.elements.IFingerActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.SwipeDirection;

public class FingerActionsListener implements IFingerActionsListener {
    @Override
    public void onDragTo (final Locator source, final Locator destination) {
        step (format ("Dragging element [{0}] to element [{1}]...", source.getName (), destination.getName ()));
    }

    @Override
    public void onSwipe (final Locator locator, final SwipeDirection direction) {
        step (format ("Swiping on element [{0}] in [{1}] direction...", locator, direction.name ()));
    }

    @Override
    public void onSwipeTill (final Locator locator, final SwipeDirection direction) {
        step (format ("Swiping till the element [{0}] is visible in [{1}] direction...", locator.getName (),
            direction.name ()));
    }

    @Override
    public void onTap (final Locator locator) {
        step (format ("Tapping on element [{0}]...", locator.getName ()));
    }
}
