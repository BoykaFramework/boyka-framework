---
sidebar_position: 3
title: 👨‍🦼 Usage
---

## Add as Maven dependency

You can start by adding the following dependency to your `pom.xml` file:

```xml title=pom.xml
<dependency>
  <groupId>com.github.wasiqb.boyka</groupId>
  <artifactId>boyka-framework</artifactId>
  <version>0.19.0</version>
</dependency>
```

## Add as Gradle dependency

You can start by adding the following dependency to your `build.gradle` file:

```groovy title=build.gradle
compile "com.github.wasiqb.boyka:boyka-framework:0.19.0"
```

## ⬇️ Download the jar

You can also download the JAR files from the [GitHub Releases page][release].

After downloading the jar, you can add it to your class path.

[release]: https://github.com/BoykaFramework/boyka-framework/releases/latest

## ☕ Examples

- API:
  - [How to configure Boyka for API Automation?](/docs/guides/api/setup-config)
  - [How to compose a request?](/docs/guides/api/compose-request)
  - [How to execute a request?](/docs/guides/api/execute-request)
  - [How to verify the response?](/docs/guides/api/verify-response)
- UI:
  - Web:
    - [How to configure Boyka for Web Automation?](/docs/guides/ui/web/setup-config)
    - [How to create page object for Web?](/docs/guides/ui/web/create-page-object)
  - Android:
    - [How to configure Boyka for Android Automation?](/docs/guides/ui/android/setup-config)
    - [How to update existing page object with Android locators?](/docs/guides/ui/android/create-page-object)
  - iOS:
    - [How to configure Boyka for iOS Automation?](/docs/guides/ui/ios/setup-config)
    - [How to update existing page object for iOS locators?](/docs/guides/ui/ios/create-page-object)
  - [How to create common application action class?](/docs/guides/ui/page-action)
  - [How to write test class using common action class?](/docs/guides/ui/write-test)
