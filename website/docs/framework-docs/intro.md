---
sidebar_position: 1
---

# Introduction

Welcome to Boyka Framework!

Boyka framework is an ultimate test automation framework, which handles all the platforms in a single framework, like API, Web, Android and iOS applications.

## Background

In the past, I worked on Web, API and Mobile automation testing, all of which required to write custom framework for every project. There was no perfect framework which we could directly add dependency and start writing tests.

To solve this problem, I created [coteafs-appium][1] framework for Android and iOS applications, [coteafs-selenium][2] framework for Web applications and [coteafs-services][3] for API.

Out of these 3 framework, first 2 got very popular. But the problem I saw in it which no one else noticed was,

- It was not easy to understand for novice.
- Coding style was not consistent.
- We cannot use same page object for Web, Android and iOS platforms.
- Getting new contributors was not possible because the framework was very complex.
- To Automate all the platforms, we must add 3 separate dependencies.

## Why Boyka Framework?

After looking at the mistakes I made in my previous frameworks, I created Boyka Framework where I tried to address all the shortcomings from my earlier frameworks.

### Features

- With Boyka, you can now create single page object for all the platforms.
- Use consistent coding style across your project.
- Start writing tests right from day 0 on your new project without writing a single line of boilerplate code.

## But why create yet another framework?

Although there are many frameworks in open source community, I found that almost all the frameworks required to write some boilerplate code in order to get started with a new project.

Boyka was created with the aim to simplify writing tests on any project you may work on. That means, no need to write even a single line of boilerplate code, just write your tests and run them.

Boyka also helps in writing simple page objects for your application where you can specify locators for Web, Android and iOS applications, and it will automatically use the appropriate locator when running your tests.

In addition, Boyka also helps with writing tests for API applications, which means you can write tests for any API endpoint.

:::info
Boyka framework is in it's early stages of development. We will add a proper comparison page with other popular frameworks in the future on this site.
:::

[1]: https://github.com/WasiqB/coteafs-appium
[2]: https://github.com/WasiqB/coteafs-selenium
[3]: https://github.com/WasiqB/coteafs-services
