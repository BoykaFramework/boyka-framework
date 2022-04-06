---
sidebar_position: 1
title: Configuration
---

The framework is highly configurable. You can configure repeated setting for your test in the framework configuration file.

## Configuration File

:::caution
Boyka framework is driven by `boyka-config.json` configuration file. You must have this file in order to use the framework.
:::

Configuration is stored in `boyka-config.json` file. This file is in JSON format and is expected to be placed in `src/test/resources/` directory. The file is loaded only once by the framework during the initialization.

:::danger
The file name and it's location cannot be modified.
:::

## Configuration file sample {#config-sample}

```json title="boyka-config.json"
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
        "user_name": "${env:CLOUD_USER}",
        "password": "${env:CLOUD_KEY}",
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
      }
    }
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

## Configuration File properties

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `ui` | Contains UI platform specific configuration. See [UI Config below](#ui-config). | `object` |  |
| `api` | Contains API platform specific configuration. See [API config below](#api-config). | `object` |  |

### UI Configuration {#ui-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `playback` | Contains playback configuration. See [Playback Config below](#playback-config). | `PlaybackSetting` |  |
| `web` | Contains web platform configuration. See [Web Config below](#web-config). | `Map<String, WebSetting>` |  |
| `android` | Contains Android platform configuration. See [Android Config below](#android-config). | `object` |  |
| `ios` | Contains iOS platform configuration. See [iOS Config below](#ios-config). | `object` |  |

:::info Web Configuration
In `ui` configuration block, you can provide different versions of web settings having different key names under `web` object.

See the example in [sample configuration file](#config-sample).
:::

#### Playback Configuration {#playback-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `implicit_wait` | Implicit wait for finding the elements on UI (in seconds). | `number` | `1` |
| `explicit_wait` | Explicit wait for finding the elements on UI (in seconds). | `number` | `1` |
| `page_load_timeout` | Page load timeout for waiting for page to load (in seconds). | `number` | `30` |
| `script_timeout` | Script timeout for waiting for page to load (in seconds). | `number` | `30` |

#### Web Configuration {#web-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `browser` | Browser name. | [`Browser`](#supported-browsers) | `Browser.NONE` |
| `protocol` | Protocol type | [`Protocol`](#supported-protocols) | `Protocol.HTTP` |
| `host` | Remote driver host name | `string` | `localhost` |
| `port` | Remote driver port, if `0`, port will not be considered. | `number` | `0` |
| `cloud` | Cloud service provider name. | [`CloudProviders`](#supported-cloud-providers) | `CloudProviders.NONE` |
| `user_name` | User name for cloud service provider. | `string` | `` |
| `password` | Password / Access key for cloud service provider. | `string` | `` |
| `capabilities` | Capabilities for browser. | `Map<String, Object>` | `null` |
| `headless` | Headless mode for browser. | `boolean` | `true` |

:::info
For fields `user_name` and `password`, you can use placeholder variables in the following format:

| Input type | Variable sample |
| ---------- | ------------- |
| Base64 Decoder | `${base64Decoder:SGVsbG9Xb3JsZCE=}` |
| Base64 Encoder | `${base64Encoder:HelloWorld!}` |
| Java Constant | `${const:java.awt.event.KeyEvent.VK_ESCAPE}` |
| Date | `${date:yyyy-MM-dd}` |
| DNS | <code>${dns:address&#124;apache.org}</code> |
| Environment Variable | `${env:USERNAME}` |
| File Content | `${file:UTF-8:src/test/resources/document.properties}` |
| Java | `${java:version}` |
| Localhost | `${localhost:canonical-name}` |
| Properties File | `${properties:src/test/resources/document.properties::mykey}` |
| Resource Bundle | `${resourceBundle:org.example.testResourceBundleLookup:mykey}` |
| Script | `${script:javascript:3 + 4}` |
| System Property | `${sys:user.dir}` |
| URL Decoder | `${urlDecoder:Hello%20World%21}` |
| URL Encoder | `${urlEncoder:Hello World!}` |
| URL Content (HTTP) | `${url:UTF-8:http://www.apache.org}` |
| URL Content (HTTPS) | `${url:UTF-8:https://www.apache.org}` |
| URL Content (File) | `${url:UTF-8:file:///${sys:user.dir}/src/test/resources/document.properties}` |
| XML XPath | `${xml:src/test/resources/document.xml:/root/path/to/node}` |
:::

#### Android Configuration {#android-config}

:::info
COMING SOON, STAY TUNED!
:::

#### iOS Configuration {#ios-config}

:::info
COMING SOON, STAY TUNED!
:::

### API Configuration {#api-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `base_uri` | Base URL of the API. | `string` |  |
| `base_path` | Base path of the API. | `string` |  |
| `port` | Port of the API. | `number` |  |
| `connection_timeout` | Connection timeout in seconds for the API. | `number` | 5 |
| `read_timeout` | Read timeout in seconds for the API. | `number` | 5 |
| `write_timeout` | Write timeout in seconds for the API. | `number` | 5 |
| `logging` | Logging configuration. See [Logging Config below](#logging-config). | `object` |  |

:::info API Configuration
In `api` configuration block, you can provide different versions of API settings having different key names.

See the example in [sample configuration file](#config-sample).
:::

#### Logging Configuration {#logging-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `request` | Log request information. | `boolean` | `true` |
| `response` | Log response information. | `boolean` | `true` |

## Supported Browsers {#supported-browsers}

We have an enum `Browser` where we maintain the list of supported browsers. They are:

- `CHROME`: Is used for Chrome browser.
- `EDGE`: Is used for Edge browser.
- `FIREFOX`: Is used for Firefox browser.
- `NONE`: No browser will be used.
- `OPERA`: Is used for Opera browser.
- `REMOTE`: Is used for Selenium Grid or cloud based browsers.
- `SAFARI`: Is used for Safari browser.

## Supported Protocols {#supported-protocols}

We have an enum `Protocol` where we maintain the list of supported protocols. They are:

- `HTTP`: Is used for HTTP protocol.
- `HTTPS`: Is used for HTTPS protocol.

## Supported Cloud Providers {#supported-cloud-providers}

We have an enum `CloudProviders` where we maintain the list of supported cloud providers. They are:

- `NONE`: No cloud provider will be used.
- `BROWSER_STACK`: Is used for BrowserStack cloud provider.
- `LAMBDA_TEST`: Is used for LambdaTest cloud provider.
