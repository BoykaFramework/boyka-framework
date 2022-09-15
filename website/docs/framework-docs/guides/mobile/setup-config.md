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
