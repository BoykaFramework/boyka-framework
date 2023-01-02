---
title: VerifyDriverActions
sidebar_position: 8
---

## `verifyAcceptAlert` {#verify-accept-alert}

This method is used to verify the alert message after accepting the alert.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
. . .
verifyAcceptAlert ().isEqualTo ("Swag Labs");
```

## `verifyAcceptAlert (text)` {#verify-accept-alert-text}

This method is used to verify the alert message after entering the text in the prompt and accepting it.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
. . .
verifyAcceptAlert ("Sample text").isEqualTo ("Swag Labs");
```

## `verifyBrowserTitle` {#verify-browser-title}

This method is used to verify the browser title.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
. . .
verifyBrowserTitle (title).isEqualTo ("Swag Labs");
```

## `verifyBrowserUrl` {#verify-browser-url}

This method is used to verify the browser url.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
. . .
verifyBrowserUrl (url).isEqualTo ("https://www.swaglabs.com/");
```

## `verifyDismissAlert` {#verify-dismiss-alert}

This method is used to verify the alert message after dismissing the alert.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyDismissAlert;
. . .
verifyDismissAlert ().isEqualTo ("Swag Labs");
```
