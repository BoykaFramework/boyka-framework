package com.github.wasiqb.boyka.testng.ui.saucedemo.actions;

import static com.github.wasiqb.boyka.actions.drivers.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.enums.SwipeDirection.UP;
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
        withMouse (homePage ().getAddToCartButton ()).click ();

        onElement (homePage ().getProductPrice ()).verifyText ()
            .isEqualTo ("$29.99");
        onElement (homePage ().getShoppingCartCount ()).verifyText ()
            .isEqualTo ("1");

        verifyProductDetailPage ();
        verifyProductCartPage ();
    }

    public void verifyCheckoutStep1 () {
        withMouse (cartPage ().getCheckout ()).click ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/checkout-step-one.html", URL));
            onElement (checkoutPage ().getTitle ()).verifyText ()
                .ignoringCase ()
                .isEqualTo ("CHECKOUT: YOUR INFORMATION");
        }

        onTextBox (checkoutPage ().getFirstName ()).enterText ("Wasiq");
        onTextBox (checkoutPage ().getLastName ()).enterText ("Bhamla");
        onTextBox (checkoutPage ().getZipCode ()).enterText ("12345");

        withMouse (checkoutPage ().getContinueButton ()).click ();
    }

    public void verifyCheckoutStep2 () {
        if (this.platformType != WEB) {
            withFinger (checkoutPage ().getFinish ()).swipeTill (UP);
        }
        withMouse (checkoutPage ().getFinish ()).click ();

        if (this.platformType != WEB) {
            onElement (checkoutPage ().getCompleteHeader ()).verifyText ()
                .isEqualTo ("THANK YOU FOR YOU ORDER");
        } else {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/checkout-complete.html", URL));
            onElement (checkoutPage ().getTitle ()).verifyText ()
                .ignoringCase ()
                .isEqualTo ("CHECKOUT: COMPLETE!");
            onElement (checkoutPage ().getCompleteHeader ()).verifyText ()
                .ignoringCase ()
                .contains ("THANK YOU FOR YOUR ORDER");
        }

        onElement (checkoutPage ().getCompleteText ()).verifyText ()
            .isEqualTo ("Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    public void verifyLogin (final String userName, final String password) {
        verifyNavigateToSite ();
        onTextBox (loginPage ().getUsername ()).enterText (userName);
        onTextBox (loginPage ().getPassword ()).enterText (password);
        withMouse (loginPage ().getLoginButton ()).click ();
        verifyLoggedIn ();
    }

    public void verifySignOut () {
        withMouse (homePage ().getMenuButton ()).click ();
        withMouse (homePage ().getLogout ()).click ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .startsWith (URL);
        }
        onElement (loginPage ().getUsername ()).verifyIsDisplayed ()
            .isTrue ();
    }

    private void verifyLoggedIn () {
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/inventory.html", URL));
            onWindow ().verifyTitle ()
                .isEqualTo ("Swag Labs");
        }
        onElement (homePage ().getMenuButton ()).verifyIsDisplayed ()
            .isTrue ();
        onElement (homePage ().getMenuButton ()).verifyIsEnabled ()
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
        withMouse (homePage ().getShoppingCart ()).click ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .isEqualTo (format ("{0}/cart.html", URL));
        }
        onElement (cartPage ().getCheckout ()).verifyIsDisplayed ()
            .isTrue ();
    }

    private void verifyProductDetailPage () {
        withMouse (homePage ().productItem ("Sauce Labs Backpack")).click ();
        if (this.platformType == WEB) {
            navigate ().verifyUrl ()
                .startsWith (format ("{0}/inventory-item.html?id=", URL));
        }
        onElement (productDetailsPage ().getContainer ()).verifyIsDisplayed ()
            .isTrue ();
    }
}
