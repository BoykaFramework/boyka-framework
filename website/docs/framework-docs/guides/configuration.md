---
sidebar_position: 1
title: 🔩 Configuration
---

The framework is highly configurable. You can configure repeated setting for your test in the framework configuration file.

## Configuration File

:::caution
Boyka framework is driven by `boyka-config.json` configuration file. You must have this file in order to use the framework.
:::

Configuration is stored in `boyka-config.json` file. This file is in JSON format. Following is the logic which Boyka-framework will find the config file:

- First it will check for the config folder in the `BOYKA_CONFIG_PATH` environment variable
- If the environment variable is not set, then it will check `boyka.config.path` system property
- If the system property is not set, then by default, it will check in the `src/test/resources` folder of your root project path

:::danger
The Config file name cannot be modified. It should always be `boyka-config.json`
:::

## Configuration file sample {#config-sample}

```json title="boyka-config.json"
{
  "listeners_package": "com.github.wasiqb.boyka.testng.listeners",
  "ui": {
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 30,
      "page_load_timeout": 30,
      "script_timeout": 10,
      "highlight_delay": 100
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
    "web": {
      "test_local_chrome": {
        "base_url": "http://the-internet.herokuapp.com/",
        "browser": "CHROME",
        "highlight": true,
        "headless": false,
        "resize": "CUSTOM",
        "custom_size": {
          "width": 1580,
          "height": 1080
        },
        "browser_options": ["use-fake-device-for-media-stream", "use-fake-ui-for-media-stream"]
      },
      "test_local_firefox": {
        "browser": "FIREFOX"
      },
      "test_local_edge": {
        "browser": "EDGE",
        "resize": "FULL_SCREEN"
      },
      "test_local_safari": {
        "browser": "SAFARI"
      },
      "test_browserstack_chrome": {
        "browser": "REMOTE",
        "target": "BROWSER_STACK",
        "user_name": "${env:BS_USER}",
        "password": "${env:BS_KEY}",
        "resize": "MAXIMIZED",
        "capabilities": {
          "browser": "Chrome",
          "browserVersion": "latest",
          "os": "Windows",
          "osVersion": "11",
          "resolution": "1920x1080",
          "projectName": "Test Boyka Project",
          "buildName": "Test BrowserStack Build",
          "sessionName": "Test BrowserStack Session",
          "seleniumVersion": "4.8.0",
          "telemetryLogs": "true",
          "seleniumLogs": "true",
          "video": "true",
          "networkLogs": "true",
          "debug": "true"
        }
      },
      "test_selenium_grid": {
        "browser": "CHROME",
        "target": "LOCAL",
        "port": "4444"
      },
      "test_lambda_test_chrome": {
        "browser": "REMOTE",
        "target": "LAMBDA_TEST_WEB",
        "user_name": "${env:LT_USER}",
        "password": "${env:LT_KEY}",
        "resize": "MINIMIZED",
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
    "mobile": {
      "test_local_sauce_android": {
        "server": {
          "target": "LOCAL",
          "port": 4723,
          "session_override": true,
          "driver": "UI_AUTOMATOR",
          "allow_insecure": ["get_server_logs", "chromedriver_autodownload"]
        },
        "device": {
          "os": "ANDROID",
          "version": "12",
          "name": "Nexus_6",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "ignore_unimportant_views": true,
          "full_reset": true,
          "system_port": 8200,
          "swipe": {
            "distance": 50,
            "max_swipe_until_found": 5
          },
          "application": {
            "path": "/apps/android/sauce-demo.apk",
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "install_timeout": 180,
            "wait_timeout": 120
          },
          "virtual_device": {
            "name": "Nexus_6",
            "headless": true
          }
        }
      },
      "test_bs_android": {
        "server": {
          "target": "BROWSER_STACK",
          "user_name": "${env:BS_USER}",
          "password": "${env:BS_KEY}",
          "driver": "UI_AUTOMATOR"
        },
        "device": {
          "os": "ANDROID",
          "version": "11.0",
          "name": "Google Pixel 5",
          "type": "CLOUD",
          "ignore_unimportant_views": true,
          "application": {
            "path": "AndroidApp",
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "external": true,
            "install_timeout": 180
          },
          "capabilities": {
            "projectName": "BrowserStack Android Project",
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
      "test_lt_android": {
        "server": {
          "target": "LAMBDA_TEST_MOBILE",
          "user_name": "${env:LT_USER}",
          "password": "${env:LT_KEY}",
          "driver": "UI_AUTOMATOR"
        },
        "device": {
          "type": "CLOUD",
          "application": {
            "install_timeout": 180,
            "wait_activity": "com.swaglabsmobileapp.MainActivity"
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
      },
      "test_local_sauce_ios": {
        "server": {
          "target": "LOCAL",
          "port": 4725,
          "session_override": true,
          "driver": "XCUI"
        },
        "device": {
          "os": "IOS",
          "version": "16.2",
          "name": "iPhone 14",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "connect_keyboard": false,
          "typing_speed": 25,
          "swipe": {
            "distance": 50,
            "max_swipe_until_found": 5
          },
          "virtual_device": {
            "headless": true,
            "launch_timeout": 180
          },
          "wda": {
            "launch_timeout": 120,
            "connection_timeout": 120,
            "local_port": 8100
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
          "driver": "XCUI"
        },
        "device": {
          "os": "IOS",
          "version": "16",
          "name": "iPhone 14",
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
            "deviceName": "iPhone 14",
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
  },
  "api": {
    "test_restfulbooker": {
      "base_uri": "https://restful-booker.herokuapp.com",
      "read_timeout": 2,
      "write_timeout": 2,
      "connection_timeout": 1,
      "schema_path": "schema/",
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
| `ui` | Contains UI platform specific configuration. See [UI Config below](#ui-config). | `object` | |
| `api` | Contains API platform specific configuration. See [API config below](#api-config). | `object` | |
| `listeners_package` | This will be the package name under which all the Boyka framework listeners are saved. | `string` | |

### UI Configuration {#ui-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `timeout` | Contains timeout configuration. See [Timeout Config below](#timeout-config). | `TimeoutSetting` | |
| `logging` | Contains logging specific configuration. See [Logging Config below](#ui-logging-config) | `LoggingSetting` | |
| `screenshot` | Contains screenshot configuration. See [Screenshot Config below](#screenshot-config). | `ScreenshotSetting` | |
| `web` | Contains web platform configuration. See [Web Config below](#web-config). | `Map<String, WebSetting>` | |
| `mobile` | Contains Mobile platform configuration. See [Mobile Config below](#mobile-config). | `object` | |

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
| `highlight_delay` | Delay for element getting highlighted | `long` | `100` |

#### UI Logging Configuration {#ui-logging-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `exclude_logs` | Exclude any specific logs which is supported by Drivers | `null` |
| `path` | Path where the logs will be saved | `string` | `{root-folder}/logs` |

#### Screenshot Configuration {#screenshot-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `enabled` | Enable/disable screenshot capturing | `boolean` | `true` |
| `path` | Path to the directory where screenshots are stored | `string` | `./screenshots` |
| `extension` | Extension of the screenshot file | `string` | `jpeg` |
| `prefix` | Prefix of the screenshot file | `string` | `SCR` |

#### Web Configuration {#web-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `base_url` | Base URL to navigate to by default | `string` | `null` |
| `browser` | Browser name | [`Browser`](#supported-browsers) | `Browser.NONE` |
| `protocol` | Protocol type, will override Host provided by Target provider | [`Protocol`](#supported-protocols) | `Protocol.HTTP` |
| `host` | Remote driver host name, will override Host provided by Target provider | `string` | `null` |
| `port` | Remote driver port, if `0`, port will not be considered | `number` | `0` |
| `target` | Target provider name | [`TargetProviders`](#target-providers) | `TargetProviders.LOCAL` |
| `platform` | Platform name for the Browser | `string` | `null` |
| `user_name` | User name for cloud service provider | `string` | `null` |
| `password` | Password / Access key for cloud service provider | `string` | `null` |
| `capabilities` | Capabilities for browser | `Map<String, Object>` | `null` |
| `headless` | Headless mode for browser | `boolean` | `true` |
| `highlight` | Highlight element on interaction, if `true` | `boolean` | `false` |
| `resize` | How to resize the window initial state | [`WindowResizeType`](#window-resize-type) | `WindowResizeType.NORMAL` |
| `custom_size` | Custom window size, when `resize` option is selected as `CUSTOM` | `Dimension` | `1920x1080` |

:::info
For fields `user_name` and `password`, you can use placeholder variables in the following format:

| Input type | Variable sample |
| ---------- | --------------- |
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
| `base_path` | Base path of the server | `string` | |
| `external` | Set `true` if using already running server, else framework will start the server automatically | `boolean` | `false` |
| `session_override` | Enables session override | `boolean` | `true` |
| `node_path` | Node executable path | `string` | |
| `appium_path` | Appium executable `main.js` path | `string` | |
| `target` | Target provider name | [`TargetProviders`](#target-providers) | `TargetProviders.LOCAL` |
| `user_name` | User name for cloud service provider | `string` | `null` |
| `password` | Password / Access key for cloud service provider | `string` | `null` |
| `allow_insecure` | Allow list of features in server considered as insecure | `List<string>` | |
| `timeout` | Timeout in seconds for server to start | `int` | `30` |
| `driver` | Appium automation driver type | [`AutomationType`](#supported-automation-types) | `null` |
| `allow_cors` | If set to `true`, will enable CORS | `boolean` | `false` |
| `callback_address` | Callback IP address | `string` | `null` |
| `callback_port` | Callback Port number | `int` | `0` |
| `deny_insecure` | Set which insecure features are not allowed to run in this server's sessions | `List<string>` | `null` |
| `keep_alive_timeout` | Number of seconds the Appium server should apply as both the keep-alive timeout and the connection timeout for all requests | `int` | `0` |
| `logging` | Logging related settings | [`LogSetting`](#mobile-log-setting) | `null` |
| `plugins` | Use a single or a list of Appium plugins | `List<string>` | `null` |
| `relaxed_security` | Disable additional security checks, so it is possible to use some advanced features, provided by drivers supporting this option | `boolean` | `false` |
| `strict_capabilities` | Cause sessions to fail if desired caps are sent in that Appium does not recognize as valid for the selected device | `boolean` | `false` |
| `webhook` | Also send log output to this http listener | `string` | `null` |
| `other_args` | Any other Appium server arguments which is currently not supported by Boyka, you can use them in this setting | `Map<string, string>` | `null` |

###### Server Logging Setting {#mobile-log-setting}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `debug_spacing` | Add exaggerated spacing in logs to help with visual inspection | `boolean` | `false` |
| `level` | Log level | [`LogLevel`](#log-level) | `INFO` |
| `local_timezone` | Use local timezone for timestamps | `boolean` | `false` |
| `timestamp` | Show timestamps in console output | `boolean` | `false` |

##### Mobile Device Configuration {#device-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `name` | Device Name | `string` | |
| `os` | Device OS type | [`OS`](#supported-os) | `ANDROID` |
| `version` | Device OS version | `string` | |
| `type` | Device type | [`DeviceType`](#supported-device-types) | `VIRTUAL` |
| `capabilities` | Contains cloud specific capabilities | `Map` | |
| `application` | Contains application related configs | [`ApplicationSetting`](#app-config) | |
| `virtual_device` | Contains virtual device specific configs | [`VirtualDeviceSetting`](#avd-config) | |
| `clear_files` | Determines if system files needs to cleared after run completes on a device | `boolean` | `true` |
| `clear_logs` | Determines if device logs needs to be cleared | `boolean` | `true` |
| `full_reset` | Determines if full reset needs to be done | `boolean` | |
| `no_reset` | Determines if there should be no reset | `boolean` | |
| `accept_alerts` | Determines if auto accept alerts is needed | `boolean` | `true` |
| `ignore_unimportant_views` | Determines if unimportant views needs to be ignored | `boolean` | `true` |
| `server_install_timeout` | Timeout in seconds to wait for Appium server app to get installed | `int` | `30` |
| `server_launch_timeout` | Timeout in seconds to wait for Appium server app to start | `int` | `30` |
| `swipe` | Swipe specific setting | [`SwipeSetting`](#swipe-setting) | |
| `wda` | WebDriverAgent specific settings for iOS | [`WDASetting`](#wda-config) | |
| `typing_speed` | Max typing speed for iOS | `int` | `60` |
| `chrome_driver_port` | Chrome driver port for Android devices | `int` | `0` |
| `system_port` | System port for Android devices | `int` | `8200` |
| `unique_id` | Device UDID | `string` | `null` |

###### Device Application Configurations {#app-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `base_url` | Base URL to navigate to by default | `string` | `null` |
| `browser` | Browser name. | [`Browser`](#supported-browsers) | `null` |
| `external` | Is the app saved outside `src/test/resources` folder? | `boolean` | `false` |
| `path` | Path to the AUT, can also contain environment variables for cloud App URL | `string` | |
| `type` | Application type | [`ApplicationType`](#application-type) | `NATIVE` |
| `install_timeout` | Timeout in seconds to wait until app gets installed on device | `int` | `30` |
| `wait_activity` | Wait for the mentioned activity to load | `string` | |
| `wait_timeout` | Wait timeout in seconds to wait for AUT | `int` | `30` |
| `bundle_id` | iOS application bundle id | `string` | `null` |
| `chrome_driver_port` | Port on which Chrome driver would run | `int` | `0` |

###### Virtual Device Configurations {#avd-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `name` | Name of AVD | `string` | |
| `launch_timeout` | Timeout in seconds to wait until AVD launches | `int` | `60` |
| `headless` | Determine if required to run in headless mode | `boolean` | `false` |
| `ready_timeout` | Timeout in seconds to wait until AVD is ready | `int` | `60` |
| `connect_keyboard` | Should iOS simulator connect to hardware keyboard? | `boolean` | `false` |

##### Swipe Configuration {#swipe-setting}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `distance` | Amount of distance to swipe from the center of the screen to the edge of the screen or element | `int` | `25` |
| `max_swipe_until_found` | Maximum amount of time to swipe until an element is found on the screen | `int` | `5` |

##### WebDriverAgent Configuration {##wda-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `local_port` | This value, if specified, will be used to forward traffic from Mac host to real ios devices over USB. Default value is the same as the port number used by WDA on the device under test | `int` | `8100` |
| `use_new` | If true, forces uninstall of any existing WebDriverAgent app on device | `boolean` | `false` |
| `launch_timeout` | Timeout to wait for WebDriverAgent to be pingable | `int` | `60` |
| `startup_retries` | Number of times to try to build and launch WebDriverAgent onto the device | `int` | `2` |
| `connection_timeout` | Connection timeout to wait for a response from WebDriverAgent | `int` | `60` |
| `startup_retry_interval` | Time interval to wait between tries to build and launch WebDriverAgent | `int` | `10` |
| `use_prebuilt` | If true, uses existing WebDriverAgent app on device | `boolean` | `false` |
| `update_bundle_id` | Bundle id to update WDA to before building and launching on real devices. This bundle id must be associated with a valid provisioning profile | `string` | `null` |
| `team_id` | Apple developer team identifier string. Must be used in conjunction with xcodeSigningId to take effect. e.g., JWL241K123 | `string` | `null` |
| `signing_id` | String representing a signing certificate. Must be used in conjunction with xcodeOrgId. This is usually just iPhone Developer | `string` | `null` |

### API Configuration {#api-config}

| Property | Description | Type | Default |
| -------- | ----------- | ---- | ------- |
| `base_uri` | Base URL of the API. | `string` | |
| `base_path` | Base path of the API. | `string` | |
| `port` | Port of the API. | `number` | |
| `connection_timeout` | Connection timeout in seconds for the API. | `number` | `5` |
| `read_timeout`       | Read timeout in seconds for the API. | `number` | `5` |
| `write_timeout` | Write timeout in seconds for the API. | `number` | `5` |
| `logging` | Logging configuration. See [Logging Config below](#logging-config). | `object` | |
| `schema_path` | Path of schema file at location `src/test/resources` | `string` | |

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
- `REMOTE`: Is used for Cloud based browsers.
- `SAFARI`: Is used for Safari browser.

:::danger
Browsers are case sensitive, so make sure you provide exact names in the config.
:::

## Supported Protocols {#supported-protocols}

We have an enum `Protocol` where we maintain the list of supported protocols. They are:

- `HTTP`: Is used for HTTP protocol.
- `HTTPS`: Is used for HTTPS protocol.

## Target Providers {#target-providers}

We have an enum `TargetProviders` where we maintain the list of supported target providers. They are:

- `LOCAL`: Local provider will be used.
- `BROWSER_STACK`: Is used for BrowserStack cloud provider.
- `LAMBDA_TEST_WEB`: Is used for LambdaTest cloud provider to run on Web browsers.
- `LAMBDA_TEST_MOBILE`: Is used for LambdaTest cloud provider to run on Mobile devices.

:::tip
Host name from the settings will be used by default, if that is not provided, the host name from the `TargetProviders` name.
:::

## Supported Device OS {#supported-os}

We have an enum `OS` where we maintain the list of currently supported device OS types. They are:

- `ANDROID`
- `IOS`

## Supported device types {#supported-device-types}

- `CLOUD`
- `VIRTUAL`

## Supported Automation types {#supported-automation-types}

- `UI_AUTOMATOR`: Equivalent for `UIAutomator2` in Appium
- `XCUI`: Equivalent for `XCuiTest` in Appium

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

## Window Resize Types {#window-resize-type}

- `CUSTOM`: You can define custom window size
- `FULL_SCREEN`: Opens the window in full screen mode
- `MAXIMIZED`: Opens the window maximized (if supported by your platform)
- `MINIMIZED`: Opens the window minimized
- `NORMAL`: Opens the window in default state

## Supported Application Types {#application-type}

- `NATIVE`: Native Mobile application
- `HYBRID`: Hybrid Mobile application
- `WEB`: Web Mobile application
