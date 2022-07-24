---
title: DriverManager
sidebar_position: 2
---

## `closeDriver`

This method is used to close the driver.

```java
import static com.github.wasiqb.boyka.manager.DriverActions.closeDriver;
. . .
closeDriver ();
```

## `createDriver`

This method is used to create the driver for given application type and config key.

```java
import static com.github.wasiqb.boyka.manager.DriverActions.createDriver;
. . .
createDriver (ApplicationType.WEB, "config-key");
```
