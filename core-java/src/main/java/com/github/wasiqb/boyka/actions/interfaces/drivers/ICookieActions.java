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

package com.github.wasiqb.boyka.actions.interfaces.drivers;

import java.util.List;

import org.openqa.selenium.Cookie;

/**
 * All cookies related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface ICookieActions {
    /**
     * Gets a particular cookie from browser.
     *
     * @param name Cookie name
     *
     * @return {@link Cookie}
     */
    Cookie cookie (final String name);

    /**
     * Gets all the browser cookies.
     *
     * @return List of cookie names.
     */
    List<String> cookies ();

    /**
     * Deletes browser cookie.
     *
     * @param name cookie name
     */
    void delete (final String name);

    /**
     * Deletes all browser cookies.
     */
    void deleteAll ();
}
