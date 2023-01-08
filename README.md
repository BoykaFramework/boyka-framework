<div align="center">

  <img src="assets/Boyka.png" alt="logo" width="200" height="auto" />

  <h2>
    🎉 Ultimate test automation for testing any application on any platform
  </h2>
  <h3>
    Don't forget to ⭐ the repository if you like it!
  </h3>

<!-- Badges -->
<p>

[![Product Hunt](https://api.producthunt.com/widgets/embed-image/v1/featured.svg?post_id=352770&theme=light)](https://www.producthunt.com/posts/boyka-framework?utm_source=badge-featured&utm_medium=badge&utm_souce=badge-boyka&#0045;framework)

[![Open in GitPod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/BoykaFramework/boyka-framework)

  <a href="https://discord.gg/dUg8K9DAsR">
    <img src="https://img.shields.io/discord/950985052769120337?label=Discord&logo=Discord&style=for-the-badge" alt="Join Discord">
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/BoykaFramework/boyka-framework?style=for-the-badge" alt="contributors" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/commits/main">
    <img src="https://img.shields.io/github/last-commit/BoykaFramework/boyka-framework?style=for-the-badge" alt="last update" />
  </a>
  <a href="https://mvnrepository.com/artifact/com.github.wasiqb.boyka/boyka-framework">
    <img src="https://img.shields.io/maven-central/v/com.github.wasiqb.boyka/boyka-framework.svg?style=for-the-badge" alt="Maven Central" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/releases/tag/v0.11.0">
    <img src="https://img.shields.io/github/downloads/BoykaFramework/boyka-framework/v0.11.0/total?color=brightgreen&label=Downloads%20for%20v0.11.0&logo=GitHub&style=for-the-badge" alt="GitHub releases" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/BoykaFramework/boyka-framework.svg?style=for-the-badge" alt="license" />
  </a>
</p>

  <h4>
    <a href="https://BoykaFramework.github.io/boyka-framework/docs/intro">Documentation</a>
  <span> | </span>
    <a href="https://github.com/BoykaFramework/boyka-framework/issues/new/choose">Report Bug</a>
  <span> | </span>
    <a href="https://github.com/BoykaFramework/boyka-framework/issues/new/choose">Request Feature</a>
  </h4>
</div>

<br />

## 🤔 Why was Boyka-framework created?

In my career having vast experience in automating API, Web browsers and Mobile apps, I have seen that people had to use different frameworks for automating API, Web and Mobile applications which created a lot of chaos with respect to maintenance of dependencies and their respective code for test automation.

Also, I never came across a test automation framework which allowed us to write automation test script without any project specific boilerplate code or a mini framework.

In addition to this, there was learning curve involved for learning those individual frameworks which slowed down the team to write automation and thus increased overall automation debt.

This all gave me an idea of having a single framework which could solve all the above mentioned problems and help the QA's to keep the pace up with writing test scripts and reduce the automation debt.

## 🎯 Features

- ✅ Zero boilerplate code
- ✅ Support Rest API automation with schema validations and response body verification
- ✅ Supports Web browser automation with support for Chrome, Edge, Firefox and Safari.
- ✅ Supports Android native apps automation
- ✅ Supports execution of Web tests on cloud platforms like BrowserStack and LambdaTest.
- ✅ Highly configurable via `boyka-config.json`
- ✅ Micro logging to log events of the test execution
- ✅ Supports taking screenshots

## ⏱️ Coming soon

Following are the awesome features which will be implemented soon to the frameworks:

- Support for iOS automation
- Support for GraphQL and SOAP API automation
- Support video recording of the tests for Web and Mobile platforms
- Support for more cloud platforms.
- Many many more...

## 👀 Usage

Use this space to tell a little more about your project and how it can be used. Show additional screenshots, code samples, demos or link to other resources.

```xml
<dependency>
  <groupId>com.github.wasiqb.boyka</groupId>
  <artifactId>boyka-framework</artifactId>
  <version>0.11.0</version>
</dependency>
```

## 🤓 Sample Code snippets

<details>
  <summary>🛠️ Boyka Config file</summary>

This is the configuration file for Boyka Framework named `boyka-config.json` stored at `src/test/resources` folder.

```json
{
  "ui": {
    "timeout": {
      "implicit_wait": 10,
      "explicit_wait": 10,
      "page_load_timeout": 30,
      "script_timeout": 10,
      "highlight_delay": 100
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
        "browser": "CHROME",
        "highlight": true,
        "headless": false,
        "resize": "CUSTOM"
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
        "host": "localhost",
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
    "mobile": {
      "test_local_sauce_android": {
        "server": {
          "protocol": "HTTP",
          "host": "127.0.0.1",
          "port": 4723,
          "base_path": "/wd/hub",
          "session_override": true,
          "allow_insecure": [
            "get_server_logs"
          ]
        },
        "device": {
          "os": "ANDROID",
          "version": "11",
          "name": "Pixel_6_Pro",
          "automation": "UI_AUTOMATOR",
          "type": "VIRTUAL",
          "server_install_timeout": 60,
          "server_launch_timeout": 60,
          "ignore_unimportant_views": true,
          "swipe": {
            "distance": 25,
            "max_swipe_until_found": 5
          },
          "application": {
            "path": "/apps/android/saucedemo.apk",
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "install_timeout": 180
          },
          "avd": {
            "name": "Pixel_6_Pro",
            "headless": false
          }
        }
      },
      "test_bs_android": {
        "server": {
          "cloud": "BROWSER_STACK",
          "protocol": "HTTPS",
          "host": "hub-cloud.browserstack.com",
          "user_name": "${env:BS_USER}",
          "password": "${env:BS_KEY}",
          "base_path": "/wd/hub"
        },
        "device": {
          "os": "ANDROID",
          "version": "11.0",
          "name": "Google Pixel 5",
          "automation": "UI_AUTOMATOR",
          "type": "CLOUD",
          "ignore_unimportant_views": true,
          "application": {
            "path": "AndroidApp",
            "external": true,
            "wait_activity": "com.swaglabsmobileapp.MainActivity",
            "install_timeout": 180
          },
          "capabilities": {
            "projectName": "BrowserStack Android Project",
            "buildName": "Test BrowserStack Build",
            "sessionName": "Test BrowserStack Session",
            "appiumVersion": "2.0.0",
            "deviceLogs": true,
            "networkLogs": true,
            "debug": true,
            "video": true,
            "appiumLogs": true
          }
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
      "schema_path": "schema/",
      "logging": {
        "request": true,
        "response": true
      }
    }
  }
}
```

</details>

<details>
  <summary>🪢 API Sample</summary>

Add your response schema JSON files at the directory mentioned in config under `src/test/resources` folder.

```bash
-| /src
 |__ /test
   |__ /resources
     |__ /schemas  # This folder path mentioned in config file.
       |__ create-user-schema.json
```

Here's how you can execute the API test and also verify its response.

```java
// Create request body object
final User user = User.createUser ()
  .name ("Wasiq")
  .job ("Software Engineer")
  .create ();

// Compose request
final ApiRequest request = ApiRequest.createRequest ()
  .configKey (API_CONFIG_KEY)
  .method (POST)
  .path ("/users")
  .bodyObject (user)
  .create ();

// Execute request
final ApiResponse response = ApiManager.execute (request);

// Verify response status code
response.verifyStatusCode ()
  .isEqualTo (201);

// Verify response schema
response.verifySchema ("create-user-schema.json");

// Verify response body
response.verifyTextField ("id")
  .isNotNull ();
response.verifyTextField ("name")
  .isEqualTo (user.getName ());
response.verifyTextField ("job")
  .isEqualTo (user.getJob ());
response.verifyTextField ("createdAt")
  .isNotNull ();
```

</details>

<details>
  <summary>💻 Common Page Object for Web and Android</summary>

This is how you can create a common page object for both Web and Android.

```java
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

@Getter
public class LoginPage {
  private static final LoginPage LOGIN_PAGE = new LoginPage ();

  public static LoginPage loginPage () {
    return LOGIN_PAGE;
  }

  private final Locator loginBox = Locator.buildLocator ()
    .web (By.id ("login_button_container"))
    .android (AppiumBy.accessibilityId ("test-Login"))
    .name ("Login Box")
    .build ();
  private final Locator loginButton = Locator.buildLocator ()
    .web (By.id ("login-button"))
    .android (AppiumBy.accessibilityId ("test-LOGIN"))
    .name ("Login Button")
    .parent (this.loginBox)
    .build ();
  private final Locator password = Locator.buildLocator ()
    .web (By.id ("password"))
    .android (AppiumBy.accessibilityId ("test-Password"))
    .name ("Password")
    .parent (this.loginBox)
    .build ();
  private final Locator username = Locator.buildLocator ()
    .web (By.id ("user-name"))
    .android (AppiumBy.accessibilityId ("test-Username"))
    .name ("User Name")
    .parent (this.loginBox)
    .build ();

  private LoginPage () {
    // Avoid explicit class initialization.
  }
}
```

</details>

<details>
  <summary>✅ Common Test flow for Web and Android</summary>

This is how you can write common actions class for Web and Android together for the app which has similar flows on both the platforms.

```java
import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
import static com.github.wasiqb.boyka.actions.KeyboardActions.enterText;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserTitle;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementDisplayed;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyElementEnabled;
import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.testng.ui.saucedemo.pages.LoginPage.loginPage;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.enums.PlatformType;

public class SauceDemoActions {
  private static final String URL = "https://www.saucedemo.com";

  private final PlatformType platformType;

  public SauceDemoActions () {
    this.platformType = getSession ().getPlatformType ();
  }

  public void verifyLogin (final String userName, final String password) {
    verifyNavigateToSite ();
    enterText (loginPage ().getUsername (), userName);
    enterText (loginPage ().getPassword (), password);
    clickOn (loginPage ().getLoginButton ());
    verifyLoggedIn ();
  }

  private void verifyNavigateToSite () {
    if (this.platformType == WEB) {
      navigate ().to (URL);
      verifyBrowserUrl ().startsWith (URL);
    }
  }

  private void verifyLoggedIn () {
    if (this.platformType == WEB) {
      verifyBrowserUrl ().isEqualTo (format ("{0}/inventory.html", URL));
      verifyBrowserTitle ().isEqualTo ("Swag Labs");
    }
    verifyElementDisplayed (homePage ().getMenuButton ()).isTrue ();
    verifyElementEnabled (homePage ().getMenuButton ()).isTrue ();
  }
}
```

Now, you can use this actions class in your test as shown below:

```java
package com.github.wasiqb.boyka.testng.ui.saucedemo;

import static com.github.wasiqb.boyka.actions.DriverActions.saveLogs;
import static com.github.wasiqb.boyka.actions.DriverActions.takeScreenshot;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.enums.PlatformType;
import com.github.wasiqb.boyka.testng.ui.saucedemo.actions.SauceDemoActions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SauceDemoTest {
  private SauceDemoActions sauceDemo;

  @AfterMethod (alwaysRun = true)
  public void afterMethod () {
    takeScreenshot ();
  }

  @BeforeClass (description = "Setup test class", alwaysRun = true)
  @Parameters ({ "platformType", "driverKey" })
  public void setupTestClass (final PlatformType platformType, final String driverKey) {
    createDriver (platformType, driverKey);
    this.sauceDemo = new SauceDemoActions ();
  }

  @AfterClass (description = "Tear down test class", alwaysRun = true)
  public void tearDownTestClass () {
    saveLogs ();
    closeDriver ();
  }

  @Test (description = "Test login functionality")
  public void testLogin () {
    this.sauceDemo.verifyLogin ("standard_user", "secret_sauce");
  }
}
```

</details>

## ☕ Examples

- API:
  - [How to configure Boyka for API Automation?](https://BoykaFramework.github.io/boyka-framework/docs/guides/api/setup-config)
  - [How to compose a request?](https://BoykaFramework.github.io/boyka-framework/docs/guides/api/compose-request)
  - [How to execute a request?](https://BoykaFramework.github.io/boyka-framework/docs/guides/api/execute-request)
  - [How to verify the response?](https://BoykaFramework.github.io/boyka-framework/docs/guides/api/verify-response)
- Web:
  - [How to configure Boyka for Web Automation?](https://BoykaFramework.github.io/boyka-framework/docs/guides/web/setup-config)
  - [How to create page object?](https://BoykaFramework.github.io/boyka-framework/docs/guides/web/create-page-object)
  - [How to write test using the page object?](https://BoykaFramework.github.io/boyka-framework/docs/guides/web/write-test)
- Mobile:
  - [How to configure Boyka for Web Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/mobile/setup-config)
  - [How to create page object?](https://boykaframework.github.io/boyka-framework/docs/guides/mobile/create-page-object)
  - [How to write test using the page object?](https://boykaframework.github.io/boyka-framework/docs/guides/mobile/write-test)

## 👾 Tech Stack

### 🏘️ Boyka Framework

<div>
  <img
    src="assets/boyka-core-tech-stack.png"
    alt="Boyka core tech stack" width="400" height="auto" />
</div>

### 💻 Main project and Website

<div>
  <img
    src="assets/boyka-main-tech-stack.png"
    alt="Boyka Main project tech stack" width="400" height="auto" />
</div>

### 💎 Cloud platform supporters

Big thanks to the following organizations for their support to the project with their open source licenses:

<div align="center">
  <a href="http://www.lambdatest.com?fp_ref=wasiq95" target="_blank" style="outline:none;border:none;"><img src="https://d2gdx5nv84sdx2.cloudfront.net/uploads/n3ufe5o3/marketing_asset/banner/6476/728_x_90.png" alt="lambdatest"/></a>
  <br/>
  <a href="http://www.browserstack.com" target="_blank" style="outline:none;border:none;"><img src="./website/static/img/docs/community/our-supporters/browser-stack.png" alt="browserstack"/></a>
</div>

## 🧭 Project Road-map

Check out our road map to know which features we are cooking,

- [Project Road-map](https://github.com/orgs/BoykaFramework/projects/4/views/1)
- [Current Milestone](https://github.com/orgs/BoykaFramework/projects/4/views/2)
- [Next Milestone](https://github.com/orgs/BoykaFramework/projects/4/views/3)
- [Future planned features](https://github.com/orgs/BoykaFramework/projects/4/views/4)

## 👋 Contributing

These are our awesome contributors:

[![Contributors](https://contrib.rocks/image?repo=BoykaFramework/boyka-framework)](https://github.com/BoykaFramework/boyka-framework/graphs/contributors)

Contributions are always welcome!

Check out [`contributing.md`](./.github/CONTRIBUTING.md) for ways to get started.

## 📜 Code of Conduct

Please read the [Code of Conduct](./.github/CODE_OF_CONDUCT.md)

## ⚠️ License

Distributed under MIT [License](LICENSE).

## 🤝 Contact

- Join our [Discord server](https://discord.gg/dUg8K9DAsR) to discuss anything about the framework
- Open a [new Discussion](https://github.com/BoykaFramework/boyka-framework/discussions/new) on GitHub to ask questions or to discuss ideas
- Connect with me on [my Linktree](https://linktr.ee/BoykaFramework)

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=BoykaFramework/boyka-framework&type=Timeline)](https://star-history.com/#BoykaFramework/boyka-framework&Timeline)
