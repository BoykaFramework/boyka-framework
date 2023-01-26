package com.github.wasiqb.boyka.testng.ui.mydemo.actions;

import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.KeyboardActions.hideKeyboard;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.enums.PlatformType.ANDROID;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.CartPage.cartPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.CheckoutPage.checkoutPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.LoginPage.loginPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.OrderSuccessPage.orderSuccessPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.PaymentPage.paymentPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.ProductDetailsPage.productDetailsPage;
import static com.github.wasiqb.boyka.testng.ui.mydemo.pages.ReviewOrderPage.reviewOrderPage;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.github.wasiqb.boyka.testng.ui.mydemo.pages.ProductDetailsPage;
import lombok.SneakyThrows;
import net.datafaker.Faker;

public class SauceDemoActions {
    private ProductDetailsPage.Color color;
    private String                   fullName;
    private int                      items;
    private double                   price;
    private double                   totalAmount;

    public void verifyAddToCart (final String productName, final int quantity, final ProductDetailsPage.Color color) {
        clickOn (homePage ().productItem (productName));
        verifyElementDisplayed (productDetailsPage ().getAddToCart ()).isTrue ();

        this.color = color;
        this.items = quantity;
        this.price = getPrice (textOf (productDetailsPage ().getPrice ()));
        this.totalAmount = this.price * quantity;

        clickOn (productDetailsPage ().getColor (color));
        swipe ().up ();
        for (int i = 1; i < quantity; i++) {
            clickOn (productDetailsPage ().getAddQuantity ());
        }
        clickOn (productDetailsPage ().getAddToCart ());
        if (getSession ().getPlatformType () == ANDROID) {
            verifyTextOf (homePage ().getShoppingCartCount ()).isEqualTo (Integer.toString (quantity));
        } else {
            verifyTextOf (homePage ().getShoppingCart ()).isEqualTo (Integer.toString (quantity));
        }
    }

    public void verifyCartPage () {
        clickOn (homePage ().getShoppingCart ());
        verifyTextOf (cartPage ().getProductPrice ()).isEqualTo (getPrice (this.price));
        verifyTextOf (cartPage ().getTotalPrice ()).isEqualTo (getPrice (this.totalAmount));
        verifyTextOf (cartPage ().getTotalItems ()).contains (Integer.toString (this.items));
        verifyElementDisplayed (cartPage ().getColor (this.color)).isTrue ();
        clickOn (cartPage ().getCheckout ());
    }

    public void verifyCheckout () {
        final var faker = new Faker ();
        this.fullName = faker.name ()
            .fullName ();

        verifyElementDisplayed (checkoutPage ().getPayment ()).isTrue ();
        enterText (checkoutPage ().getFullName (), this.fullName);
        enterText (checkoutPage ().getAddress1 (), faker.address ()
            .fullAddress ());
        enterText (checkoutPage ().getCity (), faker.address ()
            .city ());
        enterText (checkoutPage ().getState (), faker.address ()
            .state ());
        swipe ().till (checkoutPage ().getZipCode ());
        enterText (checkoutPage ().getZipCode (), faker.address ()
            .zipCode ());
        enterText (checkoutPage ().getCountry (), faker.address ()
            .country ());
        hideKeyboard ();
        clickOn (checkoutPage ().getPayment ());
    }

    public void verifyLogin (final String userName, final String password) {
        verifyElementDisplayed (loginPage ().getLoginButton ()).isTrue ();
        enterText (loginPage ().getUsername (), userName);
        enterText (loginPage ().getPassword (), password);
        hideKeyboard ();
        clickOn (loginPage ().getLoginButton ());
    }

    public void verifyOrderReview () {
        verifyElementDisplayed (reviewOrderPage ().getPlaceOrder ()).isTrue ();
        clickOn (reviewOrderPage ().getPlaceOrder ());

        verifyTextOf (orderSuccessPage ().getMessageMatching ("Checkout Complete")).isEqualTo ("Checkout Complete");
        verifyTextOf (orderSuccessPage ().getMessageMatching ("Thank you")).isEqualTo ("Thank you for your order");
        verifyTextOf (orderSuccessPage ().getMessageMatching ("Your new swag")).isEqualTo (
            "Your new swag is on its way");
        verifyTextOf (orderSuccessPage ().getMessageMatching ("Your order has been dispatched")).isEqualTo (
            " Your order has been dispatched and will arrive as fast as the pony gallops!");
        clickOn (orderSuccessPage ().getContinueShopping ());
    }

    public void verifyPayment () {
        final var faker = new Faker ();
        verifyElementDisplayed (paymentPage ().getReviewOrder ()).isTrue ();
        enterText (paymentPage ().getFullName (), this.fullName);
        enterText (paymentPage ().getCardNumber (), faker.finance ()
            .creditCard ());
        enterText (paymentPage ().getExpiryDate (), getExpiry ());
        enterText (paymentPage ().getSecurityCode (), faker.number ()
            .digits (3));
        clickOn (paymentPage ().getReviewOrder ());
    }

    private String getExpiry () {
        final var format = new SimpleDateFormat ("MM/yy");
        final var calendar = Calendar.getInstance ();
        calendar.add (Calendar.YEAR, 1);
        return format.format (calendar.getTime ());
    }

    private String getPrice (final double amount) {
        final var format = NumberFormat.getCurrencyInstance (Locale.US);
        return format.format (amount);
    }

    @SneakyThrows
    private double getPrice (final String amount) {
        final var format = NumberFormat.getCurrencyInstance (Locale.US);
        return format.parse (amount)
            .doubleValue ();
    }
}
