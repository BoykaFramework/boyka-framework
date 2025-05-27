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
  <a href="https://dub.sh/boyka-discord">
    <img src="https://img.shields.io/discord/950985052769120337?label=Discord&logo=Discord&style=for-the-badge" alt="Join Discord">
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/graphs/contributors">
    <img src="https://img.shields.io/github/contributors/BoykaFramework/boyka-framework?style=for-the-badge" alt="contributors" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/commits/main">
    <img src="https://img.shields.io/github/last-commit/BoykaFramework/boyka-framework?style=for-the-badge" alt="last update" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/releases/latest">
    <img src="https://img.shields.io/github/downloads/BoykaFramework/boyka-framework/latest/total?color=brightgreen&label=Downloads%20for%20latest&logo=GitHub&style=for-the-badge" alt="GitHub releases" />
  </a>
  <a href="https://central.sonatype.com/artifact/io.github.boykaframework/boyka-framework">
    <img src="https://img.shields.io/maven-central/v/io.github.boykaframework/boyka-framework.svg?style=for-the-badge" alt="Maven Central" />
  </a>
  <a href="https://github.com/BoykaFramework/boyka-framework/blob/master/LICENSE">
    <img src="https://img.shields.io/github/license/BoykaFramework/boyka-framework.svg?style=for-the-badge" alt="license" />
  </a>
</p>

  <h4>
    <a href="https://boykaframework.github.io/boyka-framework/docs/intro">Documentation</a>
  <span> | </span>
    <a href="https://github.com/BoykaFramework/boyka-framework/issues/new/choose">Report Bug</a>
  <span> | </span>
    <a href="https://github.com/BoykaFramework/boyka-framework/issues/new/choose">Request Feature</a>
  </h4>
</div>

<br />

## 🎯 Features

### Browsers

| OS | Windows | MacOS | Linux | Android | iOS |
| -- | ------- | ----- | ----- | ------- | --- |
| Chrome | ✅ | ✅ | ✅ | ✅ |  |
| Firefox | ✅ | ✅ | ✅ |  |  |
| Safari |  | ✅ |  |  | ✅ |
| Edge | ✅ | ✅ | ✅ |  |  |

### Mobile applications

| OS | Android | iOS |
| -- | ------- | --- |
| Native | ✅ | ✅ |
| Hybrid | ✅ | ✅ |
| Web | ✅ | ✅ |

### Desktop applications

| OS | Supported |
| -- | ------- |
| MacOS | ✅ |
| Windows |  |
| Linux |  |

### Remote platforms

| Platform | Status |
| -------- | ------ |
| BrowserStack | ✅ |
| LambdaTest | ✅ |
| Selenium Grid | ✅ |

### Test frameworks

| Framework | Java |
| -------- | --- |
| JUnit 5 | ✅ |
| TestNG | ✅ |
| Cucumber | ✅ |

### Other features

| Feature | Status |
| ------- | ------ |
| Zero boilerplate code | ✅ |
| Page Object Model | ✅ |
| Request object model | ✅ |
| Single configuration | ✅ |
| Page actions | ✅ |
| Multi-user multi-platform | ✅ |
| Take screenshots | ✅ |
| Video recording (Mobile) | ✅ |
| Logging | ✅ |
| Extension support | ✅ |
| Inline assertion | ✅ |
| API response schema validation | ✅ |

## 🖱️ One command Boyka project setup

