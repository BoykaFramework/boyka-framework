/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.ui.pages.ProductPage.productPage;
import static java.text.MessageFormat.format;

/**
 * Product page actions
 *
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
public class ProductPageActions {
    public ProductPageActions addPalmTreoCameraLensToCart () {
        withMouse (productPage ().getPalmTreoCameraLens ()).hover ();
        withMouse (productPage ().getAddToCartBtn ()).hover ();
        withMouse (productPage ().getAddToCartBtn ()).click ();
        return this;
    }

    public CheckoutPageActions checkoutProduct () {
        withMouse (productPage ().getCheckoutBtn ()).click ();
        return new CheckoutPageActions ();
    }

    public ProductPageActions verifySuccessMessage () {
        final String expectedSuccessMessage = "{0}\n{1}\n{2}\n{3}\n{4}";
        System.out.println ("Success message text is " + onElement (productPage ().getSuccessMessage ()).getText ());
        onElement (productPage ().getSuccessMessage ()).verifyText ()
            .isEqualTo (
                format (expectedSuccessMessage, "Success: You have added", "Palm Treo Pro", "to your", "shopping cart",
                    "!"));
        return this;
    }
}
