---
title: IFrameActionsListener
sidebar_position: 5
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling Web iFrames related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onSwitchTo (frameNameOrId)` {#on-switch-to}

This method will get executed after executing switching to the iFrame by its name or ID.

## `onSwitchTo (index)` {#on-switch-to-index}

This method will get executed after executing switching to the iFrame by index.

## `onSwitchToParent` {#on-switch-to-parent}

This method will get executed after executing switching to the parent iFrame.
