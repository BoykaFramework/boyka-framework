---
title: IDropDownActionsListener
sidebar_position: 2
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling the Drop down related actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onDeselectAll (locator)` {#on-deselect-all}

This method will get executed after deselecting all the items from the dropdown element located by using the provided locator.

## `onDeselectByIndex (locator, index)` {#on-deselect-by-index}

This method will get executed after deselecting an item at the provided index from the dropdown element located by using the provided locator.

## `onDeselectByText (locator, text)` {#on-deselect-by-text}

This method will get executed after deselecting an item by its provided text from the dropdown element located by using the provided locator.

## `onDeselectByValue (locator, value)` {#on-deselect-by-value}

This method will get executed after deselecting an item by its provided value from the dropdown element located by using the provided locator.

## `onSelectByIndex (locator, index)` {#on-select-by-index}

This method will get executed after selecting an item at the provided index from the dropdown element located by using the provided locator.

## `onSelectByText (locator, text)` {#on-select-by-text}

This method will get executed after selecting an item by its provided text from the dropdown element located by using the provided locator.

## `onSelectByValue (locator, value)` {#on-select-by-value}

This method will get executed after selecting an item by its provided value from the dropdown element located by using the provided locator.

## `onSelectedItem (locator)` {#on-selected-item}

This method will get executed after getting the selected item from the dropdown element located by using the provided locator.

## `onSelectedItems (locator)` {#on-selected-items}

This method will get executed after getting all the selected items from the dropdown element located by using the provided locator.

## `onVerifySelectedItem (locator)` {#on-verify-selected-item}

This method will get executed after verifying the selected item from the dropdown element located by using the provided locator.

## `onVerifySelectedItems (locator)` {#on-verify-selected-items}

This method will get executed after verifying all the selected items from the dropdown element located by using the provided locator.
