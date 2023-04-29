---
sidebar_position: 2
title: 🏗️ Compose Request
---

For API automation, we have created `ApiRequest` class which you can utilize to build API requests.

:::tip
Check out complete documentation for [ApiRequest](/api/builders/api-request).
:::

This class has a builder pattern which allows you to build API requests in a fluent way.

## Example

Let's create an instance of `ApiRequest` and build a request for `POST /api/users` with body:

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

// Create API session using the config key.
createSession (PlatformType.API, "test_reqres");

// Create request body object.
final User user = User.createUser ()
    .name ("Wasiq")
    .job ("Software Engineer")
    .create ();

// Build API request.
final ApiRequest request = ApiRequest.createRequest ()
    .method (RequestMethod.POST)
    .path ("/users")
    .bodyObject (user)
    .create ();
```

:::info
Similarly, you can build a request for other types of requests by modifying `RequestMethod` enum.
:::
