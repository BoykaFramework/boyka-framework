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

package com.github.wasiqb.boyka.testng.ui.wdio.pages;

import static com.github.wasiqb.boyka.actions.drivers.AlertActions.onAlert;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static io.appium.java_client.AppiumBy.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Alert dialog pop-up.
 *
 * @author Wasiq Bhamla
 * @since 21-Oct-2023
 */
@Getter
public class AlertDialog {
    private static final AlertDialog ALERT_DIALOG = new AlertDialog ();

    /**
     * Alert dialog pop-up instance.
     *
     * @return {@link AlertDialog}
     */
    public static AlertDialog alertDialog () {
        return ALERT_DIALOG;
    }

    private final Locator button1 = Locator.buildLocator ()
        .name ("Button 1")
        .android (id ("android:id/button1"))
        .build ();
    private final Locator message = Locator.buildLocator ()
        .name ("Message")
        .android (id ("android:id/message"))
        .build ();
    private final Locator title   = Locator.buildLocator ()
        .name ("Title")
        .android (id ("android:id/alertTitle"))
        .build ();

    private AlertDialog () {
        // Page class.
    }

    /**
     * Accepts pop-up and verify message.
     */
    public void verifyMessage (final String expectedMessage) {
        if (getSession ().getPlatformType () == ANDROID) {
            try {
                onElement (getMessage ()).verifyText ()
                    .isEqualTo (expectedMessage);
            } finally {
                withFinger (getButton1 ()).tap ();
            }
        } else {
            onAlert ().verifyAccept (expectedMessage);
        }
    }
}
