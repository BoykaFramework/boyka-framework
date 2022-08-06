package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Order success page objects
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
@Getter
public class OrderSuccessPage {
    public static OrderSuccessPage orderSuccessPage () {
        return new OrderSuccessPage ();
    }

    private final Locator continueBtn = Locator.buildLocator ()
        .web ((By.cssSelector ("#content > div > a")))
        .build ();

    private final Locator successMessage = Locator.buildLocator ()
        .web (By.tagName ("h1"))
        .build ();
}
