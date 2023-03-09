---
title: FrameActions
sidebar_position: 4
---

## Static methods

### `onFrame` {#on-frame}

This will return `IFrameActions` which will expose different methods to handle IFrames.

```java
IFrameActions frameActions = FrameActions.onFrame ();
```

## Instance methods

### `switchTo (name)` {#switch-to}

This method will switch to the given frame by it's name / ID.

```java
import static com.github.wasiqb.boyka.actions.drivers.FrameActions.onFrame;
. . .
onFrame ().switchTo ("frame-name-or-id");
```

:::tip
In order to come out of this frame, use [`switchToParent`](#switch-to-parent) method.
:::

### `switchTo (index)` {#switch-to-index}

This method will switch to the given frame by it's index.

```java
import static com.github.wasiqb.boyka.actions.drivers.FrameActions.onFrame;
. . .
onFrame ().switchTo (1);
```

:::tip
In order to come out of this frame, use [`switchToParent`](#switch-to-parent) method.
:::

### `switchToParent` {#switch-to-parent}

This method will switch to the parent frame of the current frame. This method is used to come out of any iframe.

```java
import static com.github.wasiqb.boyka.actions.drivers.FrameActions.onFrame;
. . .
onFrame ().switchToParent ();
```
