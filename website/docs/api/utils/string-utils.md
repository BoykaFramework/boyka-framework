---
title: StringUtils
sidebar_position: 10
---

## `interpolate`

This method is used to interpolate the given string with the given values or with environment variables / system properties.

```java
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;
. . .
System.out.println (interpolate ("${env:USER_NAME}"));

. . . OR . . .

Map<String, String> values = new HashMap<> ();
values.put ("USER_NAME", "wasiq");

System.out.println (interpolate ("${USER_NAME}", values));
```

:::tip
You can explore all the available formats for interpolation in Web configuration [`info` section](/docs/guides/configuration#web-config).
:::
