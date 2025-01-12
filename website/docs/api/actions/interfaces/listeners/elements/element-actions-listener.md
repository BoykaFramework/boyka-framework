---
title: IElementActionsListener
sidebar_position: 3
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the Element related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onClear (locator)` {#on-clear}

This method will get executed after clearing the value in the element located by using the provided locator.

## `onExecuteScript (script, args)` {#on-execute-script}

This method will get executed after executing the provided script on the element with the provided arguments.

## `onGetAttribute (locator, attribute)` {#on-get-attribute}

This method will get executed after getting the given attribute in the element located by using the provided locator.

## `onGetStyle (locator, styleName)` {#on-get-style}

This method will get executed after getting the given style in the element located by using the provided locator.

## `onGetElement (locator)` {#on-get-element}

This method will get executed after getting the element located by using the provided locator.

## `onGetLocation (locator)` {#on-get-location}

This method will get executed after getting the location of the element located by using the provided locator.

## `onGetSize (locator)` {#on-get-size}

This method will get executed after getting the size of the element located by using the provided locator.

## `onGetText (locator)` {#on-get-text}

This method will get executed after getting the text of the element located by using the provided locator.

## `onIsDisplayed (locator)` {#on-is-displayed}

This method will get executed after checking if the element located by using the provided locator is displayed.

## `onIsEnabled (locator)` {#on-is-enabled}

This method will get executed after checking if the element located by using the provided locator is enabled.

## `onIsSelected (locator)` {#on-is-selected}

This method will get executed after checking if the element located by using the provided locator is selected.

## `onScrollIntoView (locator)` {#on-scroll-into-view}

This method will get executed after scroll the element located by using the provided locator into view.

## `onVerifyAttribute (locator, attribute)` {#on-verify-attribute}

This method will get executed after verifying the provided attribute value of the element located by using the provided locator into view.

## `onVerifyIsDisplayed (locator)` {#on-verify-is-displayed}

This method will get executed after verifying the element located by using the provided locator is displayed.

## `onVerifyIsEnabled (locator)` {#on-verify-is-enabled}

This method will get executed after verifying the element located by using the provided locator is enabled.

## `onVerifyIsSelected (locator)` {#on-verify-is-selected}

This method will get executed after verifying the element located by using the provided locator is selected.

## `onVerifyStyle (locator, styleName)` {#on-verify-style}

This method will get executed after verifying the provided style of the element located by using the provided locator.

## `onVerifyText (locator)` {#on-verify-text}

This method will get executed after verifying the text of the element located by using the provided locator.
