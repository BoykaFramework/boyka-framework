---
title: VerifyElementActions
sidebar_position: 9
---

## `verifyAttributeOf (locator, attribute)` {#verify-attribute-of}

This method is used to verify the given attribute of the given element.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyAttributeOf;
. . .
verifyAttributeOf (locator, "attribute-name").isEqualTo ("Swag Labs");
```

## `verifyElementDisplayed (locator)` {#verify-element-displayed}

This method is used to verify the given element is displayed.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
. . .
verifyElementDisplayed (locator).isTrue();
```

## `verifyElementEnabled (locator)` {#verify-element-enabled}

This method is used to verify the given element is enabled.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
. . .
verifyElementEnabled (locator).isTrue();
```

## `verifyElementSelected (locator)` {#verify-element-selected}

This method is used to verify the given element is selected.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementSelected;
. . .
verifyElementSelected (locator).isTrue();
```

## `verifyStyleOf (locator, attribute)` {#verify-style-of}

This method is used to verify the style of the given element locator.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyStyleOf;
. . .
verifyStyleOf (locator, "color").isEqualTo ("Red");
```

## `verifyTextOf (locator)` {#verify-text-of}

This method is used to verify the text of the given element.

```java
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
. . .
verifyTextOf (locator).isEqualTo ("Swag Labs");
```
