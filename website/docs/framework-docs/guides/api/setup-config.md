---
title: 1️⃣ Setup API Configuration
sidebar_position: 1
---

## Create API configuration

To generate a new configuration, run the following command on your terminal:

```shell
boyka config api [config-name]
```

A set of questions will be asked, depending on your response, a new config settings will get created with the given config name.

:::tip
You can add as many different API configs as you want using the above command
:::

## Update existing configuration

To update any existing API configuration, you can update the required values in the `boyka-config.json` file.

:::info
Check out complete details about API configurations in [API configuration guide][api-config].
:::

Let's see how a sample API configuration in the config file is:

<details>
  <summary>Sample API config</summary>

```json title="src/test/resources/boyka-config.json"
{
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
      },
      "schema_path":"schema/",
      "validate_ssl": true,
      "verify_host_name": true
    }
  }
}
```

</details>

[api-config]: /docs/guides/config/configuration#api-config
