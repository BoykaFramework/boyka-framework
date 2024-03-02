/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.testng.ui.saucedemo.actions;

import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.FingerActions.withFinger;
import static io.github.boykaframework.testng.ui.saucedemo.pages.CartPage.cartPage;
import static java.text.MessageFormat.format;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.github.boykaframework.actions.device.AndroidDeviceActions;
import io.github.boykaframework.actions.device.DeviceActions;
import io.github.boykaframework.actions.drivers.NavigateActions;
import io.github.boykaframework.actions.drivers.WindowActions;
import io.github.boykaframework.actions.elements.ElementActions;
import io.github.boykaframework.actions.elements.FingersActions;
import io.github.boykaframework.actions.elements.TextBoxActions;
import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.enums.SwipeDirection;
import io.github.boykaframework.manager.ParallelSession;
import io.github.boykaframework.testng.ui.saucedemo.pages.CheckoutPage;
import io.github.boykaframework.testng.ui.saucedemo.pages.HomePage;
import io.github.boykaframework.testng.ui.saucedemo.pages.LoginPage;
import io.github.boykaframework.testng.ui.saucedemo.pages.ProductDetailsPage;

public class SauceDemoActions {
    private static final String       URL = "https://www.saucedemo.com";
    private final        PlatformType platformType;

    public SauceDemoActions () {
        this.platformType = ParallelSession.getSession ()
            .getPlatformType ();
    }

    public void verifyAddToCart () {
        if (this.platformType == PlatformType.WEB) {
            withMouse (HomePage.homePage ()
                .getAddToCartButton ()).click ();
        } else {
            withFinger (HomePage.homePage ()
                .getAddToCartDragHandle ()).dragTo (HomePage.homePage ()
                .getCartDropZone ());
        }

        ElementActions.onElement (HomePage.homePage ()
                .getProductPrice ())
            .verifyText ()
            .isEqualTo ("$29.99");
        ElementActions.onElement (HomePage.homePage ()
                .getShoppingCartCount ())
            .verifyText ()
            .isEqualTo ("1");

        verifyProductDetailPage ();
        verifyProductCartPage ();
    }

    public void verifyCheckoutStep1 () {
        withMouse (cartPage ().getCheckout ()).click ();
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .verifyUrl ()
                .isEqualTo (format ("{0}/checkout-step-one.html", URL));
            ElementActions.onElement (CheckoutPage.checkoutPage ()
                    .getTitle ())
                .verifyText ()
                .ignoringCase ()
                .isEqualTo ("CHECKOUT: YOUR INFORMATION");
        }

        TextBoxActions.onTextBox (CheckoutPage.checkoutPage ()
                .getFirstName ())
            .enterText ("Wasiq");
        TextBoxActions.onTextBox (CheckoutPage.checkoutPage ()
                .getLastName ())
            .enterText ("Bhamla");
        TextBoxActions.onTextBox (CheckoutPage.checkoutPage ()
                .getZipCode ())
            .enterText ("12345");

        withMouse (CheckoutPage.checkoutPage ()
            .getContinueButton ()).click ();
    }

    public void verifyCheckoutStep2 () {
        if (this.platformType != PlatformType.WEB) {
            withFinger (CheckoutPage.checkoutPage ()
                .getFinish ()).swipeTill (SwipeDirection.UP);
        }
        withMouse (CheckoutPage.checkoutPage ()
            .getFinish ()).click ();

        if (this.platformType != PlatformType.WEB) {
            ElementActions.onElement (CheckoutPage.checkoutPage ()
                    .getCompleteHeader ())
                .verifyText ()
                .isEqualTo ("THANK YOU FOR YOU ORDER");
        } else {
            NavigateActions.navigate ()
                .verifyUrl ()
                .isEqualTo (format ("{0}/checkout-complete.html", URL));
            ElementActions.onElement (CheckoutPage.checkoutPage ()
                    .getTitle ())
                .verifyText ()
                .ignoringCase ()
                .isEqualTo ("CHECKOUT: COMPLETE!");
            ElementActions.onElement (CheckoutPage.checkoutPage ()
                    .getCompleteHeader ())
                .verifyText ()
                .ignoringCase ()
                .contains ("THANK YOU FOR YOUR ORDER");
        }

        ElementActions.onElement (CheckoutPage.checkoutPage ()
                .getCompleteText ())
            .verifyText ()
            .isEqualTo ("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    public void verifyLogin (final String userName, final String password) {
        verifyNavigateToSite ();
        TextBoxActions.onTextBox (LoginPage.loginPage ()
                .getUsername ())
            .enterText (userName);
        TextBoxActions.onTextBox (LoginPage.loginPage ()
                .getPassword ())
            .enterText (password);
        if (this.platformType == PlatformType.ANDROID && DeviceActions.onDevice ()
            .isKeyboardVisible ()) {
            AndroidDeviceActions.onAndroidDevice ()
                .pressKey (AndroidKey.BACK);
        }
        withMouse (LoginPage.loginPage ()
            .getLoginButton ()).click ();
        verifyLoggedIn ();
    }

    public void verifySignOut () {
        withMouse (HomePage.homePage ()
            .getMenuButton ()).click ();
        withMouse (HomePage.homePage ()
            .getLogout ()).click ();
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .verifyUrl ()
                .startsWith (URL);
        }
        ElementActions.onElement (LoginPage.loginPage ()
                .getUsername ())
            .verifyIsDisplayed ()
            .isTrue ();
    }

    private void verifyLoggedIn () {
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .verifyUrl ()
                .isEqualTo (format ("{0}/inventory.html", URL));
            WindowActions.onWindow ()
                .verifyTitle ()
                .isEqualTo ("Swag Labs");
        }
        ElementActions.onElement (HomePage.homePage ()
                .getMenuButton ())
            .verifyIsDisplayed ()
            .isTrue ();
        ElementActions.onElement (HomePage.homePage ()
                .getMenuButton ())
            .verifyIsEnabled ()
            .isTrue ();
    }

    private void verifyNavigateToSite () {
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .to (URL);
            NavigateActions.navigate ()
                .verifyUrl ()
                .startsWith (URL);
        }
    }

    private void verifyProductCartPage () {
        withMouse (HomePage.homePage ()
            .getShoppingCart ()).click ();
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .verifyUrl ()
                .isEqualTo (format ("{0}/cart.html", URL));
        }
        ElementActions.onElement (cartPage ().getCheckout ())
            .verifyIsDisplayed ()
            .isTrue ();
    }

    private void verifyProductDetailPage () {
        withMouse (HomePage.homePage ()
            .productItem ("Sauce Labs Backpack")).click ();
        if (this.platformType == PlatformType.WEB) {
            NavigateActions.navigate ()
                .verifyUrl ()
                .startsWith (format ("{0}/inventory-item.html?id=", URL));
        }
        ElementActions.onElement (ProductDetailsPage.productDetailsPage ()
                .getContainer ())
            .verifyIsDisplayed ()
            .isTrue ();
        if (this.platformType != PlatformType.WEB) {
            withFinger ().swipe (SwipeDirection.UP);
            withFinger ().swipe (SwipeDirection.DOWN);
            FingersActions.withFingers (ProductDetailsPage.productDetailsPage ()
                    .getImage ())
                .zoomIn ();
            FingersActions.withFingers (ProductDetailsPage.productDetailsPage ()
                    .getImage ())
                .zoomOut ();
        }
    }
}
