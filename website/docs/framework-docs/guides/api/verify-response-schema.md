---
sidebar_position: 5
title: ✅ Verify Response schema
---

## Generate response schema

Once you get the response body from the logs or from Postman, you can generate its schema by using any good online JSON schema generator, example [Transform tools](https://transform.tools/json-to-json-schema)

Once the schema is generated, copy the schema and create a new file in the schema folder which you have configured in the Boyka config file.

You can give a meaningful name to that schema file.

## Verify schema

In your test class, you can add the following line to verify the response schema:

```java
. . .
response.verifySchema ("[schema-file-name].json");
. . .
```
