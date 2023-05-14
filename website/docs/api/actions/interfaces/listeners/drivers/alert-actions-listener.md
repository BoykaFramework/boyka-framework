---
title: IAlertActionsListener
sidebar_position: 1
---

## Implementation

In order inject your customized logging for your reports or you want to perform any other action when the handling Alert pop-ups, you can implement this class and provide your implementations for different methods available in this listener interface.

## `onAccept` {#on-accept}

This method will get executed after accepting the Alert pop-up.

## `onAccept (text)` {#on-accept-text}

This method will get executed after accepting the Alert prompt with provided text.

## `onDismiss` {#on-dismiss}

This method will get executed after dismissing the Alert pop-up.

## `onVerifyAccept (text)` {#on-verify-accept-text}

This method will get executed before verifying accepting the Alert prompt with provided text.

## `onVerifyAccept` {#on-verify-accept}

This method will get executed before verifying accepting the Alert prompt.

## `onVerifyDismiss` {#on-verify-dismiss}

This method will get executed before verifying accepting the Alert prompt.
