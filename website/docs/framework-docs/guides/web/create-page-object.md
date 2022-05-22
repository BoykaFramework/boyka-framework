---
title: 📄 Create Page Object
sidebar_position: 2
---

In Boyka, we have decoupled the page object from the `WebDriver` class. This is done by creating a Page Object class and defining locators using `Locator` builder class.

## Example Page Object class

In the following example, I've used Lombok `@Getter` to auto-generate getters for all the declared locators.

```java
import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginBox = Locator.buildLocator ()
        .web (By.id ("login_button_container"))
        .build ();

    private final Locator loginButton = Locator.buildLocator ()
        .web (By.id ("login-button"))
        .parent (this.loginBox)
        .build ();
    private final Locator password = Locator.buildLocator ()
        .web (By.id ("password"))
        .parent (this.loginBox)
        .build ();
    private final Locator username = Locator.buildLocator ()
        .web (By.id ("user-name"))
        .parent (this.loginBox)
        .build ();

    private LoginPage () {
        // Avoid explicit class initialization.
    }
}
```
