---
title: JsonUtil
sidebar_position: 2
---

## `fromFile`

This method is used to parse the given JSON file to Java object.

```java
import static io.github.boykaframework.utils.JsonParser.fromFile;
. . .
Users users = fromFile ("/path/to/file.json", Users.class);
```

## `toFile`

This method is used to write the given JSON object to the given file.

```java
import static io.github.boykaframework.utils.JsonParser.toFile;
. . .
toFile (users, "/path/to/file.json");
```

## `toString(object)`

This method is used to convert the given object to JSON string.

```java
import static io.github.boykaframework.utils.JsonParser.toString;
. . .
System.out.println (toString (users));
```

## `toString(string)`

This method is used to pretty-print the given JSON string.

```java
import static io.github.boykaframework.utils.JsonParser.toString;
. . .
String jsonString = "{\"name\":\"Wasiq\",\"age\":25}";
System.out.println (toString (jsonString));
```
