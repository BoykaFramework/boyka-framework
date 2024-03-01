---
sidebar_position: 4
title: ✅ Verify Response
---

Once the `ApiResponse` object is returned from the `withRequest` method, it can be used to verify that the response is valid.

```java
import static io.github.boykaframework.manager.ParallelSession.clearSession;

// Execute request.
final ApiResponse response = ApiManager.execute (request);

// Verify response body.
response.verifyStatusCode ()
    .isEqualTo (201);
response.verifyTextField ("id")
    .isNotNull ();
response.verifyTextField ("name")
    .isEqualTo (user.getName ());
response.verifyTextField ("job")
    .isEqualTo (user.getJob ());
response.verifyTextField ("createdAt")
    .isNotNull ();
response.verifySchema("CreateUserSchema.json");

// Clear session after test is completed.
clearSession ();
```

### Verification methods

Following are the methods exposed in `ApiResponse` class to verify the response body and status code:

- `verifyBooleanField`: Verifies the boolean field in response body.
- `verifyHeader`: Verifies the header in response.
- `verifyIntField`: Verifies the integer field in response body.
- `verifySchema`: Verifies the response body against the given schema.
- `verifyStatusCode`: Verifies the status code of response.
- `verifyStatusMessage`: Verifies the status message of response.
- `verifyTextField`: Verifies the text field in response body.

### Methods to get response data

Following are the methods exposed in `ApiResponse` class to get response data:

- `getResponseData (expression)`: Returns the response data as String.
- `getResponseData (expression, type)`: Returns the response data as per the specified class type.

:::info
All the verification methods in `ApiResponse` class uses [Google Truth library][truth] and exposes it's methods to verify the response.
:::

[truth]: https://truth.dev/
