---
title: ITextBoxActionsListener
sidebar_position: 6
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the textbox related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onEnterText (locator, text)` {#on-enter-text}

This method will get executed after entering the provided text in the element located by using the provided element locator.

## `onFocus (locator)` {#on-focus}

This method will get executed after focusing on an input element located by using the provided element locator.
