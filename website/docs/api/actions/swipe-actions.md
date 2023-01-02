---
title: SwipeActions
sidebar_position: 7
---

## `down`

This method will swipe down on the screen, i.e: finger will move from center of the screen to bottom of the screen.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
swipe ().down ();
```

## `dragTo (source, target)` {#drag-to}

This method will drag the source element to the target element.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
WebElement sourceElement = // source element to drag
WebElement targetElement = // target element to drag the source to
swipe ().dragTo (sourceElement, targetElement);
```

## `left`

This method will swipe left on the screen, i.e: finger will move from center of the screen to left of the screen.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
swipe ().left ();
```

## `right`

This method will swipe right on the screen, i.e: finger will move from center of the screen to right of the screen.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
swipe ().right ();
```

## `till (locator)` {#till}

This method will [swipe up](#up) on the screen until the element located by the provided locator is visible.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
WebElement someElementBelowViewport = // Element which is somewhere below the viewport.
swipe ().till (someElementBelowViewport);
```

## `up`

This method will swipe up on the screen, i.e: finger will move from center of the screen to up of the screen.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.swipe;
. . .
swipe ().up ();
```
