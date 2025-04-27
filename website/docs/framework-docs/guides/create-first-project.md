---
sidebar_position: 2
title: 1️⃣ Create your first Boyka Framework project
---

## Install Boyka Framework command line interface (CLI)

To create a new Boyka project, you need to install the Boyka CLI. You can do this by running the following command:

```bash
npm install -g @boykaframework/boyka-cli
```

## Create a new Boyka project

Once you have installed the Boyka CLI, you can create a new Boyka project by running the following command:

```bash
boyka init my-project
```

Checkout the screenshot below for a visual representation of the command:

![Boyka CLI](/img/docs/framework-docs/tutorial/create-first-project/boyka-init.png)

:::info
This is just a sample command where I have selected `UI` as the platform and `Web` as the type. **You can select any platform and type based on your requirements.**
:::

This will create a new directory called `my-project` with the basic structure of a Boyka project.

Answer the questions asked by the CLI to set up your project. The CLI will create a new directory with the name you provided and generate the necessary files and folders for your Boyka project.

You can replace `my-project` with the name of your project.

## Open the project in IntelliJ IDEA

After the project is created, you can open it in your favorite IDE. For this tutorial, we will use IntelliJ IDEA.

1. Open IntelliJ IDEA.
2. Click on `Open` in the welcome screen.
3. Navigate to the directory where you created your Boyka project and select it.
4. Click `OK` to open the project.
5. IntelliJ IDEA will automatically detect the project structure and set up the necessary configurations.
6. You need to install Lombok plugin in IntelliJ IDEA to use the Boyka Framework. You can do this by going to `File` > `Settings` > `Plugins` and searching for `Lombok`. Install the plugin and restart IntelliJ IDEA.
7. Now, build the project by going to `Build` > `Build Project` or by pressing `Ctrl + F9`. This will compile the project and generate the necessary files.
8. When prompted, select `Enable annotation processing` to enable Lombok support in your project.

## What is included in the Sample test suite

The Boyka project comes with a sample test suite located in the `test-suites` directory. This test suite includes a sample test case that demonstrates how to use the Boyka Framework for testing.

### Sample Web test case

The sample test case automates the login page of [The Internet Herokuapp](https://the-internet.herokuapp.com/login) website. It demonstrates how to use the Boyka Framework to interact with web elements, perform actions, and verify results.

### Sample API test case

The sample API test case automates the [Restful Bookstore API](https://restful-booker.herokuapp.com/apidoc/index.html). It demonstrates how to use the Boyka Framework to send HTTP requests, handle responses, and perform assertions.

### Sample Mobile test case

The sample mobile test case automates the [WebDriverIO demo app](https://github.com/webdriverio/native-demo-app). It demonstrates how to use the Boyka Framework to interact with mobile elements, both Android and iOS, perform actions, and verify results.

## Run the project

To run the Boyka project, you can right-click on the sample suite xml file (e.g., `test-suites/sample.xml`) and select `Run 'sample'`. This will execute the test suite and display the results in the console.
