package com.github.wasiqb.boyka.testng.ui.saucedemo.actions;

import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
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
        clickOn (homePage ().getAddToCartButton ());

        verifyTextOf (homePage ().getProductPrice ()).isEqualTo ("$29.99");
        verifyTextOf (homePage ().getShoppingCartCount ()).isEqualTo ("1");

        verifyProductDetailPage ();
        verifyProductCartPage ();
    }

    public void verifyCheckoutStep1 () {
        clickOn (cartPage ().getCheckout ());
        if (this.platformType == WEB) {
            verifyBrowserUrl ().isEqualTo (format ("{0}/checkout-step-one.html", URL));
            verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: YOUR INFORMATION");
        }

        enterText (checkoutPage ().getFirstName (), "Wasiq");
        enterText (checkoutPage ().getLastName (), "Bhamla");
        enterText (checkoutPage ().getZipCode (), "12345");

        clickOn (checkoutPage ().getContinueButton ());
        verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: OVERVIEW");
    }

    public void verifyCheckoutStep2 () {
        clickOn (checkoutPage ().getFinish ());

        if (this.platformType != WEB) {
            verifyTextOf (checkoutPage ().getCompleteHeader ()).isEqualTo ("THANK YOU FOR YOU ORDER");
        } else {
            verifyBrowserUrl ().isEqualTo (format ("{0}/checkout-complete.html", URL));
            verifyTextOf (checkoutPage ().getTitle ()).isEqualTo ("CHECKOUT: COMPLETE!");
            verifyTextOf (checkoutPage ().getCompleteHeader ()).isEqualTo ("THANK YOU FOR YOUR ORDER");
        }

        verifyTextOf (checkoutPage ().getCompleteText ()).isEqualTo (
            "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
    }

    public void verifyLogin (final String userName, final String password) {
        verifyNavigateToSite ();
        enterText (loginPage ().getUsername (), userName);
        enterText (loginPage ().getPassword (), password);
        clickOn (loginPage ().getLoginButton ());
        verifyLoggedIn ();
    }

    public void verifySignOut () {
        clickOn (homePage ().getMenuButton ());
        clickOn (homePage ().getLogout ());
        if (this.platformType == WEB) {
            verifyBrowserUrl ().startsWith (URL);
        }
        verifyElementDisplayed (loginPage ().getUsername ()).isTrue ();
    }

    private void verifyLoggedIn () {
        if (this.platformType == WEB) {
            verifyBrowserUrl ().isEqualTo (format ("{0}/inventory.html", URL));
            verifyBrowserTitle ().isEqualTo ("Swag Labs");
        }
        verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
        verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
    }

    private void verifyNavigateToSite () {
        if (this.platformType == WEB) {
            navigate ().to (URL);
            verifyBrowserUrl ().startsWith (URL);
        }
    }

    private void verifyProductCartPage () {
        clickOn (homePage ().getShoppingCart ());
        if (this.platformType == WEB) {
            verifyBrowserUrl ().isEqualTo (format ("{0}/cart.html", URL));
        }
        verifyElementDisplayed (cartPage ().getCheckout ()).isTrue ();
    }

    private void verifyProductDetailPage () {
        clickOn (homePage ().productItem ("Sauce Labs Backpack"));
        if (this.platformType == WEB) {
            verifyBrowserUrl ().startsWith (format ("{0}/inventory-item.html?id=", URL));
        }
        verifyElementDisplayed (productDetailsPage ().getContainer ()).isTrue ();
    }
}
