---
title: ITextBoxActionsListener
sidebar_position: 6
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the textbox related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onEnterText (locator, text)` {#on-enter-text}

This method will get executed before entering the provided text in the element located by using the provided element locator.

## `onFocus (locator)` {#on-focus}

This method will get executed before focusing on an input element located by using the provided element locator.

## `onInputValue` {#on-input-value}

This method will get executed before getting the input value from the Textbox element by using the provided element locator.

## `onVerifyInputValue` {#on-verify-input-value}

This method will get executed before verifying the input value from the Textbox element by using the provided element locator.
