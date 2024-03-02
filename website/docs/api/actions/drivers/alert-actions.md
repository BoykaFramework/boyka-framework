---
title: AlertActions
sidebar_position: 1
---

## Static methods

### `onAlert` {#on-alert}

This will return `IAlertActions` which will expose different methods to handle Alerts.

```java
IAlertActions alertActions = AlertActions.onAlert ();
```

## Instance methods

### `accept (text)` {#accept-prompt}

This method will enter the text in the prompt, accept the Alert and will return the Alert message.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
String message = onAlert ().accept ("some text");
```

### `accept` {#accept}

This method will accept the Alert and return the Alert message.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
String message = onAlert ().accept ();
```

### `dismiss` {#dismiss}

This method will dismiss the Alert and return the Alert message.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
String message = onAlert ().dismiss ();
```

## `verifyAccept` {#verify-accept}

This method is used to verify the alert message after accepting the alert.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
onAlert ().verifyAccept ().isEqualTo ("Swag Labs");
```

## `verifyAccept (text)` {#verify-accept-alert}

This method is used to verify the alert message after entering the text in the prompt and accepting it.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
onAlert ().verifyAccept ("Sample text").isEqualTo ("Swag Labs");
```

## `verifyDismiss` {#verify-dismiss}

This method is used to verify the alert message after dismissing the alert.

```java
import static io.github.boykaframework.actions.drivers.AlertActions.onAlert;
. . .
onAlert ().verifyDismiss ().isEqualTo ("Swag Labs");
```
