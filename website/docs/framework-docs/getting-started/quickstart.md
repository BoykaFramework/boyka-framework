---
sidebar_position: 1
title: 🚀 Quick start
---

## 🤖 Using automated approach

### 🚩 Pre-requisites

Following are the pre-requisites for getting started via automated approach:

- **Node JS LTS**: Download the latest v18 LTS from [here][node].
- **Java**: Download the latest Java JDK v17 from [here][jdk17].
- **Maven**: Download the latest Maven distribution from [here][maven-download] and follow the installation guide provided [here][maven].

### ⬇️ Install Boyka CLI

To quickly get started, run the following command on the terminal to install the Boyka command line tool:

```shell
npm i -g @boykaframework/boyka-cli
```

### ✨ Create the Boyka project

Once you have installed the Boyka command line assistant, run the following command on your terminal to create a minimalist Maven project:

```shell
boyka init [project-name]
```

Here, you can give any project name of your choosing.

When you execute this command, you will be asked a set of questions, based on which, the Boyka project will be setup.

:::tip
Read the questions very carefully and understand it, then reply with your answers
:::

A new folder of the project name which you provided to the command will be created which you can open in any IDE of your choosing.

:::info
It is recommended to use IntelliJ IDEA for writing your test.
:::

When you open the newly created project in IntelliJ, first do Maven refresh in the Maven Tab, Then build the project from the `Build` menu, when prompted to `Enable Lombok Annotation processing`, click on that button. That's it!

:::tip
If you had selected to generate sample tests option in the `boyka init` command execution, then you can refer to the tests, how it is structured and what methods are being used, and create your own tests.
:::

## 💪 Using manual approach

### ✨ Create Maven project

Use any IDE of your choosing and create a new Java Maven project using JDK 17.

You can also enforce the Java version in `pom.xml` file as shown below.

```xml title="pom.xml"
. . .
<properties>
  . . .
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
  . . .
</properties>
```

### ⬇️ Add Dependency

Add the following dependency to your `pom.xml` file:

```xml title="pom.xml"
<dependency>
  <groupId>io.github.boykaframework</groupId>
  <artifactId>boyka-framework</artifactId>
  <version>2.1.0</version>
</dependency>
```

[jdk17]: https://adoptium.net/temurin/releases/
[maven]: https://maven.apache.org/install.html
[maven-download]: https://maven.apache.org/download.cgi
[node]: https://nodejs.org/en/download/package-manager
