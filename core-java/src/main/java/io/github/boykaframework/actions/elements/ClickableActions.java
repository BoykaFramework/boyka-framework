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
import static io.github.boykaframework.actions.CommonActions.performMobileGestures;
import static io.github.boykaframework.enums.ListenerType.CLICKABLE_ACTION;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static java.util.Collections.singletonList;
import static java.util.Optional.ofNullable;
import static org.apache.logging.log4j.LogManager.getLogger;

import io.github.boykaframework.actions.interfaces.elements.IClickableActions;
import io.github.boykaframework.actions.interfaces.listeners.elements.IClickableActionsListener;
import io.github.boykaframework.builders.Locator;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;

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
        this.listener = getSession ().getListener (CLICKABLE_ACTION);
    }

    @Override
    public void click () {
        LOGGER.traceEntry ();
        LOGGER.info ("Clicking on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClick (this.locator));
        pause (this.delaySetting.getBeforeClick ());
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .click ();
        LOGGER.traceExit ();
    }

    @Override
    public void clickAndHold () {
        LOGGER.traceEntry ();
        LOGGER.info ("Click and hold on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onClickAndHold (this.locator));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .clickAndHold ();
        LOGGER.traceExit ();
    }

    @Override
    public void doubleClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Double Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDoubleClick (this.locator));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .doubleClick ();
        LOGGER.traceExit ();
    }

    @Override
    public void dragTo (final Locator destination) {
        LOGGER.traceEntry ();
        LOGGER.info ("Drag and Drop on element: {} , {}", this.locator.getName (), destination.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onDragTo (this.locator, destination));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .dragAndDrop (destination);
        LOGGER.traceExit ();
    }

    @Override
    public void hover () {
        LOGGER.traceEntry ();
        LOGGER.info ("Hover on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onHover (this.locator));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .moveTo ();
        LOGGER.traceExit ();
    }

    @Override
    public void pressBackButton () {
        LOGGER.traceEntry ();
        LOGGER.info ("Pressing the Mouse Back button...");
        ofNullable (this.listener).ifPresent (IClickableActionsListener::onPressBackButton);
        final var sequence = MouseActionBuilder.builder ()
            .build ()
            .backButtonClick ();
        performMobileGestures (singletonList (sequence));
        LOGGER.traceExit ();
    }

    @Override
    public void pressForwardButton () {
        LOGGER.traceEntry ();
        LOGGER.info ("Pressing the Mouse Forward button...");
        ofNullable (this.listener).ifPresent (IClickableActionsListener::onPressForwardButton);
        final var sequence = MouseActionBuilder.builder ()
            .build ()
            .forwardButtonClick ();
        performMobileGestures (singletonList (sequence));
        LOGGER.traceExit ();
    }

    @Override
    public void rightClick () {
        LOGGER.traceEntry ();
        LOGGER.info ("Right Click on element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onRightClick (this.locator));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .rightClick ();
        LOGGER.traceExit ();
    }

    @Override
    public void scrollToElement () {
        LOGGER.traceEntry ();
        LOGGER.info ("Scrolling to element: {}", this.locator.getName ());
        ofNullable (this.listener).ifPresent (l -> l.onScrollToElement (this.locator));
        MouseActionBuilder.builder ()
            .sourceLocator (this.locator)
            .build ()
            .scrollTo ();
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
