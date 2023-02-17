package com.github.wasiqb.boyka.testng.ui.saucedemo.actions;

import static com.github.wasiqb.boyka.actions.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.TextBoxActions.onTextBox;
import static com.github.wasiqb.boyka.actions.WindowActions.onWindow;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.CartPage.cartPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.CheckoutPage.checkoutPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.LoginPage.loginPage;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.ProductDetailsPage.productDetailsPage;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.enums.PlatformType;

public class SauceDemoActions {
    private static final String       URL = "https://www.saucedemo.com";
    private final        PlatformType platformType;

    public SauceDemoActions () {
        this.platformType = getSession ().getPlatformType ();
    }

    public void verifyAddToCart () {
        withMouse (homePage ().getAddToCartButton ()).clickOn ();

        onElement (homePage ().getProductPrice ()).verifyTextOf ()
            .isEqualTo ("$29.99");
        onElement (homePage ().getShoppingCartCount ()).verifyTextOf ()
            .isEqualTo ("1");

        verifyProductDetailPage ();
        verifyProductCartPage ();
    }

    public void verifyCheckoutStep1 () {
        withMouse (cartPage ().getCheckout ()).clickOn ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/checkout-step-one.html", URL));
            onElement (checkoutPage ().getTitle ()).verifyTextOf ()
                .isEqualTo ("CHECKOUT: YOUR INFORMATION");
        }

        onTextBox (checkoutPage ().getFirstName ()).enterText ("Wasiq");
        onTextBox (checkoutPage ().getLastName ()).enterText ("Bhamla");
        onTextBox (checkoutPage ().getZipCode ()).enterText ("12345");

        withMouse (checkoutPage ().getContinueButton ()).clickOn ();
        onElement (checkoutPage ().getTitle ()).verifyTextOf ()
            .isEqualTo ("CHECKOUT: OVERVIEW");
    }

    public void verifyCheckoutStep2 () {
        withMouse (checkoutPage ().getFinish ()).clickOn ();

        if (this.platformType != WEB) {
            onElement (checkoutPage ().getCompleteHeader ()).verifyTextOf ()
                .isEqualTo ("THANK YOU FOR YOU ORDER");
        } else {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/checkout-complete.html", URL));
            onElement (checkoutPage ().getTitle ()).verifyTextOf ()
                .isEqualTo ("CHECKOUT: COMPLETE!");
            onElement (checkoutPage ().getCompleteHeader ()).verifyTextOf ()
                .isEqualTo ("THANK YOU FOR YOUR ORDER");
        }

        onElement (checkoutPage ().getCompleteText ()).verifyTextOf ()
            .isEqualTo ("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    public void verifyLogin (final String userName, final String password) {
        verifyNavigateToSite ();
        onTextBox (loginPage ().getUsername ()).enterText (userName);
        onTextBox (loginPage ().getPassword ()).enterText (password);
        withMouse (loginPage ().getLoginButton ()).clickOn ();
        verifyLoggedIn ();
    }

    public void verifySignOut () {
        withMouse (homePage ().getMenuButton ()).clickOn ();
        withMouse (homePage ().getLogout ()).clickOn ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .startsWith (URL);
        }
        onElement (loginPage ().getUsername ()).verifyElementDisplayed ()
            .isTrue ();
    }

    private void verifyLoggedIn () {
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/inventory.html", URL));
            onWindow ().verifyTitle ()
                .isEqualTo ("Swag Labs");
        }
        onElement (homePage ().getMenuButton ()).verifyElementDisplayed ()
            .isTrue ();
        onElement (homePage ().getMenuButton ()).verifyElementEnabled ()
            .isTrue ();
    }

    private void verifyNavigateToSite () {
        if (this.platformType == WEB) {
            navigate ().to (URL);
            navigate ().verifyUrl ()
                .startsWith (URL);
        }
    }

    private void verifyProductCartPage () {
        withMouse (homePage ().getShoppingCart ()).clickOn ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/cart.html", URL));
        }
        onElement (cartPage ().getCheckout ()).verifyElementDisplayed ()
            .isTrue ();
    }

    private void verifyProductDetailPage () {
        withMouse (homePage ().productItem ("Sauce Labs Backpack")).clickOn ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .startsWith (format ("{0}/inventory-item.html?id=", URL));
        }
        onElement (productDetailsPage ().getContainer ()).verifyElementDisplayed ()
            .isTrue ();
    }
}
