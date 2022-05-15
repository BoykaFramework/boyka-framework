---
title: VerifyElementActions
sidebar_position: 6
---

## `verifyAttributeOf`

This method is used to verify the given attribute of the given element.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyAttributeOf;
. . .
verifyAttributeOf (locator, "attribute-name").isEqualTo ("Swag Labs");
```

## `verifyElementDisplayed`

This method is used to verify the given element is displayed.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
. . .
verifyElementDisplayed (locator).isTrue();
```

## `verifyElementEnabled`

This method is used to verify the given element is enabled.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
. . .
verifyElementEnabled (locator).isTrue();
```

## `verifyElementSelected`

This method is used to verify the given element is selected.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementSelected;
. . .
verifyElementSelected (locator).isTrue();
```

## `verifyTextOf`

This method is used to verify the text of the given element.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
. . .
verifyTextOf (locator).isEqualTo ("Swag Labs");
```
