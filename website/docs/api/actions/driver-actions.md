---
title: DriverActions
sidebar_position: 1
---

## `acceptAlert`

This method returns the alert text and accepts the alert.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.acceptAlert;
. . .
System.out.println (acceptAlert ());
```

## `acceptAlert(string)`

This method returns the alert text, enter text in prompt and accepts the prompt alert.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.acceptAlert;
. . .
System.out.println (acceptAlert ("Hello World"));
```

## `closeWindow`

This method will close the open browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.closeWindow;
. . .
closeWindow ();
```

## `cookie(string)`

This method will get the cookie from the browser based on it's name.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.cookie;
import org.openqa.selenium.Cookie;
. . .
Cookie c = cookie ("cookie-name");
```

## `cookies`

This method will get all the cookie from the browser and return all the cookie names as list.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.cookies;
. . .
List<String> cookieNames = cookies ();
```

## `currentWindowHandle`

This method returns the current window handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.currentWindowHandle;
. . .
System.out.println (currentWindowHandle ());
```

## `deleteAllCookies`

This method will delete all the cookies from the browser.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.deleteAllCookies;
. . .
deleteAllCookies ();
```

## `deleteCookie(string)`

This method will delete the cookie from the browser based on it's name.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.deleteCookie;
. . .
deleteCookie ("cookie-name");
```

## `dismissAlert`

This method will dismiss the alert and returns the alert text.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.dismissAlert;
. . .
String message = dismissAlert ();
```

## `executeScript(string, object[])`

This method will execute the JS script and returns the result.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.executeScript;
. . .
String output = executeScript ("alert('Hello World');");
```

## `fullScreen`

This method will on-demand do full screen on the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.fullScreen;
. . .
fullScreen ();
```

## `goBack`

This method will go back to the previous page.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.goBack;
. . .
goBack ();
```

## `goForward`

This method will go forward to the next page.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.goForward;
. . .
goForward ();
```

## `maximize`

This method will maximize the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.maximize;
. . .
maximize ();
```

## `minimize`

This method will minimize the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.minimize;
. . .
minimize ();
```

## `navigateTo`

This method is used to navigate to the given URL.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
. . .
navigateTo ("https://google.com");
```

## `refresh`

This method will refresh the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.refresh;
. . .
refresh ();
```

## `switchToFrame(string)`

This method will switch to the given frame by it's name.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToFrame;
. . .
switchToFrame ("frame-name");
```

:::tip
In order to come out of this frame, use [`switchToParentFrame`](#switchtoparentframe) method.
:::

## `switchToMainWindow`

This method will switch to the first window after you close any of the other opened windows.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToMainWindow;
. . .
switchToMainWindow ();
```

## `switchToNewWindow`

This method is used to switch to new window of given type.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToNewWindow;
import org.openqa.selenium.WindowType;
. . .
switchToNewWindow (WindowType.TAB);
```

## `switchToParentFrame`

This method will switch to the parent frame of the current frame. This method is used to come out of any iframe.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToParentFrame;
. . .
switchToParentFrame ();
```

## `switchToWindow(string)`

This method is used to switch to window of given handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToWindow;
. . .
switchToWindow ("window-handle");
```

## `takeScreenshot`

This method will take the screenshot of the current page and save it at the path configured in `boyka-config.json`.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
. . .
takeScreenshot ();
```

## `takeScreenshot(string)`

This method will take the screenshot of the current page and save it at the path mentioned in the parameter.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
. . .
takeScreenshot ("path/to/screenshot.png");
```

## `title`

This method will get the title of the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.title;
. . .
System.out.println (title ());
```

## `url`

This method will get the URL of the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.url;
. . .
System.out.println (url ());
```

## `waitUntil`

This method will wait for any given condition to be true. It takes in Selenium WebDrivers `ExpectedCondition<Boolean>` object as parameter.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.waitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
. . .
waitUntil (ExpectedConditions.urlMatches (URL));
```

## `windowHandles`

This method will get the list of all open window handles.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.windowHandles;
. . .
System.out.println (windowHandles ());
```
