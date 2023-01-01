---
title: ElementActions
sidebar_position: 3
---

## `attributeOf (locator, attribute)` {#attribute-of}

This method is used to get the attribute of the given element.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.attributeOf;
. . .
String attribute = attributeOf ("href");
```

## `clear (locator)` {#clear}

This method is used to clear the given element.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.clear;
. . .
clear (locator);
```

## `isDisplayed (locator)` {#isDisplayed}

This method is used to check whether the given element is displayed or not.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.isDisplayed;
. . .
boolean displayed = isDisplayed (locator);
```

## `isEnabled (locator)` {#isEnabled}

This method is used to check whether the given element is enabled or not.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.isEnabled;
. . .
boolean enabled = isEnabled (locator);
```

## `isSelected (locator)` {#isSelected}

This method is used to check whether the given element is selected or not.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.isSelected;
. . .
boolean selected = isSelected (locator);
```

## `styleOf (locator, attribute)` {#styleOf}

This method will return the style of the given element for the given attribute.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.styleOf;
. . .
String backgroundColor = styleOf (locator, "background-color");
```

## `submit (locator)` {#submit}

This method is used to submit the given element.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.submit;
. . .
submit (locator);
```

## `tapOn (locator)` {#tapOn}

This method will tap on element on the Mobile screen using W3C actions.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.tapOn;
. . .
tapOn (locator);
```

## `textOf (locator)` {#textOf}

This method is used to get the text of the given element.

```java
import static com.github.wasiqb.boyka.actions.ElementActions.textOf;
. . .
String text = textOf (locator));
```
