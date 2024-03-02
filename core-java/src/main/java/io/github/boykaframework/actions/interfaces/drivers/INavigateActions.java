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

package io.github.boykaframework.actions.interfaces.drivers;

import com.google.common.truth.StringSubject;

/**
 * All navigation related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface INavigateActions {
    /**
     * Navigate back to previous page on browser.
     */
    void back ();

    /**
     * Navigate forward to next page on browser.
     */
    void forward ();

    /**
     * Get current url of the browser.
     *
     * @return current url of the browser
     */
    String getUrl ();

    /**
     * Refreshes browser page.
     */
    void refresh ();

    /**
     * Navigate to url on browser.
     *
     * @param url url to navigate to
     */
    void to (final String url);

    /**
     * Navigate to the base URL.
     */
    void toBaseUrl ();

    /**
     * Verify browser url.
     *
     * @return {@link StringSubject} to verify browser url
     */
    StringSubject verifyUrl ();
}
