---
title: DriverActions
sidebar_position: 1
---

## `currentWindowHandle`

This method returns the current window handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.currentWindowHandle;
. . .
System.out.println (currentWindowHandle ());
```

## `navigateTo`

This method is used to navigate to the given URL.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
. . .
navigateTo ("https://google.com");
```

## `switchToNewWindow`

This method is used to switch to new window of given type.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToNewWindow;
import org.openqa.selenium.WindowType;
. . .
switchToNewWindow (WindowType.TAB);
```

## `switchToWindow`

This method is used to switch to window of given handle.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.switchToWindow;
. . .
switchToWindow ("window-handle");
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

## `windowHandles`

This method will get the list of all open window handles.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.windowHandles;
. . .
System.out.println (windowHandles ());
```
