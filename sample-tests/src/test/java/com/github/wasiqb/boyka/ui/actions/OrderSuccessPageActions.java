package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.OrderSuccessPage.orderSuccessPage;

/**
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
public class OrderSuccessPageActions {
    public static OrderSuccessPageActions orderSuccessPageActions () {
        return new OrderSuccessPageActions ();
    }

    public void continueToHomePage () {
        clickOn (orderSuccessPage ().getContinueBtn ());
    }

    public void verifySuccessMessage () {
        verifyTextOf (orderSuccessPage ().getSuccessMessage ()).isEqualTo ("Your order has been placed!");
    }

}
