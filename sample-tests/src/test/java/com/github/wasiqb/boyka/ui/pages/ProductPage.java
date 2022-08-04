package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
@Getter
public class ProductPage {

    public static ProductPage productPage () {
        return new ProductPage ();
    }

    private final Locator addToCartBtn      = Locator.buildLocator ()
        .web (By.cssSelector ("div.product-action > button.btn.btn-cart.cart-29"))
        .build ();
    private final Locator checkoutBtn       = Locator.buildLocator ()
        .web (By.cssSelector ("div.form-row > div:nth-child(2) > a"))
        .build ();
    private final Locator notificationPopUp = Locator.buildLocator ()
        .web (By.id ("notification-box-top"))
        .build ();

    private final Locator palmTreoCameraLens = Locator.buildLocator ()
        .web (By.cssSelector ("#entry_212408 > div > div:nth-child(2)"))
        .build ();
    private final Locator successMessage     = Locator.buildLocator ()
        .web (By.tagName ("p"))
        .build ();

}

