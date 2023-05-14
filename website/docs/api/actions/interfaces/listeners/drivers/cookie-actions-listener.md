---
title: ICookieActionsListener
sidebar_position: 3
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when handling Web cookies, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onCookie (name)` {#on-cookie}

This method will get executed after getting the cookie by its name.

## `onCookies` {#on-cookies}

This method will get executed after getting all the cookies.

## `onDelete (name)` {#on-delete}

This method will get executed after deleting the cookie by its name.

## `onDeleteAll` {#on-delete-all}

This method will get executed after deleting all the cookies.
