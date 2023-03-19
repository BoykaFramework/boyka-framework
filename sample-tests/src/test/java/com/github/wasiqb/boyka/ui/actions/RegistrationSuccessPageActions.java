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
import static com.github.wasiqb.boyka.ui.pages.RegistrationSuccessPage.registrationSuccessPage;

/**
 * Registration success page actions
 *
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
public class RegistrationSuccessPageActions {
    public MyAccountPageActions continueToMyAccount () {
        withMouse (registrationSuccessPage ().getContinueBtn ()).click ();
        return new MyAccountPageActions ();
    }

    public RegistrationSuccessPageActions verifySuccessfulRegistration () {
        onElement (registrationSuccessPage ().getPageHeader ()).verifyText ()
            .isEqualTo ("Your Account Has Been Created!");
        return this;
    }
}
