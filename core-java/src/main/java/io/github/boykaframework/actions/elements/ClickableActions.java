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

import static io.github.boykaframework.actions.CommonActions.pause;
import static io.github.boykaframework.actions.CommonActions.performElementAction;
import static io.github.boykaframework.actions.elements.ElementFinder.find;
import static io.github.boykaframework.enums.WaitStrategy.CLICKABLE;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.github.boykaframework.actions.interfaces.elements.IClickableActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IClickableActionsListener;
import io.github.boykaframework.builders.Locator;
import io.github.boykaframework.enums.ApplicationType;
import io.github.boykaframework.enums.ListenerType;
import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.manager.ParallelSession;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Handles all mouse related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public class ClickableActions extends FingersActions implements IClickableActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handles all mouse related actions.
     *
     * @param locator Locator of the elements.
     *
     * @return {@link IClickableActions} instance object
     */
    public static IClickableActions withMouse (final Locator locator) {
        return new ClickableActions (locator);
    }

    private final IClickableActionsListener listener;

    ClickableActions (final Locator locator) {
        super (locator);
        this.listener = ParallelSession.getSession ()
            .getListener (ListenerType.CLICKABLE_ACTION);
    }

    @Override
    public void click () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.locator));
        final var session = ParallelSession.getSession ();
        if (session.getPlatformType () == PlatformType.WEB || (session.getMobileSetting ()
            .getDevice ()
            .getApplication ()
            .getType () == ApplicationType.WEB && session.getPlatformType () == PlatformType.IOS)) {
            pause (this.delaySetting.getBeforeClick ());
            scrollIntoView ();
            performElementAction (WebElement::click, this.locator);
        } else {
            tap ();
        }
        LOGGER.traceExit ();
    }

    @Override
    public void clickAndHold () {
        LOGGER.traceEntry ();
        LOGGER.info ("Click and hold on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClickAndHold (this.locator));
        pause (this.delaySetting.getBeforeClick ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.clickAndHold (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void doubleClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDoubleClick (this.locator));
        pause (this.delaySetting.getBeforeClick ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.doubleClick (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void dragTo (final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Drag and Drop on element: {} , {}", this.locator.getName (), destination.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDragTo (this.locator, destination));
        pause (this.delaySetting.getBeforeClick ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.dragAndDrop (element, find (destination, CLICKABLE))
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void hover () {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onHover (this.locator));
        pause (this.delaySetting.getBeforeMouseMove ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.moveToElement (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void rightClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onRightClick (this.locator));
        pause (this.delaySetting.getBeforeClick ());
        performElementAction ((driver, element) -> {
            final var actions = new Actions (driver);
            actions.contextClick (element)
                .perform ();
        }, this.locator);
        LOGGER.traceExit ();
    }

    @Override
    public void submit () {
        LOGGER.traceEntry ();
        LOGGER.info ("Submitting element located by: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onSubmit (this.locator));
        performElementAction (WebElement::submit, this.locator);
        LOGGER.traceExit ();
    }
}
