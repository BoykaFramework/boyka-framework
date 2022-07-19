---
title: 🏗️ Building the project
sidebar_position: 2
---

## Cloning the Project

The first step to take before you start contributing to this project is to Clone the project.

Run the following command if you want to clone using `SSH`:

```shell
> git clone git@github.com:WasiqBhamla/boyka-framework.git
```

Run the following command if you want to clone using `HTTPS`:

```shell
> git clone https://github.com/WasiqBhamla/boyka-framework.git
```

Run the following command if you want to clone using [Github CLI][github_cli]:

```shell
> git clone gh repo clone WasiqBhamla/boyka-framework
```

The above URLs for SSH, HTTPS and Github CLI can be found on the github repo main page as well.

<img src="assets/gh_repo_mainpage.png" alt="Github repo main page" width="auto" height="auto"/>


## Java project

The main Java project is located in `core-java/` directory.

### Build the project {#build-project}

`Maven` is the build tool we would be using for this project. So need to have Maven installed on your machine before you proceed to build the project.
In case Maven is not installed on your machine, the following steps should help.

**Installing Maven on Windows**

1. Download the Maven Zip File from the maven download page and extract it.
2. Add MAVEN_HOME System Variable.
    
    2.1. Open the Start menu and search for environment variables. Click on the Edit System environment variables result.

    2.2. Under the Advanced tab in the System Properties window, click Environment Variables.
  
    <img src="assets/system_properties_window.png" alt="System Properties Tab" width="auto" height="auto"/>

    2.3. Click the New button under the System variables section to add a new system environment variable.

    2.4. Enter MAVEN_HOME as the variable name and the path to the Maven directory as the variable value. Click OK to save the new system variable.

    <img src="assets/new_system_variable.png" alt="System Properties Tab" width="auto" height="auto"/>

3. Add MAVEN_HOME directory in the PATH Variable.

    3.1. Select the Path variable under the System variables section in the Environment Variables window. Click the Edit button to edit the variable.

    3.2. Click the New button in the Edit environment variable window.

    <img src="assets/env_variable.png" alt="Environment Variable Dialog box" width="auto" height="auto"/>

    3.3. Enter `%MAVEN_HOME%\bin` in the new field. Click OK to save changes to the Path variable.

    3.4. Click OK in the Environment Variables window to save the changes to the system variables.  

    <img src="assets/edit_nv_variable.png" alt="Edit Environment Variable Dialog box" width="auto" height="auto"/>


4. Verify if Maven is installed correctly.

    4.1. Open Command Prompt and run the command: `mvn -v`

    4.2. It should display the Maven version as shown in the screenshot below:

    <img src="assets/command_prompt_mvn_v.png" alt="Command Prompt maven version check" width="auto" height="auto"/>


**Installing Maven on MacOS**

Installation of Maven on a Mac machine can be easily done using the Homebrew software. 
After installing Homebrew, just run the command “brew install maven”, and it will install Maven within seconds on your machine, and you could straight away start using maven.

Once Maven installation is complete, from the root repository directory, run the following command:

```shell
> mvn install -f core-java/pom.xml -DskipTests
```

This will resolve the dependencies and run checkstyle for the project.

## Documentation project

The documentation project is located in `website/` directory.

### Build the project {#build-documentation-project}

To build the project, `yarn` is required to be installed on your machine. 
Open your terminal or command prompt and run the following command:

```shell
> npm install --global yarn
```

Once the installation is complete you can check by running the following command to verify that `yarn` was installed correctly on your machine:

```shell
> yarn --version
```

Once `yarn` is installed on your machine, navigate to the root folder of the project and run the following command:

```shell
> yarn install
> yarn build:site
```

This will install all dependencies and build the documentation site.

### Run the project

From the root repository directory, run the following command:

```shell
> yarn start:site
```

This will start the documentation site on `localhost:3000`.

### Upgrade outdated dependencies

From the root repository directory, run the following command:

```shell
> yarn upgrade-interactive
```

This will prompt to select the version to which we need to upgrade any particular dependency.

:::tip
You can upgrade any dependency to latest version even if it is upgrading to major version, except for the following dependencies:

- `react`
- `react-dom`
- `@mdx-js/react`
- `@docusaurus/*`

Only the `boyka-core` team will upgrade the above dependencies.
:::

[github_cli]:https://github.com/cli/cli
