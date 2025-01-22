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

### `getClipboardText` {#get-clipboard-text}

This method is used to get the device clipboard text.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
String text = onAndroidDevice ().getClipboardText ();
```

### `openNotification` {#open-notification}

This method is used to open the notification panel.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
onAndroidDevice ().openNotification ();
```

### `pressKey (key)` {#press-key}

This method is used to press Android device keys.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
onAndroidDevice ().pressKey (AndroidKey.BACK);
```

### `pullFile` {#pull-file}

This method is used to pull a file from the device.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
byte[] content = onAndroidDevice ().pullFile ("/sdcard/Download/boyka.png");
```

### `putFile` {#put-file}

This method is used to push a file to the device.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
boolean success = onAndroidDevice ().putFile (new File ("/path/to/local/file.png"), "/sdcard/Download/boyka.png");
```

### `setClipboardText` {#set-clipboard-text}

This method is used to set the device clipboard text.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
onAndroidDevice ().setClipboardText ("Hello, World!");
```

### `verifyClipboardText` {#verify-clipboard-text}

This method is used to verify the device clipboard text.

```java
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
. . .
onAndroidDevice ().verifyClipboardText ()
    .isEqualTo ("Hello World!");
```
