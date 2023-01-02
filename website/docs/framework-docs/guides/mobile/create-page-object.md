---
title: 📄 Create Page Object
sidebar_position: 2
---

Let's see how you can create page object for Android application.

## Example Page Object class

In the following example, I have used the [same page object](/docs/guides/web/create-page-object) which I had created for Web assuming the application under test has Android and Web version with same UI.

```java
import static io.appium.java_client.AppiumBy.accessibilityId;
import static org.openqa.selenium.By.id;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
    private static final LoginPage LOGIN_PAGE = new LoginPage ();

    public static LoginPage loginPage () {
        return LOGIN_PAGE;
    }

    private final Locator loginBox = Locator.buildLocator ()
        .web (id ("login_button_container"))
        // highlight-next-line
        .android (accessibilityId ("test-Login"))
        .name ("Login Box")
        .build ();
    private final Locator loginButton = Locator.buildLocator ()
        .web (id ("login-button"))
        // highlight-next-line
        .android (accessibilityId ("test-LOGIN"))
        .name ("Login Button")
        .parent (this.loginBox)
        .build ();
    private final Locator password = Locator.buildLocator ()
        .web (id ("password"))
        // highlight-next-line
        .android (accessibilityId ("test-Password"))
        .name ("Password")
        .parent (this.loginBox)
        .build ();
    private final Locator username = Locator.buildLocator ()
        .web (id ("user-name"))
        // highlight-next-line
        .android (accessibilityId ("test-Username"))
        .name ("User Name")
        .parent (this.loginBox)
        .build ();

    private LoginPage () {
        // Avoid explicit class initialization.
    }
}
```
