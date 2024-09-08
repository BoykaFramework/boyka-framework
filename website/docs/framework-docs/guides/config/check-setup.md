---
sidebar_position: 3
title: 🔎 Check Boyka setup
---

You can check whether your machine and the project is setup properly for Boyka Framework by executing the following command in your terminal:

```shell
boyka doctor
```

## Checks verified

- Current directory should have `pom.xml` file
- There should exist a folder path of `src/test/resources`
- There should exist a file named `boyka-config.json` at the `src/test/resources` path

:::danger
Any check if failed, will give an error in the console.
:::
