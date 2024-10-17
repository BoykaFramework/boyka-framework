---
title: WindowActions
sidebar_position: 7
---

## Static methods

### `onWindow`

This will return `IWindowActions` which will expose different methods to handle windows related actions.

```java
IWindowActions windowActions = WindowActions.onWindow ();
```

## Instance methods

### `close`

This method will close the open browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().close ();
```

### `currentHandle` {#current-handle}

This method returns the current window handle.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
String handle = onWindow ().currentHandle ();
```

### `fullScreen` {#full-screen}

This method will on-demand do full screen on the browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().fullScreen ();
```

### `getScreenshot` {#get-screenshot}

This method will get the screenshot of the current page and return its Base64 encoded string.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
System.out.println (onWindow ().getScreenshot ());
```

### `maximize`

This method will maximize the browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().maximize ();
```

### `minimize`

This method will minimize the browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().minimize ();
```

### `switchToDefault` {#switch-to-default}

This method will switch to the first window after you close any of the other opened windows.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().switchToDefault ();
```

### `switchToNew` {#switch-to-new}

This method is used to switch to new window of given type.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import org.openqa.selenium.WindowType;
. . .
onWindow ().switchToNew (WindowType.TAB);
```

### `switchTo (handle)` {#switch-to}

This method is used to switch to window of given handle.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().switchTo ("window-handle");
```

### `takeScreenshot` {#take-screenshot}

This method will take the screenshot of the current page and save it at the path configured in `boyka-config.json`.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().takeScreenshot ();
```

### `takeScreenshot (path)` {#take-screenshot-path}

This method will take the screenshot of the current page and save it at the path mentioned in the parameter.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().takeScreenshot ("path/to/screenshot.png");
```

### `title`

This method will get the title of the browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
System.out.println (onWindow ().getTitle ());
```

### `viewportSize` {#viewport-size}

This method will the size dimension of the screen viewport for Mobile screen or Browser window.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import org.openqa.selenium.Dimension;
. . .
Dimension size = onWindow ().viewportSize ();
```

### `handles`

This method will get the list of all open window handles.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
List<String> handles = onWindow ().handles ();
```

### `verifyBrowserTitle` {#verify-browser-title}

This method is used to verify the browser title.

```java
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
. . .
onWindow ().verifyTitle (title).isEqualTo ("Swag Labs");
```
