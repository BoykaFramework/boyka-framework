---
title: ApiResponse
sidebar_position: 3
---

## `getResponseData` {#get-response-data-1}

This method will get the response data from the response body.

### Parameters

#### `expression`

This parameter expects a valid `JsonPath` expression.

### Return Type: `String`

Returns the response data as string.

## `getResponseData` {#get-response-data-2}

This method will get the response data from the response body.

### Parameters

#### `expression`

This parameter expects a valid `JsonPath` expression.

#### `type`

This parameter expects a class type.

### Return Type: `<T>`

Returns the response data as Java object.

## `verifyBooleanField` {#verify-boolean-field}

This method will verify the boolean field in the response body.

### Parameters

#### `expression`

This parameter expects a valid `JsonPath` expression.

### Return Type: `BooleanSubject`

Returns the [`BooleanSubject`][boolean-subject] object.

## `verifyIntField` {#verify-int-field}

This method will verify the boolean field in the response body.

### Parameters

#### `expression`

This parameter expects a valid `JsonPath` expression.

### Return Type: `IntegerSubject`

Returns the [`IntegerSubject`][int-subject] object.

## `verifyStatusCode` {#verify-status-code}

This method will verify the status code of the response.

### Return Type: `IntegerSubject`

Returns the [`IntegerSubject`][int-subject] object.

## `verifyStatusMessage` {#verify-status-message}

This method will verify the status message of the response.

### Return Type: `StringSubject`

Returns the [`StringSubject`][string-subject] object.

## `verifyTextField` {#verify-text-field}

This method will verify the text field in the response body.

### Parameters

#### `expression`

This parameter expects a valid `JsonPath` expression.

### Return Type: `StringSubject`

Returns the [`StringSubject`][string-subject] object.

[boolean-subject]: https://truth.dev/api/latest/com/google/common/truth/BooleanSubject.html#method.summary
[string-subject]: https://truth.dev/api/latest/com/google/common/truth/StringSubject.html#method.summary
[int-subject]: https://truth.dev/api/latest/com/google/common/truth/IntegerSubject.html#method.summary
