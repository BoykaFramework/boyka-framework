package com.github.wasiqb.boyka.uitests.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 31/07/2022
 */
@Getter
public class HomePage {

    public static HomePage homePage () {
        return new HomePage ();
    }

    private final Locator loginLink         = Locator.buildLocator ()
        .web (By.linkText ("Login"))
        .build ();
    private final Locator openMyAccountMenu = Locator.buildLocator ()
        .web (By.linkText ("My account"))
        .build ();
    private final Locator registerLink      = Locator.buildLocator ()
        .web (By.linkText ("Register"))
        .build ();
    private final Locator shopByCategory    = Locator.buildLocator ()
        .web (By.linkText ("Shop by Category"))
        .build ();

    public Locator selectCategory (final String linkName) {
        return Locator.buildLocator ()
            .web (By.linkText (linkName))
            .build ();
    }

}
