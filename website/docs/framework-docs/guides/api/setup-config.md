---
title: 🪛 Setup Configuration file
sidebar_position: 1
---

We can set multiple configurations in the configuration file with different key name for different end points.

Let's see how to set configuration in the configuration file for API end-points.

```json title="src/test/resources/boyka-config.json"
{
  "ui": {
    ...
  },
  "api": {
    "test_reqres": {
      "base_uri": "https://reqres.in",
      "base_path": "/api",
      "read_timeout": 2,
      "write_timeout": 2,
      "connection_timeout": 1,
      "logging": {
        "request": true,
        "response": true
      }
    }
  }
}
```

:::info
For more information about API configurations, please refer to [API configuration guide][api-config].
:::

[api-config]: /docs/guides/configuration#api-config
