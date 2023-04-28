---
title: IClickableActionsListener
sidebar_position: 1
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the Mouse related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onClick (locator)` {#on-click}

This method will get executed after clicking on the element located by using the provided locator.

## `onClickAndHold (locator)` {#on-click-and-hold}

This method will get executed after clicking and holding on the element located by using the provided locator.

## `onDoubleClick (locator)` {#on-double-click}

This method will get executed after double clicking on the element located by using the provided locator.

## `onDragTo (source, destination)` {#on-double-click}

This method will get executed after dragging the source element located by using the provided source locator to the destination element located by using the provided destination locator.

## `onHover (locator)` {#on-hover}

This method will get executed after hovering on the element located by using the provided locator.

## `onRightClick (locator)` {#on-hover}

This method will get executed after right clicking on the element located by using the provided locator.

## `onSubmit (locator)` {#on-hover}

This method will get executed after submitting on the element located by using the provided locator.
