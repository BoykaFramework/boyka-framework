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
import static com.github.wasiqb.boyka.ui.pages.HomePage.homePage;

/**
 * Home page actions
 *
 * @author Faisal Khatri
 * @since 30/07/2022
 */

public class HomePageActions {
    public static HomePageActions homePageActions () {
        return new HomePageActions ();
    }

    public RegistrationPageActions openUserRegistrationPage () {
        withMouse (homePage ().getOpenMyAccountMenu ()).click ();
        withMouse (homePage ().getRegisterLink ()).click ();
        return new RegistrationPageActions ();
    }

    public ProductPageActions shopByCategory (final String linkName) {
        withMouse (homePage ().getShopByCategory ()).click ();
        withMouse (homePage ().selectCategory (linkName)).click ();
        return new ProductPageActions ();
    }
}
