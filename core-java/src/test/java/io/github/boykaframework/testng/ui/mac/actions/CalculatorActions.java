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

package io.github.boykaframework.testng.ui.mac.actions;

import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.testng.ui.mac.pages.CalculatorPage.calculatorPage;

/**
 * Calculator action class.
 *
 * @author Wasiq Bhamla
 * @since 09-May-2025
 */
public final class CalculatorActions {
    public static void verifyAdd (final int a, final int b) {
        final var expected = a + b;

        withMouse (calculatorPage ().getNumber (a)).click ();
        withMouse (calculatorPage ().getAdd ()).click ();
        withMouse (calculatorPage ().getNumber (b)).click ();
        withMouse (calculatorPage ().getEquals ()).click ();

        onElement (calculatorPage ().getInput ()).verifyText ()
            .endsWith (Integer.toString (expected));
    }

    private CalculatorActions () {
        // Utility class.
    }
}
