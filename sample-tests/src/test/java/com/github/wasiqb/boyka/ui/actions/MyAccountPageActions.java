package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.ui.pages.MyAccountPage.myAccountPage;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class MyAccountPageActions {

    public void verifyPageHeader () {
        verifyTextOf (myAccountPage ().getPageHeader ()).isEqualTo ("My Account");
    }
}
