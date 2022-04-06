---
title: Compose Request
---

For API automation, we have created `ApiRequest` class which you can utilize to build API requests.

This class has a builder pattern which allows you to build API requests in a fluent way.

Let's see how to build API request for different request methods using the following API configuration.

```json title="src/test/resources/boyka-config.json"
{
  "ui": {
    ...
  },
  "api": {
    "test_reqres": {
      "base_uri": "https://reqres.in",
      "base_path": "/api",
      "read_timeout": 2,
      "write_timeout": 2,
      "connection_timeout": 1,
      "logging": {
        "request": true,
        "response": true
      }
    }
  }
}
```

## Example

Let's create an instance of `ApiRequest` and build a request for `POST /api/users` with body:

```java
// This key refers to config key under API section.
final String API_CONFIG_KEY = "test_reqres";

// Create request body object.
final User user = User.createUser ()
    .name ("Wasiq")
    .job ("Software Engineer")
    .create ();

// Build API request.
final ApiRequest request = ApiRequest.createRequest ()
    .configKey (API_CONFIG_KEY)
    .method (RequestMethod.POST)
    .path ("/users")
    .bodyObject (user)
    .create ();
```

:::info
Similarly, you can build a request for other types of requests by modifying `RequestMethod` enum.
:::
