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

import io.github.boykaframework.actions.interfaces.listeners.elements.IClickableActionsListener;
import io.github.boykaframework.builders.Locator;

public class ClickableActionsListener implements IClickableActionsListener {
    @Override
    public void onClick (final Locator locator) {
        step (format ("Clicking on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onClickAndHold (final Locator locator) {
        step (format ("Click and Hold on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onDoubleClick (final Locator locator) {
        step (format ("Double clicking on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onDragTo (final Locator source, final Locator destination) {
        step (format ("Dragging element [{0}] to element [{1}]...", source.getName (), destination.getName ()));
    }

    @Override
    public void onHover (final Locator locator) {
        step (format ("Hovering on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onRightClick (final Locator locator) {
        step (format ("Right Clicking on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onSubmit (final Locator locator) {
        step (format ("Submitting on element [{0}]...", locator.getName ()));
    }
}
