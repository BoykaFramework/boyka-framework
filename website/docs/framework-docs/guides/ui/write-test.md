---
title: ✅ Write Test for Web
sidebar_position: 4
---

Once the page object class is created with locators for all the platforms and the [application action class](/docs/guides/ui/page-action) is also created, we can now use it to interact with the page in our tests by calling the corresponding methods from the action class.

:::tip
Check out all the available static methods to interact with the page in your tests. Following are the available action classes:

## Driver actions

- [`AlertActions`](/api/actions/drivers/alert-actions): Contains all Alert related actions
- [`CookieActions`](/api/actions/drivers/cookie-actions): Contains all cookies related actions
- [`DriverActions`](/api/actions/drivers/driver-actions): Contains all other driver related actions
- [`FrameActions`](/api/actions/drivers/frame-actions): Contains all Frames related actions
- [`NavigateActions`](/api/actions/drivers/navigate-actions): Contains all navigate related actions
- [`WindowActions`](/api/actions/drivers/window-actions): Contains all windows related actions

## Element Actions

- [`ClickableActions`](/api/actions/elements/clickable-actions): Contains all clickable element related actions
- [`DropDownActions`](/api/actions/elements/drop-down-actions): Contains all drop down element related actions
- [`ElementActions`](/api/actions/elements/element-actions): Contains all common methods for element related actions
- [`FingerActions`](/api/actions/elements/finger-actions): Contains all methods for single finger on element / screen related actions
- [`TextBoxActions`](/api/actions/elements/textbox-actions): Contains all text box related actions methods
   :::

## Example

```java
package com.github.wasiqb.boyka.testng.ui.saucedemo;

import static com.github.wasiqb.boyka.actions.drivers.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.saucedemo.actions.SauceDemoActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceDemoTest {
  private SauceDemoActions sauceDemo;

  @AfterMethod (alwaysRun = true)
  public void afterMethod () {
    onWindow ().takeScreenshot ();
  }

  @BeforeClass (description = "Setup test class", alwaysRun = true)
  @Parameters ({ "platformType", "driverKey" })
  public void setupTestClass (final PlatformType platformType, final String driverKey) {
    createSession ("SauceDemoTest Persona", platformType, driverKey);
    this.sauceDemo = new SauceDemoActions ();
  }

  @AfterClass (description = "Tear down test class", alwaysRun = true)
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
