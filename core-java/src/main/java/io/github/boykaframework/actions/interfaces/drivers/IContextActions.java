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

import java.util.List;

/**
 * Handles driver contexts
 *
 * @author Wasiq Bhamla
 * @since 09-Mar-2023
 */
public interface IContextActions {
    /**
     * List all available context names.
     *
     * @return List of contexts
     */
    List<String> contexts ();

    /**
     * Gets Current context name
     *
     * @return Current context name
     */
    String currentContext ();

    /**
     * Switch back to Native app context.
     */
    void switchToNative ();

    /**
     * Switch context to the mentioned WebView context.
     *
     * @param name Name of the context
     */
    void switchToWebView (String name);

    /**
     * Switch context to the first available WebView context.
     */
    void switchToWebView ();
}
