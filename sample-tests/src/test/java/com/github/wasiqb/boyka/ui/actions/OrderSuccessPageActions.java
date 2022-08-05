package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.DriverActions.waitUntil;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.OrderSuccessPage.orderSuccessPage;

import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public OrderSuccessPageActions verifySuccessMessage () {
        waitUntil (ExpectedConditions.textToBePresentInElementLocated (orderSuccessPage ().getSuccessMessage ()
            .getWeb (), "Your order has been placed!"));
        verifyTextOf (orderSuccessPage ().getSuccessMessage ()).isEqualTo ("Your order has been placed!");
        return this;
    }

}
