---
title: NavigateActions
sidebar_position: 6
---

## `back`

This method will go back to the previous page.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
navigate ().back ();
```

## `forward`

This method will go forward to the next page.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
navigate ().forward ();
```

## `refresh`

This method will refresh the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
navigate ().refresh ();
```

## `to`

This method will navigate you to the URL mentioned.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
navigate ().to ("https://google.com");
```

## `url`

This method will get the URL of the browser window.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
. . .
System.out.println (navigate ().url ());
```
