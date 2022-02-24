package com.github.wasiqb.boyka.testng.web.pages;

import static com.github.wasiqb.boyka.pages.Locator.createLocator;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.pages.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginBox    = createLocator ().web (id ("login_button_container"))
        .create ();
    private final Locator loginButton = createLocator ().web (id ("login-button"))
        .parent (this.loginBox)
        .create ();
    private final Locator password    = createLocator ().web (id ("password"))
        .parent (this.loginBox)
        .create ();
    private final Locator username    = createLocator ().web (id ("user-name"))
        .parent (this.loginBox)
        .create ();
}
