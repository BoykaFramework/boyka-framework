package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.uitests.pages.ProductPage.productPage;

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
        return CheckoutPageActions.checkoutPageActions ();
    }

    public ProductPageActions verifySuccessMessage () {
        final String expectedSuccessMessage = "{0}{1}{2}{3}{4}";
        verifyTextOf (productPage ().getSuccessMessage ()).isEqualTo (
            MessageFormat.format (expectedSuccessMessage, "Success: You have added", "Palm Treo Pro", "to your",
                "shopping cart", "!"));
        return this;
    }
}
