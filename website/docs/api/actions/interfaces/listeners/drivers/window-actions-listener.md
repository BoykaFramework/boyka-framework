---
title: IWindowActionsListener
sidebar_position: 7
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the screen window related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onClose` {#on-close}

This method will get executed after closing the window.

## `onCurrentHandle` {#on-current-handle}

This method will get executed after getting the current window handle.

## `onFullScreen` {#on-full-screen}

This method will get executed after making the window full screen.

## `onGetScreenshot` {#on-get-screenshot}

This method will get executed after getting the screenshot content.

## `onGetTitle` {#on-get-title}

This method will get executed after getting the current window title.

## `onHandles` {#on-handles}

This method will get executed after getting all the window handles.

## `onMaximize` {#on-maximize}

This method will get executed after maximizing the window.

## `onMinimize` {#on-minimize}

This method will get executed after minimizing the window.

## `onSwitchTo (nameOfHandle)` {#on-switch-to}

This method will get executed after switching to the window by its name or handle.

## `onSwitchToDefault` {#on-switch-to-default}

This method will get executed after switching to the default main window.

## `onSwitchToNew (windowType)` {#on-switch-to-new}

This method will get executed after switching to the new window for the given type.

## `onTakeScreenshot (fileName)` {#on-switch-to-new}

This method will get executed after taking the screenshot with its file name.

## `onVerifyTitle` {#on-verify-title}

This method will get executed after verifying the window title.

## `onViewportSize` {#on-viewport-size}

This method will get executed after getting the viewport size.
