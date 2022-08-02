package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.uitests.pages.MyAccountPage.myAccountPage;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class MyAccountPageActions {

    public void verifyPageHeader () {
        verifyTextOf (myAccountPage ().getPageHeader ()).isEqualTo ("My Account");
    }
}
