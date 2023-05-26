---
title: FingersActions
sidebar_position: 5
---

## Static methods

### `withFingers (locator)` {#with-finger-locator}

This will return `IFingerActions` which will expose different methods to handle all finger related actions on an element.

```java
IFingerActions fingerActions = FingerActions.withFinger (locator);
```

## Instance methods

### `zoomIn` {#zoom-in}

This method will zoom in on the element, i.e: one finger will move from the center of the element to the left direction and other finger will move from the center of the element to th right direction.

```java
import static com.github.wasiqb.boyka.actions.elements.FingersActions.withFingers;
. . .
withFingers (imageElement).zoomIn ();
```

### `zoomOut` {#zoom-out}

This method will zoom out on the element, i.e: one finger will move from the left edge of the element to the center and other finger will move from the right edge of the element to th center.

```java
import static com.github.wasiqb.boyka.actions.elements.FingersActions.withFingers;
. . .
withFingers (imageElement).zoomOut ();
```
