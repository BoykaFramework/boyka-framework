---
title: ClickableActions
sidebar_position: 1
---

## Static methods

### `withMouse` {#with-mouse}

This will return `IClickableActions` which will expose different methods to handle mouse actions.

```java
IClickableActions mouseActions = ClickableActions.withMouse ();
```

## Instance methods

### `click`

This method is used to click on the given element if it's a Web application or will perform `tap` action if the element is on Mobile. It will also scroll the element into view before clicking on it (in Web application)

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).click ();
```

### `doubleClick` {#double-click}

This method is used to double click on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).doubleClick ();
```

### `rightClick` {#right-click}

This method is used to right click on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).rightClick ();
```

### `clickAndHold` {#click-and-hold}

This method is used to click and hold on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).clickAndHold ();
```

### `dragTo (locator)` {#drag-to}

This method is used to drag source element and drop it on the target element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
Locator source = // source element to drag
Locator target = // target element to drag the source to
withMouse (source).dragTo (target);
```

### `draw (steps)` {#draw}

This method is used to draw on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.MOVE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PAUSE;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.PRESSED;
import static io.github.boykaframework.actions.elements.MouseAction.ActionType.RELEASED;
import static io.github.boykaframework.testng.ui.excalidraw.pages.HomePage.homePage;
import static java.time.Duration.ofMillis;
. . .
final var steps = new ArrayList<MouseAction> ();
steps.add (MouseAction.composeAction ()
    .actionType (MOVE)
    .duration (ofMillis (5))
    .location (new Point (x, y))
    .compose ());
steps.add (MouseAction.composeAction ()
    .actionType (PRESSED)
    .compose ());
steps.add (MouseAction.composeAction ()
    .actionType (PAUSE)
    .duration (ofMillis (10))
    .compose ());
steps.add (MouseAction.composeAction ()
    .actionType (RELEASED)
    .compose ());

withMouse (homePage ().getCanvas ()).draw (steps);
```

### `hover`

This method is used to hover on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).hover ();
```

### `pressBackButton` {#press-back-button}

This method is used to press the back button of mouse on the device.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse ().pressBackButton ();
```

### `pressForwardButton` {#press-forward-button}

This method is used to press the forward button of mouse on the device.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse ().pressForwardButton ();
```

### `scrollToElement` {#scroll-to-element}

This method is used to scroll the given element into view.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).scrollToElement ();
```

### `submit`

This method is used to submit the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).submit ();
```
