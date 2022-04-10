---
sidebar_position: 3
title: Execute Request
---

Once you have composed your API request, you can execute it using class `ApiManager` and method `execute`.

## Example

```java
// Using request created in previous example.
final ApiResponse response = ApiManager.execute (request);
```

## API Response class

The `execute` method will return `ApiResponse` class object which contains methods to verify the response body and status code.

:::caution
Methods to verify Response headers will be added soon!
:::

### Verification methods

Following are the methods exposed in `ApiResponse` class to verify the response body and status code:

- `verifyBooleanField`: Verifies the boolean field in response body.
- `verifyIntField`: Verifies the integer field in response body.
- `verifyStatusCode`: Verifies the status code of response.
- `verifyStatusMessage`: Verifies the status message of response.
- `verifyTextField`: Verifies the text field in response body.

### Methods to get response data

Following are the methods exposed in `ApiResponse` class to get response data:

- `getResponseData (expression)`: Returns the response data as String.
- `getResponseData (expression, type)`: Returns the response data as per the specified class type.
