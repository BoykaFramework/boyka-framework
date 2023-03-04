---
title: 🪛 Setup Configuration
sidebar_position: 1
---

You can set multiple configurations in the configuration file with different key name for different Android devices.

Let's see how to set configuration in the configuration file for Android application to run on different devices.

```json title="src/test/resources/boyka-config.json"
{
  "ui": {
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10
    },
    "logging": {
      "exclude_logs": [
        "bugreport"
      ]
    },
    "screenshot": {
      "enabled": true,
      "path": "./screenshots",
      "extension": "jpeg",
      "prefix": "SCR"
    },
    "mobile": {
      "test_local_sauce_android": {
        "server": {
          "target": "LOCAL",
          "port": 4723,
          "base_path": "/wd/hub",
          "session_override": true,
          "driver": "UI_AUTOMATOR",
          "allow_insecure": [
            "get_server_logs"
          ]
        },
        "device": {
          "os": "ANDROID",
          "version": "11",
          "name": "Pixel_6_Pro",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "ignore_unimportant_views": true,
          "swipe": {
            "distance": 25,
            "max_swipe_until_found": 5
          },
          "application": {
            "path": "/apps/android/sauce-demo.apk",
            "install_timeout": 180
          },
          "virtual_device": {
            "name": "Pixel_6_Pro",
            "headless": true
          }
        }
      },
      "test_bs_android": {
        "server": {
          "target": "BROWSER_STACK",
          "user_name": "${env:BS_USER}",
          "password": "${env:BS_KEY}",
          "base_path": "/wd/hub",
          "driver": "UI_AUTOMATOR"
        },
        "device": {
          "os": "ANDROID",
          "version": "11.0",
          "name": "Google Pixel 5",
          "type": "CLOUD",
          "ignore_unimportant_views": true,
          "application": {
            "path": "AndroidApp",
            "external": true,
            "install_timeout": 180
          },
          "capabilities": {
            "projectName": "BrowserStack Android Project",
            "buildName": "Test BrowserStack Build",
            "sessionName": "Test BrowserStack Session",
            "appiumVersion": "2.0.0",
            "automationVersion": "latest",
            "deviceLogs": true,
            "networkLogs": true,
            "debug": true,
            "video": true,
            "appiumLogs": true
          }
        }
      },
      "test_lt_android": {
        "server": {
          "target": "LAMBDA_TEST_MOBILE",
          "user_name": "${env:LT_USER}",
          "password": "${env:LT_KEY}",
          "base_path": "/wd/hub",
          "driver": "UI_AUTOMATOR"
        },
        "device": {
          "type": "CLOUD",
          "application": {
            "install_timeout": 180
          },
          "ignore_unimportant_views": true,
          "capabilities": {
            "platformName": "Android",
            "deviceName": "Pixel 5",
            "platformVersion": "11",
            "app": "${env:LT_APP_ANDROID}",
            "project": "LambdaTest Android Project",
            "build": "Test LambdaTest Build",
            "name": "Test LambdaTest Session",
            "devicelog": true,
            "network": true,
            "visual": true,
            "video": true,
            "autoGrantPermissions": true,
            "autoAcceptAlerts": true,
            "isRealMobile": true,
            "w3c": true
          }
        }
      }
    }
  }
}
```

:::info
For more information about API configurations, please refer to [Mobile configuration guide][mobile-config].
:::

## Details of each Android configurations {#android-config-details}

- `test_local_sauce_android`: This is the configuration for running the test on local Android Emulator.
- `test_bs_android`: This is the configuration for running the test on the BrowserStack cloud Android device.
- `test_lt_android`: This is the configuration for running the test on the LambdaTest cloud Android device.

[mobile-config]: /docs/guides/configuration#mobile-config
