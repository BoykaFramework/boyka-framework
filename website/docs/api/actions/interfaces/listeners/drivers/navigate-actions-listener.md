---
title: INavigateActionsListener
sidebar_position: 6
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling Web navigation actions, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onBack` {#on-back}

This method will get executed after navigating back on the web page.

## `onForward` {#on-forward}

This method will get executed after navigating forward on the web page.

## `onGetUrl` {#on-forward}

This method will get executed after getting the current page URL.

## `onRefresh` {#on-refresh}

This method will get executed after refreshing the page.

## `onTo (url)` {#on-to}

This method will get executed after navigating to the provided URL.

## `onVerifyUrl` {#on-verify-url}

This method will get executed after verifying the current page URL.
