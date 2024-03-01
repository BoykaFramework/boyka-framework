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

### `hover`

This method is used to hover on the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).hover ();
```

### `submit`

This method is used to submit the given element.

```java
import static io.github.boykaframework.actions.elements.ClickableActions.withMouse;
. . .
withMouse (locator).submit ();
```
