---
sidebar_position: 1
title: 🔩 Configuration
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
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10,
      "highlight_delay": 100
    },
    "screenshot": {
      "enabled": true,
      "path": "./screenshots",
      "extension": "jpeg",
      "prefix": "SCR"
    },
    "web": {
      "test_local_chrome": {
        "browser": "CHROME",
        "highlight": true
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
      },
      "schema_path": "schema/"
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
| `timeout` | Contains timeout configuration. See [Timeout Config below](#timeout-config). | `TimeoutSetting` |  |
| `screenshot` | Contains screenshot configuration. See [Screenshot Config below](#screenshot-config). | `ScreenshotSetting` |  |
| `web` | Contains web platform configuration. See [Web Config below](#web-config). | `Map<String, WebSetting>` |  |
| `mobile` | Contains Mobile platform configuration. See [Mobile Config below](#mobile-config). | `object` |  |

:::info Web Configuration
In `ui` configuration block, you can provide different versions of web settings having different key names under `web` object.

See the example in [sample configuration file](#config-sample).
:::

#### Timeout Configuration {#timeout-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `implicit_wait` | Implicit wait for finding the elements on UI (in seconds). | `number` | `1` |
| `explicit_wait` | Explicit wait for finding the elements on UI (in seconds). | `number` | `1` |
| `page_load_timeout` | Page load timeout for waiting for page to load (in seconds). | `number` | `30` |
| `script_timeout` | Script timeout for waiting for page to load (in seconds). | `number` | `30` |
| `highlight_delay` | Delay for element getting highlighted | `long` | `100`

#### Screenshot Configuration {#screenshot-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `enabled` | Enable/disable screenshot capturing. | `boolean` | `true` |
| `path` | Path to the directory where screenshots are stored. | `string` | `./screenshots` |
| `extension` | Extension of the screenshot file. | `string` | `jpeg` |
| `prefix` | Prefix of the screenshot file. | `string` | `SCR` |

#### Web Configuration {#web-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `browser` | Browser name. | [`Browser`](#supported-browsers) | `Browser.NONE` |
| `protocol` | Protocol type | [`Protocol`](#supported-protocols) | `Protocol.HTTP` |
| `host` | Remote driver host name | `string` | `null` |
| `port` | Remote driver port, if `0`, port will not be considered. | `number` | `0` |
| `cloud` | Cloud service provider name. | [`CloudProviders`](#supported-cloud-providers) | `CloudProviders.NONE` |
| `user_name` | User name for cloud service provider. | `string` | `null` |
| `password` | Password / Access key for cloud service provider. | `string` | `null` |
| `capabilities` | Capabilities for browser. | `Map<String, Object>` | `null` |
| `headless` | Headless mode for browser. | `boolean` | `true` |
| `highlight` | Highlight element on interaction, if `true` | `boolean` | `false` |

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

#### Mobile Configuration {#mobile-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `server` | Contains Appium Server related configurations | [`ServerSetting`](#server-config) | |
| `device` | Contains Mobile test device related configurations | [`DeviceSetting`](#device-config) | |

##### Appium Server Configuration {#server-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `protocol` | Protocol type of the server host | [`Protocol`](#supported-protocols) | `HTTP` |
| `host` | Host of the server | `string` | |
| `port` | Port on which server will run | `int` | |
| `base_path` | Base path of the server, normally its `/wd/hub` which needs to be added in config | `string` | |
| `external` | Set `true` if using already running server, else framework will start the server automatically | `boolean` | `false` |
| `session_override` | Enables session override | `boolean` | `true` |
| `node_path` | Node executable path | `string` | |
| `appium_path` | Appium executable `main.js` path | `string` | |
| `cloud` | Cloud service provider name. | [`CloudProviders`](#supported-cloud-providers) | `CloudProviders.NONE` |
| `user_name` | User name for cloud service provider. | `string` | `null` |
| `password` | Password / Access key for cloud service provider. | `string` | `null` |
| `android` | Android specific server settings | [`AndroidServerSetting`](#android-server-config) | |
| `logs` | Logging specific server settings | [`LogSetting`](#log-config) | |
| `allow_insecure` | Allow list of features in server considered as insecure | `List<string>` | |
| `timeout` | Timeout in seconds for server to start | `int` | `30` |

###### Android Server Configuration {#android-server-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `bootstrap_port` | Bootstrap port | `int` | |
| `reboot` | Should emulator be rebooted? | `boolean` | `false` |
| `suppress_adb_kill` | Should kill ADB after session completion? | `boolean` | `true` |

###### Server Logs Configuration {#log-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `enable` | Should the logging be saved? | `boolean` | `true` |
| `level` | Log level which server will capture | [`LogLevel`](#log-level) | `DEBUG` |
| `path` | Log folder path | `string` | `{root-folder}/logs` |

##### Mobile Device Configuration {#device-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `name` | Device Name | `string` |  |
| `os` | Device OS type | [`OS`](#supported-os) | `ANDROID` |
| `version` | Device OS version | `string` | |
| `type` | Device type | [`DeviceType`](#supported-device-types) | `VIRTUAL` |
| `automation` | Appium automation type | [`AutomationType`](#supported-automation-types) | `UI_AUTOMATOR` |
| `capabilities` | Contains cloud specific capabilities | `Map` | |
| `application` | Contains application related configs | [`ApplicationSetting`](#app-config) | |
| `avd` | Contains virtual device specific configs | [`VirtualDeviceSetting`](#avd-config) | |
| `clear_files` | Determines if system files needs to cleared after run completes on a device | `boolean` | `true` |
| `clear_logs` | Determines if device logs needs to be cleared | `boolean` | `true` |
| `full_reset` | Determines if full reset needs to be done | `boolean` | |
| `no_reset` | Determines if there should be no reset | `boolean` | |
| `grant_permission` | Determines if auto grant permission is needed | `boolean` | `true` |
| `ignore_unimportant_views` | Determines if unimportant views needs to be ignored | `boolean` | `true` |
| `server_install_timeout` | Timeout in seconds to wait for Appium server app to get installed | `int` | `30` |
| `server_launch_timeout` | Timeout in seconds to wait for Appium server app to start | `int` | `30` |

###### Device Application Configurations {#app-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `external` | Is the app saved outside `src/test/resources` folder? | `boolean` | `false` |
| `path` | Path to the AUT, can also contain environment variables for cloud App URL | `string` | |
| `type` | Application type | `ApplicationType` | `NATIVE` |
| `install_timeout` | Timeout in seconds to wait until app gets installed on device | `int` | `30` |
| `wait_activity` | Wait for the mentioned activity to load | `string` | |
| `wait_timeout` | Wait timeout in seconds to wait for AUT | `int` | `30` |

###### Virtual Device Configurations {#avd-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `name` | Name of AVD | `string` | |
| `launch_timeout` | Timeout in seconds to wait until AVD launches | `int` | `60` |
| `headless` | Determine if required to run in headless mode | `boolean` | `false` |
| `ready_timeout` | Timeout in seconds to wait until AVD is ready | `int` | `60` |

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
| `schema_path` | Path of schema file at location `src/test/resources` | `string` |  |

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

## Supported Device OS {#supported-os}

We have an enum `OS` where we maintain the list of currently supported device OS types. They are:

- `ANDROID`

## Supported device types {#supported-device-types}

- `CLOUD`
- `VIRTUAL`

## Supported Automation types {#supported-automation-types}

- `UI_AUTOMATOR`: Equivalent for `UIAutomator2` in Appium

## Supported Server Log levels {#log-level}

- `DEBUG`
- `DEBUG_DEBUG`
- `DEBUG_ERROR`
- `DEBUG_INFO`
- `DEBUG_WARN`
- `ERROR`
- `ERROR_DEBUG`
- `ERROR_ERROR`
- `ERROR_INFO`
- `ERROR_WARN`
- `INFO`
- `INFO_DEBUG`
- `INFO_ERROR`
- `INFO_INFO`
- `INFO_WARN`
- `WARN`
- `WARN_DEBUG`
- `WARN_ERROR`
- `WARN_INFO`
- `WARN_WARN`
