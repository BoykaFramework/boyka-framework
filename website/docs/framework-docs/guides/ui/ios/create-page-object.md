---
title: 2️⃣ Create iOS Page Object
sidebar_position: 2
---

## Example Page Object class

In the following example, I have used the [same page object](/docs/guides/ui/android/create-page-object) which I had created for Web assuming the application under test has Android, iOS and Web version with same UI.

```java
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.id;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    private static final LoginPage LOGIN_PAGE = new LoginPage ();

    public static LoginPage loginPage () {
        return LOGIN_PAGE;
    }

    private final Locator loginButton = Locator.buildLocator ()
        .web (id ("login-button"))
        .android (accessibilityId ("test-LOGIN"))
        // highlight-next-line
        .ios (accessibilityId ("test-LOGIN"))
        .name ("Login Button")
        .build ();
    private final Locator password = Locator.buildLocator ()
        .web (id ("password"))
        .android (accessibilityId ("test-Password"))
        // highlight-next-line
        .ios (accessibilityId ("test-Password"))
        .name ("Password")
        .build ();
    private final Locator username = Locator.buildLocator ()
        .web (id ("user-name"))
        .android (accessibilityId ("test-Username"))
        // highlight-next-line
        .ios (accessibilityId ("test-Username"))
        .name ("User Name")
        .build ();

    private LoginPage () {
        // Avoid explicit class initialization.
    }
}
```
