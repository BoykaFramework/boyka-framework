---
title: StringUtils
sidebar_position: 4
---

## `interpolate`

This method is used to interpolate the given string with the given values or with environment variables / system properties.

```java
import static io.github.boykaframework.utils.StringUtils.interpolate;
. . .
System.out.println (interpolate ("${env:USER_NAME}"));

. . . OR . . .

Map<String, String> values = new HashMap<> ();
values.put ("USER_NAME", "wasiq");

System.out.println (interpolate ("${USER_NAME}", values));
```

:::tip
You can explore all the available formats for interpolation in Web configuration [`info` section](/docs/guides/config/configuration#web-config).
:::
