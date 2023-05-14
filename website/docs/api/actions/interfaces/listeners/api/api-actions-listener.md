---
title: IApiActionsListener
sidebar_position: 1
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when the request gets executed, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onExecute` {#on-execute}

This method will get executed after the request has been executed. This method will get the `ApiResponse` object which will contain all the details of the response and the request which was executed.
