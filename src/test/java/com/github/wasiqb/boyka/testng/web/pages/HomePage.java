package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.pages.Locator.createLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.pages.Locator;
import lombok.Getter;

@Getter
public class HomePage {
    public static HomePage homePage () {
        return new HomePage ();
    }

    private final Locator menuButton = createLocator ().web (id ("react-burger-menu-btn"))
        .create ();
}
