---
title: 🪛 Setup Mobile Configuration
sidebar_position: 1
---

We can set multiple configurations in the configuration file with different key name for different end points.

Let's see how to set configuration in the configuration file for API end-points.

```json title="src/test/resources/boyka-config.json"
{
  "ui": {
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10
    },
    "screenshot": {
      "enabled": true,
      "path": "./screenshots",
      "extension": "jpeg",
      "prefix": "SCR"
    },
    "mobile": {
      "test_local_android": {
        "server": {
          "protocol": "HTTP",
          "host": "127.0.0.1",
          "port": 4723,
          "session_override": true
        },
        "device": {
          "os": "ANDROID",
          "version": "10",
          "name": "Pixel_5",
          "automation": "UI_AUTOMATOR",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "application": {
            "path": "/apps/android/saucedemo.apk",
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "install_timeout": 180
          },
          "avd": {
            "headless": true
          }
        }
      },
      "test_bs_android": {
        "server": {
          "cloud": "BROWSER_STACK",
          "protocol": "HTTPS",
          "host": "hub-cloud.browserstack.com",
          "user_name": "${env:BS_USER}",
          "password": "${env:BS_KEY}",
          "base_path": "/wd/hub"
        },
        "device": {
          "os": "ANDROID",
          "version": "11.0",
          "name": "Google Pixel 5",
          "automation": "UI_AUTOMATOR",
          "type": "CLOUD",
          "application": {
            "path": "${env:BS_APP_ANDROID}",
            "external": true,
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "install_timeout": 180
          },
          "capabilities": {
            "projectName": "BrowserStack Android Project",
            "buildName": "Test BrowserStack Build",
            "sessionName": "Test BrowserStack Session",
            "appiumVersion": "1.22.0",
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
          "cloud": "LAMBDA_TEST",
          "protocol": "HTTPS",
          "host": "mobile-hub.lambdatest.com",
          "user_name": "${env:LT_USER}",
          "password": "${env:LT_KEY}",
          "base_path": "/wd/hub"
        },
        "device": {
          "automation": "UI_AUTOMATOR",
          "type": "CLOUD",
          "application": {
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
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

[mobile-config]: /docs/guides/configuration#mobile-config
