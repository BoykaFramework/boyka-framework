---
title: ✅ Write Test for Web
sidebar_position: 3
---

Once the page object class is created, we can use it to interact with the page in our tests by passing the locators to static action methods exposed by Boyka framework.

:::tip
Check out all the available static methods to interact with the page in our tests. Following are the available action methods:

- [`DriverActions`](/api/actions/driver-actions): Contains all driver related actions
- [`ElementActions`](/api/actions/element-actions): Contains all element related actions
- [`KeyboardActions`](/api/actions/keyboard-actions): Contains all keyboard related actions
- [`MouseActions`](/api/actions/mouse-actions): Contains all mouse related actions
- [`VerifyDriverActions`](/api/actions/verify-driver-actions): Contains all verification methods for driver related actions
- [`VerifyElementActions`](/api/actions/verify-element-actions): Contains all verification methods for element related actions
:::

## Example

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.LoginPage.loginPage;

import com.github.wasiqb.boyka.enums.ApplicationType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestWeb {
    private static final String URL = "https://www.saucedemo.com";

    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        createDriver (ApplicationType.WEB, "test_local_chrome");
    }

    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        closeDriver ();
    }

    @Test (description = "Test login functionality")
    public void testLogin () {
        navigateTo (URL);
        verifyBrowserUrl ().startsWith (URL);
        enterText (loginPage ().getUsername (), "standard_user");
        enterText (loginPage ().getPassword (), "secret_sauce");
        clickOn (loginPage ().getLoginButton ());
        verifyBrowserTitle ().isEqualTo ("Swag Labs");
        verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
        verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
    }
}
```
