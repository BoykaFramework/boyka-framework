---
title: 🏗️ Building the project
sidebar_position: 2
---

## Java project

The main Java project is located in `core-java/` directory.

### Build the project {#build-project}

From the root repository directory, run the following command:

```shell
> mvn install -f core-java/pom.xml -DskipTests
```

This will resolve the dependencies and run checkstyle for the project.

## Documentation project

The documentation project is located in `website/` directory.

### Build the project {#build-documentation-project}

From the root repository directory, run the following command:

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
