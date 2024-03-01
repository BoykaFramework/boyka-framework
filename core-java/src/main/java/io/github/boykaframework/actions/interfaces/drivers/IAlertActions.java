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
 * All Alerts related actions
 *
 * @author Wasiq Bhamla
 * @since 16-Feb-2023
 */
public interface IAlertActions {
    /**
     * Accepts browser alert.
     *
     * @return the alert message
     */
    String accept ();

    /**
     * Enters text in browser alert and accept it.
     *
     * @param text Text to enter in alert
     *
     * @return the alert message
     */
    String accept (final String text);

    /**
     * Dismisses browser alert.
     *
     * @return the alert message
     */
    String dismiss ();

    /**
     * Verify alert text, enter text in prompt and accept the alert
     *
     * @param text Text to enter in prompt
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyAccept (final String text);

    /**
     * Verify alert text and accept the alert
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyAccept ();

    /**
     * Verify alert text and dismissing the alert
     *
     * @return {@link StringSubject}
     */
    StringSubject verifyDismiss ();
}
