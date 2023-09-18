---
title: 🏗️ Building the project
sidebar_position: 2
---

## Cloning the Project

The first step to take before you start contributing to this project is to Clone the project.

Run the following command if you want to clone using `SSH`:

```shell
> git clone git@github.com:BoykaFramework/boyka-framework.git
```

Run the following command if you want to clone using `HTTPS`:

```shell
> git clone https://github.com/BoykaFramework/boyka-framework.git
```

Run the following command if you want to clone using [Github CLI][github_cli]:

```shell
> git clone gh repo clone BoykaFramework/boyka-framework
```

The above URLs for `SSH`, `HTTPS` and `Github CLI` can be found on the github repo main page as well.

![Github repo main page](/img/docs/contributing/gh-repo-main-page.png)

## Setting up the project

To build the project, `pnpm` is required to be installed on your machine.

On Mac OS, run the following command:

```shell
> brew install pnpm
```

On Windows and Linux, you can run the following command:

```shell
> npm install -g pnpm
```

Once the installation is complete you can check by running the following command to verify that `pnpm` was installed correctly on your machine:

```shell
> pnpm --version
```

Now, execute the following command to set up the project:

```shell
> cd boyka-framework
> pnpm i
```

This step is mandatory before you start contributing to the project, because it will setup pre-commit hooks to automatically run the lint checks and test coverage check for the code before you can commit.

## Core framework project

The core framework project is located in `core-java/` directory.

### Install Maven

`Maven` is the build tool we would be using for this project. So need to have Maven installed on your machine before you proceed to build the project.

:::danger Minimum Maven version check
Minimum version for Maven `3.8.0` is required, else, project will **not build**
:::

In case Maven is not installed on your machine, the following steps should help.

#### Installing Maven on Windows

1. Download the Maven Zip File from the maven download page and extract it.
1. Add `MAVEN_HOME` System Variable.
   1. Open the Start menu and search for environment variables. Click on the Edit System environment variables result.
   1. Under the Advanced tab in the System Properties window, click Environment Variables.  
      ![System Properties Tab](/img/docs/contributing/system-properties-window.png)
   1. Click the New button under the System variables section to add a new system environment variable.
   1. Enter `MAVEN_HOME` as the variable name and the path to the Maven directory as the variable value. Click OK to save the new system variable.
      ![New System Variable Dialog box](/img/docs/contributing/new-system-variable.png)
1. Add `MAVEN_HOME` directory in the PATH Variable.
   1. Select the Path variable under the System variables section in the Environment Variables window. Click the Edit button to edit the variable.
   1. Click the New button in the Edit environment variable window.
      ![Environment Variable](/img/docs/contributing/env-variable.png)
   1. Enter `%MAVEN_HOME%\bin` in the new field. Click OK to save changes to the Path variable.
   1. Click OK in the Environment Variables window to save the changes to the system variables.  
      ![Edit env Variable](/img/docs/contributing/edit-env-variable.png)
1. Verify if Maven is installed correctly.
   1. Open Command Prompt and run the command: `mvn -v`
   1. It should display the Maven version as shown in the screenshot below:
      ![Maven Version Check](/img/docs/contributing/command-prompt-mvn-v.png)

#### Installing Maven on MacOS

Installation of Maven on a Mac machine can be easily done using the [Homebrew](https://brew.sh/) package manager.
After installing Homebrew, just run the command `brew install maven`, and it will install Maven within seconds on your machine, and you could straight away start using maven.

### Build the Core project {#build-project}

Once Maven installation is complete, from the root repository directory, run the following command:

```shell
> mvn install -f core-java/pom.xml -DskipTests
```

This will resolve the dependencies and run check-style check for the project without actually running the tests.

## Documentation project

The documentation project is located in `website/` directory.

### Install Node >= v16.15.0 {#install-node}

1. Install NVM (Node Version Manager) on your machine.
1. Open your terminal or command prompt and run the following command: `curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.1/install.sh | bash`
1. Run the following command to set up the project: `nvm install`
1. Verify if Node is installed correctly.
   1. Open Command Prompt and run the command: `nvm -v` and `node -v`
   1. It should display the NVM and Node version as shown in the screenshot below:
      ![Node Version Check](/img/docs/contributing/command-prompt-node-v.png)

### Build the documentation project {#build-documentation-project}

To build the documentation project, navigate to the root folder of the project and run the following command:

```shell
> pnpm build:site
```

This will install all dependencies and build the documentation site.

### Run the project

From the root repository directory, run the following command:

```shell
> pnpm start:site
```

This will start the documentation site on `localhost:3000` and will constantly watch on any changes you do in the project.

### Upgrade outdated dependencies

:::danger
Dependency upgrade will only be done by `boyka-core` team members.
:::

From the root repository directory, run the following command:

```shell
> pnpm up -i -r --latest
```

This will prompt to select the version to which we need to upgrade any particular dependency.

[github_cli]: https://github.com/cli/cli
