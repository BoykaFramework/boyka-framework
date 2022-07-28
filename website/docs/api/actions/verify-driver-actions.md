---
title: VerifyDriverActions
sidebar_position: 5
---

## `verifyAcceptAlert`

This method is used to verify the alert message after accepting the alert.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
. . .
verifyAcceptAlert ().isEqualTo ("Swag Labs");
```

## `verifyAcceptAlert(string)`

This method is used to verify the alert message after entering the text in the prompt and accepting it.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyAcceptAlert;
. . .
verifyAcceptAlert ("Sample text").isEqualTo ("Swag Labs");
```

## `verifyBrowserTitle`

This method is used to verify the browser title.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
. . .
verifyBrowserTitle (title).isEqualTo ("Swag Labs");
```

## `verifyBrowserUrl`

This method is used to verify the browser url.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
. . .
verifyBrowserUrl (url).isEqualTo ("https://www.swaglabs.com/");
```

## `verifyDismissAlert`

This method is used to verify the alert message after dismissing the alert.

```java
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyDismissAlert;
. . .
verifyDismissAlert ().isEqualTo ("Swag Labs");
```
