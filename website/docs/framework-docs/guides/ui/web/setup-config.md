---
title: 1️⃣ Setup Configuration
sidebar_position: 1
---

## Create Web configuration

To generate a new configuration, run the following command on your terminal:

```shell
boyka config web [config-name]
```

A set of questions will be asked, depending on your response, a new config settings will get created with the given config name.

:::tip
You can add as many different Web configs as you want using the above command
:::

## Update existing configuration

To update any existing Web configuration, you can update the required values in the `boyka-config.json` file.

:::info
Check out complete details about Web configurations in [Web configuration guide][web-config].
:::

Let's see how a sample Android configuration in the config file is:

<details>
  <summary>Sample Web config</summary>

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
    "web": {
      "test_local_chrome": {
        "browser": "CHROME"
      },
      "test_local_firefox": {
        "browser": "FIREFOX"
      },
      "test_local_edge": {
        "browser": "EDGE"
      },
      "test_local_safari": {
        "browser": "SAFARI"
      },
      "test_browserstack_chrome": {
        "browser": "REMOTE",
        "target": "BROWSER_STACK",
        "user_name": "${env:BS_USER}",
        "password": "${env:BS_KEY}",
        "capabilities": {
          "browser": "Chrome",
          "browser_version": "latest",
          "os": "Windows",
          "os_version": "10",
          "resolution": "1920x1080",
          "project": "Test Boyka Project",
          "build": "Test BrowserStack Build",
          "name": "Test BrowserStack Session"
        }
      },
      "test_selenium_grid": {
        "browser": "REMOTE",
        "target": "LOCAL",
        "port": "4444",
        "capabilities": {
          "browserName": "chrome",
          "platform": "MAC"
        }
      },
      "test_lambda_test_chrome": {
        "browser": "REMOTE",
        "target": "LAMBDA_TEST_WEB",
        "user_name": "${env:LT_USER}",
        "password": "${env:LT_KEY}",
        "capabilities": {
          "browserName": "Chrome",
          "version": "99.0",
          "platform": "Windows 10",
          "resolution": "1920x1080",
          "build": "Test LambdaTest Build",
          "name": "Test LambdaTest Session",
          "network": true,
          "visual": true,
          "video": true,
          "console": true
        }
      }
    }
  }
}
```

</details>

## Details of each Web configurations {#web-config-details}

- `test_local_chrome`: This is the configuration for running the test on local Chrome browser.
- `test_local_firefox`: This is the configuration for running the test on local Firefox browser.
- `test_local_edge`: This is the configuration for running the test on local Edge browser.
- `test_local_safari`: This is the configuration for running the test on local Safari browser.
- `test_browserstack_chrome`: This is the configuration for running the test on BrowserStack Chrome browser.
- `test_selenium_grid`: This is the configuration for running the test on Selenium Grid.
- `test_lambda_test_chrome`: This is the configuration for running the test on LambdaTest Chrome browser.

[web-config]: /docs/guides/config/configuration#web-config
