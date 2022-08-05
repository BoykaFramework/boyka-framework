package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.ProductPage.productPage;

import java.text.MessageFormat;

/**
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
public class ProductPageActions {

    public ProductPageActions addPalmTreoCameraLensToCart () {
        hoverOn (productPage ().getPalmTreoCameraLens ());
        hoverOn (productPage ().getAddToCartBtn ());
        clickOn (productPage ().getAddToCartBtn ());
        return this;
    }

    public CheckoutPageActions checkoutProduct () {
        clickOn (productPage ().getCheckoutBtn ());
        return new CheckoutPageActions ();
    }

    public ProductPageActions verifySuccessMessage () {
        final String expectedSuccessMessage = "{0}\n{1}\n{2}\n{3}\n{4}";
        System.out.println ("Success message text is " + textOf (productPage ().getSuccessMessage ()));
        verifyTextOf (productPage ().getSuccessMessage ()).isEqualTo (
            MessageFormat.format (expectedSuccessMessage, "Success: You have added", "Palm Treo Pro", "to your",
                "shopping cart", "!"));
        return this;
    }
}
