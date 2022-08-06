package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Confirm order page objects
 * @author Faisal Khatri
 * @since 30/07/2022
 */
@Getter
public class ConfirmOrderPage {

    public static ConfirmOrderPage confirmOrderPage () {
        return new ConfirmOrderPage ();
    }

    private final Locator confirmOrderBtn = Locator.buildLocator ()
        .web (By.id ("button-confirm"))
        .build ();

    private final Locator pageHeader = Locator.buildLocator ()
        .web (By.tagName ("h1"))
        .build ();

    private final Locator productName = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.table-responsive > table > tbody > tr > td:nth-child(1)"))
        .build ();

    private final Locator shippingAddress = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.row > div:nth-child(2) > div > div"))
        .build ();

    private final Locator unitPrice = Locator.buildLocator ()
        .web (By.cssSelector ("#content > div.table-responsive > table > tbody > tr > td:nth-child(4)"))
        .build ();

}
