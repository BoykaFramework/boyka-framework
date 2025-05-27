---
title: 3️⃣ Create Page Action class
sidebar_position: 5
---

Since we have a common page object for all the three platforms (i.e.: Web. Android and iOS), we will create an Action class for the application where we will expose different methods which will take care of each page specific user flow.

Let's check out the following action class example below for our `LoginPage` class we created:

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.actions.elements.FingerActions.withFinger;
import static io.github.boykaframework.actions.elements.TextBoxActions.onTextBox;
import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.testng.ui.saucedemo.pages.LoginPage.loginPage;
import static java.text.MessageFormat.format;

import io.github.boykaframework.enums.PlatformType;

public class SauceDemoActions {
  private static final String URL = "https://www.saucedemo.com";

  private final PlatformType platformType;

  public SauceDemoActions () {
    this.platformType = getSession ().getPlatformType ();
  }

  public void verifyLogin (final String userName, final String password) {
    verifyNavigateToSite ();
    onTextBox (loginPage ().getUsername ()).enterText (userName);
    onTextBox (loginPage ().getPassword ()).enterText (password);
    withMouse (loginPage ().getLoginButton ()).click ();
    verifyLoggedIn ();
  }

  private void verifyNavigateToSite () {
    if (this.platformType == WEB) {
      navigate ().to (URL);
      navigate ().verifyUrl ()
        .startsWith (URL);
    }
  }

  private void verifyLoggedIn () {
    if (this.platformType == WEB) {
      navigate ().verifyUrl ()
        .isEqualTo (format ("{0}/inventory.html", URL));
      onWindow ().verifyTitle ()
        .isEqualTo ("Swag Labs");
    }
    onElement (homePage ().getMenuButton ()).verifyIsDisplayed ()
      .isTrue ();
    onElement (homePage ().getMenuButton ()).verifyIsEnabled ()
      .isTrue ();
  }
}
```

Notice how we are handling `Web` platform by using an `if` condition because the Web application user flow differs with Mobile app flow.

:::tip
Here we are using `getSession ().getPlatformType ()` to get the platform name of the application running in the current session.
:::
