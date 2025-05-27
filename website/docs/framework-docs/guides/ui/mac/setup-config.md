---
title: 1️⃣ Setup Configuration
sidebar_position: 1
---

## Create MacOS configuration

To generate a new configuration, run the following command on your terminal:

```shell
boyka config mac [config-name]
```

A set of questions will be asked, depending on your response, a new config settings will get created with the given config name.

:::tip
You can add as many different MacOS configs as you want using the above command
:::

## Update existing configuration

To update any existing MacOS configuration, you can update the required values in the `boyka-config.json` file.

:::info
Check out complete details about MacOS configurations in [MacOS configuration guide][desktop-config].
:::

Let's see how a sample MacOS configuration in the config file is:

<details>
  <summary>Sample MacOS config</summary>

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
      "exclude_logs": ["bugreport"]
    },
    "screenshot": {
      "enabled": true,
      "path": "./screenshots",
      "extension": "jpeg",
      "prefix": "SCR"
    },
    "desktop": {
      "test_mac": {
        "server": {
          "target": "LOCAL",
          "port": 4729,
          "session_override": true,
          "driver": "MAC"
        },
        "machine": {
          "os": "MAC",
          "version": "15.4",
          "application": {
            "bundle_id": "com.apple.calculator"
          },
          "video": {
            "enabled": true,
            "time_limit": 300,
            "mac": {
              "preset": "VERY_FAST",
              "device_id": 4,
              "capture_clicks": true,
              "capture_cursor": true,
              "fps": 10
            }
          }
        }
      }
    },
  }
}
```

</details>

## Details of each MacOS configurations {#mac-config-details}

- `test_mac`: This is the configuration for running the test on the local MacOS desktop.

[desktop-config]: /docs/guides/config/configuration#desktop-config
