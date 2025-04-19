---
title: 4️⃣ Write common Test class
sidebar_position: 4
---

Once the page object class is created with locators for all the platforms and the [application action class](/docs/guides/ui/page-action) is also created, we can now use it to interact with the page in our tests by calling the corresponding methods from the action class.

:::tip
Check out all the available static methods to interact with the page in your tests. Following are the available action classes:

## Driver actions

- [`AlertActions`](/api/actions/drivers/alert-actions): Contains all Alert related actions
- [`ContextActions`](/api/actions/drivers/context-actions): Contains all Mobile context actions methods
- [`CookieActions`](/api/actions/drivers/cookie-actions): Contains all cookies related actions
- [`DriverActions`](/api/actions/drivers/driver-actions): Contains all other driver related actions
- [`FrameActions`](/api/actions/drivers/frame-actions): Contains all Frames related actions
- [`NavigateActions`](/api/actions/drivers/navigate-actions): Contains all navigate related actions
- [`WindowActions`](/api/actions/drivers/window-actions): Contains all windows related actions

## Element Actions

- [`ClickableActions`](/api/actions/elements/clickable-actions): Contains all clickable element related actions
- [`DropDownActions`](/api/actions/elements/drop-down-actions): Contains all drop down element related actions
- [`ElementActions`](/api/actions/elements/element-actions): Contains all common methods for element related actions
- [`FingerActions`](/api/actions/elements/finger-actions): Contains all methods for single finger actions on element / screen related
- [`FingersAction`](/api/actions/elements/fingers-actions): Contains all method for multi finger actions on element / screen
- [`TextBoxActions`](/api/actions/elements/textbox-actions): Contains all text box related actions methods

:::

## Example

```java
package io.github.boykaframework.testng.ui.saucedemo;

import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static com.google.common.truth.Truth.assertThat;

import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.testng.ui.saucedemo.actions.SauceDemoActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceDemoTest {
  private SauceDemoActions sauceDemo;

  @AfterMethod
  public void afterMethod (final ITestResult result) {
    if (!result.isSuccess ()) {
      onWindow ().takeScreenshot ();
    }
  }

  @BeforeClass (description = "Setup test class")
  @Parameters ({ "platformType", "driverKey" })
  public void setupTestClass (final PlatformType platformType, final String driverKey) {
    createSession ("SauceDemoTest Persona", platformType, driverKey);
    this.sauceDemo = new SauceDemoActions ();
  }

  @AfterClass (description = "Tear down test class")
  public void tearDownTestClass () {
    withDriver ().saveLogs ();
    clearSession ();
  }

  @Test (description = "Test login functionality")
  public void testLogin () {
    this.sauceDemo.verifyLogin ("standard_user", "secret_sauce");
  }
}
```
