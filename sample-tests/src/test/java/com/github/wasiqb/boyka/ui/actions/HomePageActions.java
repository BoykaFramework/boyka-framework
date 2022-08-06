package com.github.wasiqb.boyka.ui.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.ui.pages.HomePage.homePage;

/**
 * Home page actions
 * @author Faisal Khatri
 * @since 30/07/2022
 */

public class HomePageActions {

    public static HomePageActions homePageActions () {
        return new HomePageActions ();
    }

    public RegistrationPageActions openUserRegistrationPage () {
        clickOn (homePage ().getOpenMyAccountMenu ());
        clickOn (homePage ().getRegisterLink ());
        return new RegistrationPageActions ();
    }

    public ProductPageActions shopByCategory (final String linkName) {
        clickOn (homePage ().getShopByCategory ());
        clickOn (homePage ().selectCategory (linkName));
        return new ProductPageActions ();
    }

}
