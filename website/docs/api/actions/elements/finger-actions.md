---
title: FingerActions
sidebar_position: 4
---

## Static methods

### `withFinger (locator)` {#with-finger-locator}

This will return `IFingerActions` which will expose different methods to handle all finger related actions on an element.

```java
IFingerActions fingerActions = FingerActions.withFinger (locator);
```

### `withFinger` {#with-finger}

This will return `IFingerActions` which will expose different methods to handle all finger related actions on the screen.

```java
IFingerActions fingerActions = FingerActions.withFinger ();
```

## Instance methods

### `swipe`

This method will swipe on the screen / element, i.e: finger will move from center of the screen to mentioned direction of the screen / element.

```java
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
. . .
withFinger (sourceElement).swipe (SwipeDirection.UP);
```

### `dragTo (target)` {#drag-to}

This method will drag the source element to the target element.

```java
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
. . .
Locator source = // source element to drag
Locator target = // target element to drag the source to
withFinger (source).dragTo (target);
```

### `swipeTill`

This method will swipe left on the screen, i.e: finger will move from center of the screen to left of the screen.

```java
import static com.github.wasiqb.boyka.actions.elements.FingerActions.withFinger;
. . .
withFinger (sourceElement).swipeTill (SwipeDirection.UP);
```
