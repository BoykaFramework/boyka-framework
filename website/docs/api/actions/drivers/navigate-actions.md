---
title: NavigateActions
sidebar_position: 6
---

## Static methods

### `navigate`

This will return `INavigateActions` which will expose different methods to handle navigate related actions.

```java
INavigateActions navigateActions = NavigateActions.navigate ();
```

## Instance methods

### `back`

This method will go back to the previous page.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().back ();
```

### `forward`

This method will go forward to the next page.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().forward ();
```

### `refresh`

This method will refresh the browser window.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().refresh ();
```

### `to`

This method will navigate you to the URL mentioned.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().to ("https://google.com");
```

### `toBaseUrl`

This method will navigate to the base URL mentioned in the config file.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().toBaseUrl ();
```

### `getUrl`

This method will get the URL of the browser window.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
System.out.println (navigate ().getUrl ());
```

### `verifyUrl`

This method is used to verify the browser url.

```java
import static io.github.boykaframework.actions.drivers.NavigateActions.navigate;
. . .
navigate ().verifyUrl ()
    .isEqualTo ("https://www.swaglabs.com/");
```
