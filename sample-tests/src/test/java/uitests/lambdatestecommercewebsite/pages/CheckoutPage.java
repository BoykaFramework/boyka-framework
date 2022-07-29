package uitests.lambdatestecommercewebsite.pages;

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

    private Locator getUnitPriceOfCameraLens = buildLocator ().web (
            By.cssSelector ("#checkout-total > tbody > tr:nth-child(1) > td.text-right"))
        .build ();

    private Locator paymentAddressForm = buildLocator ().web (By.id ("payment-address"))
        .build ();

    private Locator firstNameField = buildLocator ().web (By.id ("input-payment-firstname"))
        .parent (paymentAddressForm)
        .build ();

    private Locator lastNameField = buildLocator ().web (By.id ("input-payment-lastname"))
        .parent (paymentAddressForm)
        .build ();

    private Locator addressLineOneField = buildLocator ().web (By.id ("input-payment-address-1"))
        .parent (paymentAddressForm)
        .build ();

    private Locator cityField = buildLocator ().web (By.id ("input-payment-city"))
        .parent (paymentAddressForm)
        .build ();

    private Locator postCodeField = buildLocator ().web (By.id ("input-payment-postcode"))
        .parent (paymentAddressForm)
        .build ();

    private Locator countryField = buildLocator ().web (By.id ("input-payment-country"))
        .parent (paymentAddressForm)
        .build ();

    private Locator stateField = buildLocator ().web (By.id ("input-payment-zone"))
        .parent (paymentAddressForm)
        .build ();

    private Locator agreeTermsAndConditionsField = buildLocator ().web (By.id ("input-agree"))
        .build ();

    private Locator continueBtn = buildLocator ().web (By.id ("button#button-save"))
        .build ();

}
