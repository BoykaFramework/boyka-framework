---
title: ITestDataActionsListener
sidebar_position: 2
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when the request gets executed, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onGet` {#on-get}

This method will get executed after the `get` method has been executed. It takes test data class as parameter.

## `onRow` {#on-row}

This method will get executed when `row` method will get executed.

## `onRows` {#on-rows}

This method will get executed when `rows` method will get executed.
