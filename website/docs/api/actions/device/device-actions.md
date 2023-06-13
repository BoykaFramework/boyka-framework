---
title: DeviceActions
sidebar_position: 2
---

## Static methods

### `onDevice` {#on-device}

This will return `IDeviceActions` which will expose different methods to handle different device specific actions.

```java
IDeviceActions deviceActions = DeviceActions.onDevice ();
```

## Instance methods

### `isKeyboardVisible` {#is-keyboard-visible}

This method is used to determine if the Mobile keyboard is visible.

```java
import static com.github.wasiqb.boyka.actions.device.DeviceActions.onDevice;
. . .
var isKeyboardDisplayed = onDevice ().isKeyboardVisible ();
```

### `hideKeyboard` {#hide-keyboard}

This method is used to hide the keyboard on the mobile.

```java
import static com.github.wasiqb.boyka.actions.device.DeviceActions.onDevice;
. . .
onDevice ().hideKeyboard ();
```
