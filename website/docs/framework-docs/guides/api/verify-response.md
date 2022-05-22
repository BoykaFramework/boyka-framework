---
sidebar_position: 4
title: ✅ Verify Response
---

Once the `ApiResponse` object is returned from the `execute` method, it can be used to verify that the response is valid.

```java
final ApiResponse response = ApiManager.execute (request);

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
```

:::info
All the verification methods in `ApiResponse` class uses [Google Truth library][truth] and exposes it's methods to verify the response.
:::

[truth]: https://truth.dev/
