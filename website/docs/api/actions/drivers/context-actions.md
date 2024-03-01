---
title: ContextActions
sidebar_position: 2
---

## Static methods

### `withContext` {#with-context}

This will return `IContextActions` which will expose different methods to handle different contexts.

```java
IContextActions contextActions = ContextActions.withContext ();
```

:::danger Important!!
In order to use these contexts related methods, your app must be set as `HYBRID` in your `boyka-config.json` file.

## Example

```json
{
  "ui": {
    "timeout": {
      // ...
    },
    "logging": {
      // ...
    },
    "screenshot": {
      // ...
    },
    "web": {
      // ...
    },
    "mobile": {
      "device_config": {
        "server": {
          // ...
        },
        "device": {
          // ...
          "swipe": {
            // ...
          },
          "application": {
            // ...
            // highlight-next-line
            "type": "HYBRID"
          },
          "virtual_device": {
            // ...
          }
        }
      }
    }
  },
  "api": {
    // ...
  }
}
```

:::

## Instance methods

### `contexts`

This method will get the list of all the available contexts on the screen.

```java
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
. . .
var contextList = withContext ().contexts ();
```

### `currentContext` {#current-context}

This method will get the current context name on the screen.

```java
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
. . .
var contextName = withContext ().currentContext ();
```

### `switchToNative` {#switch-to-native}

This method will switch current context to native context. This will work only for Hybrid application type.

```java
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
. . .
withContext ().switchToNative ();
```

### `switchToWebView (name)` {#switch-to-web-view-name}

This method will switch the context to mentioned context name. This will work only for Hybrid application type.

```java
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
. . .
withContext ().switchToWebView ("WEBVIEW_xxxx");
```

### `switchToWebView` {#switch-to-web-view}

This method will switch the context to first available Web view context. This will work only for Hybrid application type.

```java
import static io.github.boykaframework.actions.drivers.ContextActions.withContext;
. . .
withContext ().switchToWebView ();
```
