---
title: Setup Configuration file
sidebar_position: 1
---

Before starting to automate Web applications, we need to first setup the configuration file for our Application under test.

```json title="src/test/resources/boyka-config.json"
{
  "ui": {
    "playback": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10
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
      "test_local_opera": {
        "browser": "OPERA"
      },
      "test_browserstack_chrome": {
        "browser": "REMOTE",
        "cloud": "BROWSER_STACK",
        "protocol": "HTTPS",
        "host": "hub-cloud.browserstack.com",
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
        "cloud": "NONE",
        "port": "4444",
        "capabilities": {
          "browserName": "chrome",
          "platform": "MAC"
        }
      },
      "test_lambda_test_chrome": {
        "browser": "REMOTE",
        "cloud": "LAMBDA_TEST",
        "protocol": "HTTPS",
        "host": "hub.lambdatest.com",
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
    },
    "android": {
      ...
    },
    "ios": {
      ...
    }
  },
  "api": {
    ...
  }
}
```

:::info
To know more about Web configurations, please refer to the [Web Configuration guide][web-config].
:::

[web-config]: /docs/guides/configuration#web-config
