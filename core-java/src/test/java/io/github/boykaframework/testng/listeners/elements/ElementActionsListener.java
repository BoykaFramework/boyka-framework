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

import io.github.boykaframework.actions.interfaces.listeners.elements.IElementActionsListener;
import io.github.boykaframework.builders.Locator;

public class ElementActionsListener implements IElementActionsListener {
    @Override
    public void onClear (final Locator locator) {
        step (format ("Clearing value in element [{0}]...", locator.getName ()));
    }

    @Override
    public void onGetAttribute (final Locator locator, final String attribute) {
        step (format ("Getting attribute [{0}] on element [{1}]...", attribute, locator.getName ()));
    }

    @Override
    public void onGetStyle (final Locator locator, final String styleName) {
        step (format ("Getting Style attribute [{0}] on element [{1}]...", styleName, locator.getName ()));
    }

    @Override
    public void onGetText (final Locator locator) {
        step (format ("Getting Text on element [{0}]...", locator.getName ()));
    }

    @Override
    public void onIsDisplayed (final Locator locator) {
        step (format ("Checking if element [{0}] is displayed...", locator.getName ()));
    }

    @Override
    public void onIsEnabled (final Locator locator) {
        step (format ("Checking if element [{0}] is enabled...", locator.getName ()));
    }

    @Override
    public void onIsSelected (final Locator locator) {
        step (format ("Checking if element [{0}] is selected...", locator.getName ()));
    }

    @Override
    public void onScrollIntoView (final Locator locator) {
        step (format ("Scrolling element [{0}] into view...", locator.getName ()));
    }

    @Override
    public void onVerifyAttribute (final Locator locator, final String attribute) {
        step (format ("Verifying attribute [{0}] on element [{1}]...", attribute, locator.getName ()));
    }

    @Override
    public void onVerifyIsDisplayed (final Locator locator) {
        step (format ("Verifying if element [{0}] is displayed...", locator.getName ()));
    }

    @Override
    public void onVerifyIsEnabled (final Locator locator) {
        step (format ("Verifying if element [{0}] is enabled...", locator.getName ()));
    }

    @Override
    public void onVerifyIsSelected (final Locator locator) {
        step (format ("Verifying if element [{0}] is selected...", locator.getName ()));
    }

    @Override
    public void onVerifyStyle (final Locator locator, final String styleName) {
        step (format ("Verifying the style [{0}] of element [{1}]...", styleName, locator.getName ()));
    }

    @Override
    public void onVerifyText (final Locator locator) {
        step (format ("Verifying the text of element [{0}]...", locator.getName ()));
    }
}
