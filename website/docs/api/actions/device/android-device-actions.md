---
title: AndroidDeviceActions
sidebar_position: 1
---

## Static methods

### `onAndroidDevice` {#on-android-device}

This will return `IAndroidDeviceActions` which will expose different methods to handle different Android device specific actions.

```java
IAndroidDeviceActions androidActions = AndroidDeviceActions.onAndroidDevice ();
```

## Instance methods

### `pressKey (key)` {#press-key}

This method is used to press Android device keys.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
onAndroidDevice ().pressKey (AndroidKey.BACK);
```
