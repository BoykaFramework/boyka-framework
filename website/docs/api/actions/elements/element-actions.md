---
title: ElementActions
sidebar_position: 3
---

## Static methods

### `onElement` {#on-element}

This will return `IElementActions` which will expose different methods to handle all other element actions.

```java
IElementActions elementActions = ElementActions.onElement ();
```

## Instance methods

### `getAttribute (attribute)` {#get-attribute}

This method is used to get the attribute of the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
String attribute = onElement (locator).getAttribute ("href");
```

### `clear`

This method is used to clear the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).clear ();
```

### `isDisplayed` {#is-displayed}

This method is used to check whether the given element is displayed or not.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
boolean displayed = onElement (locator).isDisplayed ();
```

### `isEnabled` {#is-enabled}

This method is used to check whether the given element is enabled or not.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
boolean enabled = onElement (locator).isEnabled ();
```

### `isSelected` {#is-selected}

This method is used to check whether the given element is selected or not.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
boolean selected = onElement (locator).isSelected ();
```

### `getStyle (attribute)` {#get-style}

This method will return the style of the given element for the given attribute.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
String backgroundColor = onElement (locator).getStyle ("background-color");
```

### `tap`

This method will tap on element on the Mobile screen using W3C actions.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).tap ();
```

### `getText` {#get-text}

This method is used to get the text of the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
String text = onElement (locator).getText ();
```

### `verifyAttribute (attribute)` {#verify-attribute}

This method is used to verify the given attribute of the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyAttribute ("attribute-name").isEqualTo ("Swag Labs");
```

### `verifyIsDisplayed` {#verify-is-displayed}

This method is used to verify the given element is displayed.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyIsDisplayed ().isTrue();
```

### `verifyIsEnabled` {#verify-is-enabled}

This method is used to verify the given element is enabled.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyIsEnabled ().isTrue();
```

### `verifyIsSelected` {#verify-is-selected}

This method is used to verify the given element is selected.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyIsSelected ().isTrue();
```

### `verifyStyle (attribute)` {#verify-style}

This method is used to verify the style of the given element locator.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyStyleOf ("color").isEqualTo ("Red");
```

### `verifyText` {#verify-text}

This method is used to verify the text of the given element.

```java
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
. . .
onElement (locator).verifyText ().isEqualTo ("Swag Labs");
```
