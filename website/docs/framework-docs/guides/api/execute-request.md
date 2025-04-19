---
sidebar_position: 3
title: 3️⃣ Execute API Request
---

Once you have composed your [API request](/docs/guides/api/compose-request), you can execute it using class `ApiActions` and method `withRequest`.

:::tip
Check out complete documentation for [ApiActions](/api/actions/api/api-actions).
:::

## Example

```java
import static io.github.boykaframework.actions.api.ApiActions.withRequest;
. . .
// Using request created in previous example.
final ApiResponse response = withRequest (request).execute ();
```

## API Response class

The `withRequest` method will return `ApiResponse` class object which contains methods to verify the response body and status code.

:::info
Check out complete documentation for [ApiResponse](/api/builders/api-response).
:::
