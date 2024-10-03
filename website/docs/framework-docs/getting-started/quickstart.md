---
sidebar_position: 1
title: 🚀 Quick start
---

## 🤖 Using automated approach (Recommended)

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
While answering the questions, make sure you read it carefully and reply accordingly to your requirement.
:::

A new folder of the project name which you provided to the command will be created which you can open in any IDE of your choosing.

:::info
It is recommended to use IntelliJ IDEA for writing your test.
:::

Open the newly created project in IntelliJ IDE and do a Maven refresh using the `Maven Tab`. Next, build the project using the `Build menu`, when prompted `Enable Lombok Annotation Processing`, click to enable it.

:::tip
If the project is set using Boyka CLI assistant and the sample tests have been generated, refer those tests for code walkthrough and write your own tests using it as an example.
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

:::info
Use the latest version from [Maven Central][maven-central] and add it to your `pom.xml`
:::

[jdk17]: https://adoptium.net/temurin/releases/
[maven]: https://maven.apache.org/install.html
[maven-download]: https://maven.apache.org/download.cgi
[node]: https://nodejs.org/en/download/package-manager
[maven-central]: https://mvnrepository.com/artifact/io.github.boykaframework/boyka-framework
