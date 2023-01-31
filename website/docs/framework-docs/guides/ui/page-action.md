---
title: 🏗️ Create Page Action class
sidebar_position: 3
---

Since we have a common page object for all the three platforms (i.e.: Web. Android and iOS), we will create an Action class for the application where we will expose different methods which will take care of each page specific user flow.

Let's check out the following action class example below for our `LoginPage` class we created:

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.LoginPage.loginPage;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.enums.PlatformType;

public class SauceDemoActions {
  private static final String URL = "https://www.saucedemo.com";

  private final PlatformType platformType;

  public SauceDemoActions () {
    this.platformType = getSession ().getPlatformType ();
  }

  public void verifyLogin (final String userName, final String password) {
    verifyNavigateToSite ();
    enterText (loginPage ().getUsername (), userName);
    enterText (loginPage ().getPassword (), password);
    clickOn (loginPage ().getLoginButton ());
    verifyLoggedIn ();
  }

  private void verifyNavigateToSite () {
    if (this.platformType == WEB) {
      navigate ().to (URL);
      verifyBrowserUrl ().startsWith (URL);
    }
  }

  private void verifyLoggedIn () {
    if (this.platformType == WEB) {
      verifyBrowserUrl ().isEqualTo (format ("{0}/inventory.html", URL));
      verifyBrowserTitle ().isEqualTo ("Swag Labs");
    }
    verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
    verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
  }
}
```

Notice how we are handling `Web` platform by using an `if` condition because the Web application user flow differs with Mobile app flow.

:::tip
Here we are using `getSession ().getPlatformType ()` to get the platform name of the application running in the current session.
:::
