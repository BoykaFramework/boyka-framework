---
sidebar_position: 1
title: 🕸️ Appium with Selenium Grid 4
---

In this tutorials, you will learn how to run Appium tests on Selenium Grid 4 (traditional Hub and Node) using Boyka framework.

## Start Appium Servers

First step is to start 2 instances of Appium server, one for Android and one for iOS.

To start Appium server for Android, run the following command:

```shell
appium server --config core-java/src/test/resources/grid/appium-android.yml
```

Note that we are using Appium config file located at `core-java/src/test/resources/grid/appium-android.yml` in the Boyka framework repository.

Now, we will start another instance of Appium server for iOS on another terminals by running the following command:

```shell
appium server --config core-java/src/test/resources/grid/appium-ios.yml
```

Here also we are using another Appium config file located at `core-java/src/test/resources/grid/appium-ios.yml` in the Boyka framework repository.

## Start Selenium Grid 4

Now start Selenium Grid Hub by running the following command on a new terminal window:

```shell
java -jar core-java/libs/selenium-server-4.12.0.jar hub
```

Here you can download the Selenium server JAR file from the Selenium Website or you can use the one available at `core-java/libs/selenium-server-4.12.0.jar` in Boyka framework repository.

Now, on a new terminal window, run the following command to connect the Android Appium server session node to the Selenium Grid Hub:

```shell
java -jar core-java/libs/selenium-server-4.12.0.jar node --config core-java/src/test/resources/grid/appium-node-android.toml
```

Here we are using Android Node config file located at `core-java/src/test/resources/grid/appium-node-android.toml` in the framework repository.

Same way, we will start another node for iOS by running the following command on a new terminal window:

```shell
java -jar core-java/libs/selenium-server-4.12.0.jar node --config core-java/src/test/resources/grid/appium-node-ios.toml
```

Here we are using iOS node config file located at `core-java/src/test/resources/grid/appium-node-ios.toml` in the framework repository.

## Boyka config for Selenium Grid

We will be using the following Boyka config file for running our tests on Android and iOS platforms:

```json
{
  "ui": {
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10,
      "highlight_delay": 100
    },
    "screenshot": {
      "enabled": true,
      "path": "./screenshots",
      "extension": "jpeg",
      "prefix": "SCR"
    },
    "mobile": {
      "test_grid_wdio_android": {
        "server": {
          "target": "LOCAL",
          "port": 4444,
          "driver": "UI_AUTOMATOR",
          "external": true
        },
        "device": {
          "os": "ANDROID",
          "version": "11",
          "name": "Pixel_7_Pro",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "ignore_unimportant_views": true,
          "full_reset": true,
          "swipe": {
            "max_swipe_until_found": 5
          },
          "application": {
            "path": "/apps/android/wdio-demo.apk",
            "wait_activity": "com.wdiodemoapp.MainActivity",
            "type": "HYBRID",
            "install_timeout": 180,
            "wait_timeout": 120
          },
          "virtual_device": {
            "name": "Pixel_7_Pro",
            "headless": true
          }
        }
      },
      "test_grid_wdio_ios": {
        "server": {
          "target": "LOCAL",
          "port": 4444,
          "driver": "XCUI",
          "external": true
        },
        "device": {
          "os": "IOS",
          "version": "16.2",
          "name": "iPhone 14",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "connect_keyboard": false,
          "typing_speed": 35,
          "swipe": {
            "max_swipe_until_found": 5
          },
          "virtual_device": {
            "headless": true,
            "launch_timeout": 180
          },
          "wda": {
            "launch_timeout": 120,
            "connection_timeout": 120
          },
          "application": {
            "path": "/apps/ios/wdio-demo.app.zip",
            "type": "HYBRID",
            "install_timeout": 180
          }
        }
      }
    }
  }
}
```

:::tip
In this example Boyka config, you must use the correct device details like, device name, platform version, virtual device name, etc.
:::

Now, when you run your tests using these config keys, your test will get executed on the Android and iOS platform with Selenium Grid 4.
