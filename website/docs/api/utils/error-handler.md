---
title: ErrorHandler
sidebar_position: 1
---

## `handleAndThrow`

This method is used to handle errors, logs the framework specific stack traces and throw the wrapped framework error.

```java
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.enums.Message.ERROR_PARSING_RESPONSE_BODY;
. . .
try {
    . . .
} catch (IOException e) {
    handleAndThrow (ERROR_PARSING_RESPONSE_BODY, e);
}
. . .
```
