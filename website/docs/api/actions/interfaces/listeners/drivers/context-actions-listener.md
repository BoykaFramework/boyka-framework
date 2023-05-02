---
title: IContextActionsListener
sidebar_position: 2
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling Mobile contexts, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onContexts` {#on-contexts}

This method will get executed after getting all the available contexts.

## `onCurrentContext` {#on-current-context}

This method will get executed after getting the current context.

## `onSwitchToNative` {#on-switch-to-native}

This method will get executed after switching to native context.

## `onSwitchToWebView (name)` {#on-switch-to-web-view-name}

This method will get executed after switching to web view context by its provided name.

## `onSwitchToWebView` {#on-switch-to-web-view}

This method will get executed after switching to the default web view context.
