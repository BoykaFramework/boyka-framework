---
title: ApiRequest
sidebar_position: 1
---

## `createRequest`

### Description

This is a builder method to build `ApiRequest` instance.

:::caution Important!
Always start composing the request with `createRequest` method.
:::

### Example

```java
// highlight-next-line
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
    .create ();
```

## `configKey`

### Description

This method is used to set the configuration key corresponding to API config block in `boyka-config.json`.

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
// highlight-next-line
    .configKey ("test_reqres")
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
    .create ();
```

## `contentType`

### Description

This method is used to set the content type of the request. Allowed values are:

- `FORM_URLENCODED`
- `JSON` (**default**)
- `MULTIPART_FORM_DATA`
- `PLAIN_TEXT`

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
// highlight-next-line
    .contentType (ContentType.JSON)
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
    .create ();
```

## `header`

### Description

This method is used to set the header of the request.

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .contentType (ContentType.JSON)
// highlight-start
    .header ("Header-Key-1", "Header value 1")
    .header ("Header-Key-2", "Header value 2")
// highlight-end
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
    .create ();
```

## `path`

### Description

This method is used to set the path of the request.

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .contentType (ContentType.JSON)
    .header ("Header-Key-1", "Header value 1")
    .header ("Header-Key-2", "Header value 2")
    .method (GET)
// highlight-next-line
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
    .create ();
```

## `pathParam`

### Description

This method is used to set the path parameter(s) of the request.

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .contentType (ContentType.JSON)
    .header ("Header-Key-1", "Header value 1")
    .header ("Header-Key-2", "Header value 2")
    .method (GET)
    .path ("/users/${userId}")
// highlight-next-line
    .pathParam ("userId", "2")
    .create ();
```

## `queryParam`

### Description

This method is used to set the query parameter(s) of the request.

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .contentType (ContentType.JSON)
    .header ("Header-Key-1", "Header value 1")
    .header ("Header-Key-2", "Header value 2")
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
// highlight-next-line
    .queryParam ("param1", "value1")
    .create ();
```

## `body`

### Description

This method is used to set the body of the request.

### Example

```java
final ApiRequest request = ApiRequest.configKey (API_CONFIG_KEY)
    .method (POST)
    .path ("/users")
// highlight-next-line
    .body ("{\"name\":\"John\",\"job\":\"Developer\"}")
    .create ();
```

## `bodyObject`

### Description

This method is used to set the body of the request using Java object.

### Example

```java
final User user = User.createUser ()
    .name ("Wasiq")
    .job ("Software Engineer")
    .create ();

final ApiRequest request = ApiRequest.configKey (API_CONFIG_KEY)
    .method (POST)
    .path ("/users")
// highlight-next-line
    .bodyObject (user)
    .create ();
```

## `method`

### Description

This method is used to set the method of the request. Following are the allowed values:

- `DELETE`
- `GET`
- `HEAD`
- `OPTIONS`
- `PATCH`
- `POST`
- `PUT`
- `TRACE`

### Example

```java
final ApiRequest request = ApiRequest.configKey (API_CONFIG_KEY)
// highlight-next-line
    .method (RequestMethod.POST)
    .path ("/users")
    .bodyObject (user)
    .create ();
```

## `userName`

### Description

This method is used to set the user name of the request.

### Example

```java
final ApiRequest request = ApiRequest.configKey (API_CONFIG_KEY)
    .method (POST)
    .path ("/users")
// highlight-next-line
    .userName ("wasiq")
    .password ("123456")
    .bodyObject (user)
    .create ();
```

## `password`

### Description

This method is used to set the password of the request.

### Example

```java
final ApiRequest request = ApiRequest.configKey (API_CONFIG_KEY)
    .method (POST)
    .path ("/users")
    .userName ("wasiq")
// highlight-next-line
    .password ("123456")
    .bodyObject (user)
    .create ();
```

## `create`

### Description

This method will create `ApiRequest` instance.

:::caution Important!
Always end composing the request with `create` method.
:::

### Example

```java
final ApiRequest request = ApiRequest.createRequest ()
    .configKey ("test_reqres")
    .method (GET)
    .path ("/users/${userId}")
    .pathParam ("userId", "2")
// highlight-next-line
    .create ();
```
