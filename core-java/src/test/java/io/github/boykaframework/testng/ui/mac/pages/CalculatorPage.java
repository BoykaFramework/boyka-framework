/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

package io.github.boykaframework.testng.ui.mac.pages;

import static io.appium.java_client.AppiumBy.iOSClassChain;
import static io.appium.java_client.AppiumBy.iOSNsPredicateString;
import static java.text.MessageFormat.format;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Calculator page class.
 *
 * @author Wasiq Bhamla
 * @since 09-May-2025
 */
@Getter
public class CalculatorPage {
    private static final CalculatorPage CALCULATOR_PAGE = new CalculatorPage ();

    /**
     * Gets the page instance.
     *
     * @return Page instance
     */
    public static CalculatorPage calculatorPage () {
        return CALCULATOR_PAGE;
    }

    private final Locator add    = Locator.buildLocator ()
        .name ("Add")
        .mac (iOSNsPredicateString ("label == \"Add\""))
        .build ();
    private final Locator equals = Locator.buildLocator ()
        .name ("Equals")
        .mac (iOSNsPredicateString ("label == \"Equals\""))
        .build ();
    private final Locator input  = Locator.buildLocator ()
        .name ("Input")
        .mac (iOSClassChain ("**/XCUIElementTypeScrollView[`label == \"Input\"`]/XCUIElementTypeStaticText"))
        .build ();

    private CalculatorPage () {
        // Singleton class.
    }

    /**
     * Gets the number on the calculator.
     *
     * @param number Digit
     *
     * @return Locator of the element
     */
    public Locator getNumber (final int number) {
        if (number < 0 || number > 9) {
            throw new IllegalArgumentException ("Number must be between 0 and 9");
        }
        return Locator.buildLocator ()
            .name (format ("Number {0}", number))
            .mac (iOSNsPredicateString (format ("label == \"{0}\"", number)))
            .build ();
    }
}
