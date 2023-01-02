---
title: DriverActions
sidebar_position: 1
---

## `acceptAlert` {#accept-alert}

This method returns the alert text and accepts the alert.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.acceptAlert;
. . .
String message = acceptAlert ();
```

## `acceptAlert (text)` {#accept-alert-text}

This method returns the alert text, enter text in prompt and accepts the prompt alert.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.acceptAlert;
. . .
String message = acceptAlert ("Hello World");
```

## `closeWindow` {#close-window}

This method will close the open browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.closeWindow;
. . .
closeWindow ();
```

## `cookie (name)` {#cookie-name}

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

## `currentWindowHandle` {#current-window-handle}

This method returns the current window handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.currentWindowHandle;
. . .
String handle = currentWindowHandle ();
```

## `deleteAllCookies` {#delete-all-cookies}

This method will delete all the cookies from the browser.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.deleteAllCookies;
. . .
deleteAllCookies ();
```

## `deleteCookie (name)` {#delete-cookie-name}

This method will delete the cookie from the browser based on it's name.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.deleteCookie;
. . .
deleteCookie ("cookie-name");
```

## `dismissAlert` {#dismiss-alert}

This method will dismiss the alert and returns the alert text.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.dismissAlert;
. . .
String message = dismissAlert ();
```

## `executeScript (script, args[])` {#execute-script}

This method will execute the JS script and returns the result.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.executeScript;
. . .
String output = executeScript ("alert('Hello World');");
```

## `fullScreen` {#full-screen}

This method will on-demand do full screen on the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.fullScreen;
. . .
fullScreen ();
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

## `navigate`

This method is used to perform navigation related actions.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
navigate ().to ("https://google.com");
```

## `pause` {#pause}

This method is used to navigate to the given URL.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.pause;
import static java.time.Duration.ofMillis;
. . .
pause (ofMillis (100));
```

## `saveLogs`

This method will save all the logs captured by the Driver.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.saveLogs;
. . .
saveLogs ();
```

## `swipe`

This method will return Swipe class instance which can be used to perform swipe gesture

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
swipe ().up ();
```

## `switchToFrame (name)` {#switch-to-frame-name}

This method will switch to the given frame by it's name.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToFrame;
. . .
switchToFrame ("frame-name");
```

:::tip
In order to come out of this frame, use [`switchToParentFrame`](#switchtoparentframe) method.
:::

## `switchToMainWindow` {#switch-to-main-window}

This method will switch to the first window after you close any of the other opened windows.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToMainWindow;
. . .
switchToMainWindow ();
```

## `switchToNewWindow` {#switch-to-new-window}

This method is used to switch to new window of given type.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToNewWindow;
import org.openqa.selenium.WindowType;
. . .
switchToNewWindow (WindowType.TAB);
```

## `switchToParentFrame` {#switch-to-parent-frame}

This method will switch to the parent frame of the current frame. This method is used to come out of any iframe.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToParentFrame;
. . .
switchToParentFrame ();
```

## `switchToWindow (handle)` {#switch-to-window-handle}

This method is used to switch to window of given handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToWindow;
. . .
switchToWindow ("window-handle");
```

## `takeScreenshot` {#take-screenshot}

This method will take the screenshot of the current page and save it at the path configured in `boyka-config.json`.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
. . .
takeScreenshot ();
```

## `takeScreenshot (path)` {#take-screenshot-path}

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

## `viewportSize` {#viewport-size}

This method will the size dimension of the screen viewport for Mobile screen or Browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.viewportSize;
import org.openqa.selenium.Dimension;
. . .
Dimension size = viewportSize ();
```

## `waitUntil` {#wait-until}

This method will wait for any given condition to be true. It takes in Selenium WebDrivers `ExpectedCondition<Boolean>` object as parameter.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.waitUntil;
import org.openqa.selenium.support.ui.ExpectedConditions;
. . .
waitUntil (ExpectedConditions.urlMatches (URL));
```

## `windowHandles` {#window-handles}

This method will get the list of all open window handles.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.windowHandles;
. . .
List<String> handles = windowHandles ();
```
