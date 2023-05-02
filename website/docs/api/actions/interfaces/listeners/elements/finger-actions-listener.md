---
title: IFingerActionsListener
sidebar_position: 4
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the single finger related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onDragTo (source, destination)` {#on-drag-to}

This method will get executed after dragging the source element located by using the provided source element locator to destination element located by using the provided destination locator.

## `onSwipe (locator, direction)` {#on-swipe}

This method will get executed after swiping on the element located by using the provided element locator in the provided direction.

## `onSwipeTill (locator, direction)` {#on-swipe-till}

This method will get executed after swiping till the element located by using the provided element locator is visible in the provided direction.

## `onTap (locator)` {#on-tap}

This method will get executed after tapping on the element located by using the provided element locator.
