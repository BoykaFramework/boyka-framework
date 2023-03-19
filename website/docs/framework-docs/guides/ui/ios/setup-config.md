---
title: 🪛 Setup Configuration
sidebar_position: 1
---

You can set multiple configurations in the configuration file with different key name for different iOS devices.

Let's see how to set configuration in the configuration file for iOS application to run on different devices.

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
      "test_local_sauce_ios": {
        "server": {
          "target": "LOCAL",
          "port": 4724,
          "base_path": "/wd/hub",
          "session_override": true,
          "driver": "XCUI",
          "allow_insecure": [
            "get_server_logs"
          ]
        },
        "device": {
          "os": "IOS",
          "version": "16.2",
          "name": "iPhone 14 Pro Max",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "connect_keyboard": false,
          "typing_speed": 30,
          "swipe": {
            "distance": 25,
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
            "path": "/apps/ios/sauce-demo.zip",
            "install_timeout": 180
          }
        }
      },
      "test_bs_ios": {
        "server": {
          "target": "BROWSER_STACK",
          "user_name": "${env:BS_USER}",
          "password": "${env:BS_KEY}",
          "base_path": "/wd/hub",
          "driver": "XCUI"
        },
        "device": {
          "os": "IOS",
          "version": "16",
          "name": "iPhone 14 Pro",
          "type": "CLOUD",
          "application": {
            "path": "IOSApp",
            "external": true,
            "install_timeout": 180
          },
          "capabilities": {
            "projectName": "BrowserStack iOS Project",
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
      "test_lt_ios": {
        "server": {
          "target": "LAMBDA_TEST_MOBILE",
          "user_name": "${env:LT_USER}",
          "password": "${env:LT_KEY}",
          "base_path": "/wd/hub",
          "driver": "XCUI"
        },
        "device": {
          "type": "CLOUD",
          "application": {
            "install_timeout": 180
          },
          "ignore_unimportant_views": true,
          "capabilities": {
            "platformName": "iOS",
            "deviceName": "iPhone 14 Pro",
            "platformVersion": "16",
            "app": "${env:LT_APP_IOS}",
            "project": "LambdaTest iOS Project",
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

## Details of each iOS configurations {#ios-config-details}

- `test_local_sauce_ios`: This is the configuration for running the test on local iOS Simulator.
- `test_bs_ios`: This is the configuration for running the test on the BrowserStack cloud iOS device.
- `test_lt_ios`: This is the configuration for running the test on the LambdaTest cloud iOS device.

[mobile-config]: /docs/guides/configuration#mobile-config
