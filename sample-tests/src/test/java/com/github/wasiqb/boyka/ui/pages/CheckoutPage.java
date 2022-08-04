package com.github.wasiqb.boyka.ui.pages;

import static com.github.wasiqb.boyka.builders.Locator.buildLocator;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 29/07/2022
 */
@Getter
public class CheckoutPage {

    public static CheckoutPage checkoutPage () {
        return new CheckoutPage ();
    }

    private final Locator agreeTermsAndConditionsField = buildLocator ().web (By.id ("input-agree"))
        .build ();
    private final Locator continueBtn                  = buildLocator ().web (By.id ("button#button-save"))
        .build ();
    private final Locator getUnitPriceOfCameraLens     = buildLocator ().web (
            By.cssSelector ("#checkout-total > tbody > tr:nth-child(1) > td.text-right"))
        .build ();
    private final Locator paymentAddressForm           = buildLocator ().web (By.id ("payment-address"))
        .build ();
    private final Locator firstNameField               = buildLocator ().web (By.id ("input-payment-firstname"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator lastNameField                = buildLocator ().web (By.id ("input-payment-lastname"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator addressLineOneField          = buildLocator ().web (By.id ("input-payment-address-1"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator cityField                    = buildLocator ().web (By.id ("input-payment-city"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator countryField                 = buildLocator ().web (By.id ("input-payment-country"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator postCodeField                = buildLocator ().web (By.id ("input-payment-postcode"))
        .parent (this.paymentAddressForm)
        .build ();
    private final Locator stateField                   = buildLocator ().web (By.id ("input-payment-zone"))
        .parent (this.paymentAddressForm)
        .build ();

}
