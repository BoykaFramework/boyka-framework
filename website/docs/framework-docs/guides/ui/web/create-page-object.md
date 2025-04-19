---
title: 2️⃣ Create Web Page Object
sidebar_position: 2
---

In Boyka, we have decoupled the page object from the `WebDriver` class. This is done by creating a Page Object class and defining locators using `Locator` builder class.

## Example Page Object class

In the following example, I've used Lombok `@Getter` to auto-generate getters for all the declared locators.

```java
import io.github.boykaframework.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    public static LoginPage loginPage () {
        return new LoginPage ();
    }

    private final Locator loginButton = Locator.buildLocator ()
        .web (id ("login-button"))
        .name ("Login Button")
        .build ();
    private final Locator password = Locator.buildLocator ()
        .web (id ("password"))
        .name ("Password")
        .build ();
    private final Locator username = Locator.buildLocator ()
        .web (id ("user-name"))
        .name ("User Name")
        .build ();

    private LoginPage () {
        // Avoid explicit class initialization.
    }
}
```
