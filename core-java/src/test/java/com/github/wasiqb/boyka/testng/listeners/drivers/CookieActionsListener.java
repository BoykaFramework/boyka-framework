/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.listeners.drivers;

import static io.qameta.allure.Allure.step;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.ICookieActionsListener;

public class CookieActionsListener implements ICookieActionsListener {
    @Override
    public void onCookie (final String name) {
        step (format ("Getting Cookie for name [{0}]...", name));
    }

    @Override
    public void onCookies () {
        step ("Getting all the cookies...");
    }

    @Override
    public void onDelete (final String name) {
        step (format ("Deleting cookie of name [{0}]...", name));
    }

    @Override
    public void onDeleteAll () {
        step ("Deleting all the cookies...");
    }
}
