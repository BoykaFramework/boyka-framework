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
  <version>0.11.0</version>
</dependency>
```

## Add as Gradle dependency

You can start by adding the following dependency to your `build.gradle` file:

```groovy title=build.gradle
compile "com.github.wasiqb.boyka:boyka-framework:0.11.0"
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
- Web:
  - [How to configure Boyka for Web Automation?](/docs/guides/web/setup-config)
  - [How to create page object?](/docs/guides/web/create-page-object)
  - [How to write test using the page object?](/docs/guides/web/write-test)
