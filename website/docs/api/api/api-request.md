---
title: ApiRequest
sidebar_position: 1
---

## `createRequest`

### Description

This is a builder method to build `ApiRequest` instance.

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

## `create`

### Description

This method will create `ApiRequest` instance.

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