Install [Boyka command line assistant](https://github.com/BoykaFramework/boyka-cli) by running the following command:

```shell
npm i -g @boykaframework/boyka-cli
```

Once it is installed, run the following command to setup Boyka project:

```shell
boyka init [project-name]
```

When this command is executed, multiple set of questions will be prompted that must be answered to setup the project. By default, the Boyka project uses Java 17 as programming language and Maven as the build tool.

> [!TIP]
> Boyka CLI assistant will allow setting up the project quickly within seconds.

## 👜 Resources

- [🤔 What is Boyka Framework?](https://boykaframework.github.io/boyka-framework/docs/intro#what-is-boyka-framework)
- [💡 Why Boyka Framework was created?](https://boykaframework.github.io/boyka-framework/docs/intro#why-boyka-framework-was-created)
- [🎯 Features](https://boykaframework.github.io/boyka-framework/docs/intro#features)
- [🚀 Quick start](https://boykaframework.github.io/boyka-framework/docs/getting-started/quickstart)

## ☕ Examples

- [Create your first Boyka framework project](https://boykaframework.github.io/boyka-framework/docs/guides/create-first-project)
- API:
  - [How to configure Boyka for API Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/api/setup-config)
  - [How to compose a request?](https://boykaframework.github.io/boyka-framework/docs/guides/api/compose-request)
  - [How to execute a request?](https://boykaframework.github.io/boyka-framework/docs/guides/api/execute-request)
  - [How to verify the response?](https://boykaframework.github.io/boyka-framework/docs/guides/api/verify-response)
  - [How to verify the response schema?](https://boykaframework.github.io/boyka-framework/docs/guides/api/verify-response-schema)
- UI:
  - Web:
    - [How to configure Boyka for Web Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/web/setup-config)
    - [How to create page object for Web?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/web/create-page-object)
  - Android:
    - [How to configure Boyka for Android Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/android/setup-config)
    - [How to update existing page object with Android locators?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/android/create-page-object)
  - iOS:
    - [How to configure Boyka for iOS Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/ios/setup-config)
    - [How to update existing page object for iOS locators?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/ios/create-page-object)
  - [How to create common application action class?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/page-actions)
  - [How to write test class using common action class?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/write-test)
  - MacOS:
    - [How to configure Boyka for MacOS Automation?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/mac/setup-config)
    - [How to create page object for MacOS?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/mac/create-page-object)
    - [How to write page actions for MacOS?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/mac/page-action)
    - [How to write test class for MacOS?](https://boykaframework.github.io/boyka-framework/docs/guides/ui/mac/write-test)

## 👨‍🎓 Tutorials

- [Appium with Selenium Grid 4](https://boykaframework.github.io/boyka-framework/docs/tutorials/appium-grid)
- [How to automate i18n and l10n?](https://boykaframework.github.io/boyka-framework/docs/tutorials/i18n-l10n)
- [Generate Allure Report](https://boykaframework.github.io/boyka-framework/docs/tutorials/generate-allure-report)
- [Data Driven Testing](https://boykaframework.github.io/boyka-framework/docs/tutorials/data-driven-testing)

## 👾 Tech Stack

### 🏘️ Boyka Framework

- Java 17
- Maven
- IntelliJ IDEA Ultimate
- SonarCloud
- CheckStyles
- Appium Java Client
- Selenium WebDriver
- OkHttp3

### 💻 Main project and Website

- TypeScript
- ESLint
- Prettier
- Commit lint
- Lerna Changelog
- Release-it
- Husky
- Docusaurus v3.x
- GitHub Actions

### 💎 Open source supporters

Big thanks to the following organizations for their support to the project with their open source licenses:

<div align="center">
  <a href="http://www.lambdatest.com?fp_ref=wasiq95" target="_blank" style="outline:none;border:none;">
    <picture>
      <source media="(prefers-color-scheme: dark)" srcset="./website/static/img/docs/community/our-supporters/lambdatest-dark.png">
      <source media="(prefers-color-scheme: light)" srcset="./website/static/img/docs/community/our-supporters/lambdatest-light.png">
      <img alt="lambdatest" src="./website/static/img/docs/community/our-supporters/lambdatest-light.png">
    </picture>
  </a>
  <br/>
  <a href="https://www.browserstack.com/" target="_blank" style="outline:none;border:none;">
    <img src="./website/static/img/docs/community/our-supporters/browser-stack.png" alt="browserstack"/>
  </a>
  <br/>
  <a href="https://www.jetbrains.com/" target="_blank" style="outline:none;border:none;">
    <picture>
      <source media="(prefers-color-scheme: dark)" srcset="./website/static/img/docs/community/our-supporters/jetbrains-white.png">
      <source media="(prefers-color-scheme: light)" srcset="./website/static/img/docs/community/our-supporters/jetbrains-black.png">
      <img alt="JetBrains" src="./website/static/img/docs/community/our-supporters/jetbrains-black.png">
    </picture>
  </a>
  <br/>
  <a href="https://tuple.app/" target="_blank" style="outline:none;border:none;">
    <img src="./website/static/img/docs/community/our-supporters/tuple.svg" alt="Tuple" width="300px"/>
  </a>
</div>

## 🧭 Project Road-map

Check out our road map to know which features we are cooking,

- [Project Road-map](https://github.com/orgs/BoykaFramework/projects/4/views/1)
- [Current Milestone](https://github.com/orgs/BoykaFramework/projects/4/views/2)
- [Next Milestone](https://github.com/orgs/BoykaFramework/projects/4/views/3)
- [Future planned features](https://github.com/orgs/BoykaFramework/projects/4/views/4)

## 👋 Contributing

These are our awesome contributors:

[![Repo contributors](https://contrib.rocks/image?repo=BoykaFramework/boyka-framework)](https://github.com/BoykaFramework/boyka-framework/graphs/contributors)

Contributions are always welcome!

Check out [`contributing.md`](./.github/CONTRIBUTING.md) for ways to get started.

## 📜 Code of Conduct

Please read the [Code of Conduct](./.github/CODE_OF_CONDUCT.md)

## ⚠️ License

Distributed under MIT [License](LICENSE).

## 🤝 Contact

- Join our [Discord server](https://dub.sh/boyka-discord) to discuss anything about the framework
- Open a [new Discussion](https://github.com/BoykaFramework/boyka-framework/discussions/new) on GitHub to ask questions or to discuss ideas
- Connect with us on [X](https://dub.sh/boyka-twitter)
- Follow us on [GitHub](https://git.new/boyka-github) and [LinkedIn](https://dub.sh/boyka-linkedin)

## 💗 Repo Activity

![Boyka Framework Repo activity](https://repobeats.axiom.co/api/embed/c0102808cee0d0c66a005dc18f95d3ef35a844a3.svg "Repobeats analytics image")

## ⭐ Star History

[![Star History Chart](https://api.star-history.com/svg?repos=BoykaFramework/boyka-framework&type=Timeline)](https://star-history.com/#BoykaFramework/boyka-framework&Timeline)
