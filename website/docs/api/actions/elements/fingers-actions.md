---
title: FingersActions
sidebar_position: 5
---

## Static methods

### `withFingers (locator)` {#with-fingers}

This will return `IFingersActions` which will expose different methods to handle all multi fingers related actions on an element.

```java
IFingersActions fingersActions = FingersActions.withFingers (locator);
```

## Instance methods

### `zoomIn` {#zoom-in}

This method will zoom in on an element, i.e: one finger will move from the center of the element to the left direction and other finger will move from the center of the element to th right direction.

```java
import static com.github.wasiqb.boyka.actions.elements.FingersActions.withFingers;
. . .
withFingers (imageElement).zoomIn ();
```

### `zoomOut` {#zoom-out}

This method will zoom out from an element, i.e: one finger will move from the left edge of the element to the center and other finger will move from the right edge of the element to th center.

```java
import static com.github.wasiqb.boyka.actions.elements.FingersActions.withFingers;
. . .
withFingers (imageElement).zoomOut ();
```
