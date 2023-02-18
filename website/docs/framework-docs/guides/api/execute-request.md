---
sidebar_position: 3
title: 🔫 Execute Request
---

Once you have composed your API request, you can execute it using class `ApiManager` and method `execute`.

:::tip
Check out complete documentation for [ApiManager](/api/api/api-manager).
:::

## Example

```java
import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;
. . .
// Using request created in previous example.
final ApiResponse response = withRequest (request).execute ();
```

## API Response class

The `execute` method will return `ApiResponse` class object which contains methods to verify the response body and status code.

:::tip
Check out complete documentation for [ApiResponse](/api/builders/api-response).
:::

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
