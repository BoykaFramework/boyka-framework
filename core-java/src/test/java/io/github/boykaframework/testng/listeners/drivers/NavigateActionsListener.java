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

package io.github.boykaframework.testng.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import io.github.boykaframework.actions.interfaces.listeners.drivers.INavigateActionsListener;

public class NavigateActionsListener implements INavigateActionsListener {
    @Override
    public void onBack () {
        step ("Navigating Back...");
    }

    @Override
    public void onForward () {
        step ("Navigating Forward...");
    }

    @Override
    public void onGetUrl () {
        step ("Getting the current page URL...");
    }

    @Override
    public void onRefresh () {
        step ("Refreshing current page...");
    }

    @Override
    public void onTo (final String url) {
        step (format ("Navigating to [{0}]...", url));
    }

    @Override
    public void onVerifyUrl () {
        step ("Verifying the current URL...");
    }
}
