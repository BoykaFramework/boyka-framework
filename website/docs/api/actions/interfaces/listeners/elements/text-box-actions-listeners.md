---
title: ITextBoxActionsListener
sidebar_position: 6
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the textbox related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onEnterText (locator, text)` {#on-enter-text}

This method will get executed after entering the provided text in the element located by using the provided element locator.

## `onHideKeyboard ()` {#on-hide-keyboard}

This method will get executed after hiding the keyboard on Mobile.

## `onIsKeyboardVisible ()` {#on-is-keyboard-visible}

This method will get executed after checking if the keyboard is visible on Mobile.

## `onPressKey (locator, keys)` {#on-enter-text}

This method will get executed after pressing the provided keys in the element located by using the provided element locator.
