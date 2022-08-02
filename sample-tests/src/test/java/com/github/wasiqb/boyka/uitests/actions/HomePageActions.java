package com.github.wasiqb.boyka.uitests.actions;

import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.uitests.pages.HomePage.homePage;

import com.github.wasiqb.boyka.uitests.pages.ProductPage;
import com.github.wasiqb.boyka.uitests.pages.RegistrationPage;

public class HomePageActions {

    public RegistrationPage openUserRegistrationPage () {
        clickOn (homePage ().getOpenMyAccountMenu ());
        clickOn (homePage ().getRegisterLink ());
        return new RegistrationPage ();
    }

    public ProductPage shopByCategory (final String linkName) {
        clickOn (homePage ().getShopByCategory ());
        clickOn (homePage ().selectCategory (linkName));
        return new ProductPage ();
    }

}
