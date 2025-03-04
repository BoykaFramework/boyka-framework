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

package io.github.boykaframework.actions.elements;

import static io.github.boykaframework.actions.CommonActions.performDriverAction;
import static io.github.boykaframework.actions.CommonActions.performElementAction;
import static io.github.boykaframework.actions.elements.ElementFinder.find;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PAUSE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PRESS;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.RELEASE;
import static io.github.boykaframework.enums.Message.ELEMENT_CANNOT_BE_NULL;
import static io.github.boykaframework.enums.WaitStrategy.CLICKABLE;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.Validator.requireNonNull;
import static java.time.Duration.ofMillis;
import static java.util.Objects.isNull;
import static org.openqa.selenium.interactions.PointerInput.Kind.MOUSE;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.BACK;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.FORWARD;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.util.List;
import java.util.function.BiFunction;

import io.github.boykaframework.builders.Locator;
import lombok.Builder;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

/**
 * Handle all the mouse specific actions.
 *
 * @author Wasiq Bhamla
 * @since 27-Dec-2024
 */
@Builder (builderMethodName = "buildAction")
public class MouseActionBuilder {
    private Locator sourceLocator;

    Sequence backButtonClick () {
        return mouseButtonAction (BACK);
    }

    void click () {
        performElementAction ((d, e) -> new Actions (d).click (e)
            .perform (), this.sourceLocator);
    }

    void clickAndHold () {
        performElementAction ((d, e) -> new Actions (d).clickAndHold (e)
            .perform (), this.sourceLocator);
    }

    Sequence composeActions (final List<MouseAction> actions) {
        return composeMouseSequence ((mouse, steps) -> {
            actions.forEach (action -> {
                switch (action.getActionType ()) {
                    case MOVE -> steps.addAction (
                        mouse.createPointerMove (action.getDuration (), viewport (), action.getLocation ()));
                    case PAUSE -> steps.addAction (new Pause (mouse, action.getDuration ()));
                    case PRESS -> steps.addAction (mouse.createPointerDown (action.getButton ()
                        .asArg ()));
                    default -> steps.addAction (mouse.createPointerUp (action.getButton ()
                        .asArg ()));
                }
            });
            return steps;
        });
    }

    void doubleClick () {
        performElementAction ((d, e) -> new Actions (d).doubleClick (e)
            .perform (), this.sourceLocator);
    }

    void dragAndDrop (final Locator targetLocator) {
        final var targetElement = find (requireNonNull (targetLocator, ELEMENT_CANNOT_BE_NULL), CLICKABLE);
        performElementAction ((d, e) -> new Actions (d).dragAndDrop (e, targetElement)
            .perform (), this.sourceLocator);
    }

    Sequence forwardButtonClick () {
        return mouseButtonAction (FORWARD);
    }

    void moveTo () {
        performElementAction ((d, e) -> new Actions (d).moveToElement (e)
            .perform (), this.sourceLocator);
    }

    void rightClick () {
        if (isNull (this.sourceLocator)) {
            performDriverAction (d -> new Actions (d).contextClick ()
                .perform ());
        } else {
            performElementAction ((d, e) -> new Actions (d).contextClick (e)
                .perform (), this.sourceLocator);
        }
    }

    void scrollTo () {
        performElementAction ((d, e) -> new Actions (d).scrollToElement (e)
            .perform (), this.sourceLocator);
    }

    private Sequence composeMouseSequence (final BiFunction<PointerInput, Sequence, Sequence> steps) {
        final var mouse = new PointerInput (MOUSE, "Default Mouse");
        final var sequence = new Sequence (mouse, 0);
        return steps.apply (mouse, sequence);
    }

    private Sequence mouseButtonAction (final PointerInput.MouseButton button) {
        final var delaySetting = getSession ().getSetting ()
            .getUi ()
            .getDelay ();

        final var actions = List.of (MouseAction.composeAction ()
            .button (button)
            .actionType (PRESS)
            .compose (), MouseAction.composeAction ()
            .actionType (PAUSE)
            .duration (ofMillis (delaySetting.getBeforeSwipe ()))
            .compose (), MouseAction.composeAction ()
            .actionType (RELEASE)
            .button (button)
            .compose ());

        return composeActions (actions);
    }
}
