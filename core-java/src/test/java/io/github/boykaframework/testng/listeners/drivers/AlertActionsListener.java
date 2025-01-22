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

package io.github.boykaframework.testng.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import io.github.boykaframework.actions.interfaces.listeners.drivers.IAlertActionsListener;

public class AlertActionsListener implements IAlertActionsListener {
    @Override
    public void onAccept () {
        step ("Accepting Alert");
    }

    @Override
    public void onAccept (final String text) {
        step (format ("Accepting Prompt Alert after entering [{0}] text", text));
    }

    @Override
    public void onDismiss () {
        step ("Dismissing Alert");
    }

    @Override
    public void onVerifyAccept (final String text) {
        step (format ("Verifying Alert message after Accepting Prompt Alert by entering [{0}] text", text));
    }

    @Override
    public void onVerifyAccept () {
        step ("Verifying Alert message after Accepting it");
    }

    @Override
    public void onVerifyDismiss () {
        step ("Verifying Alert message after Dismissing it");
    }
}
