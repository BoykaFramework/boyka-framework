---
title: JsonParser
sidebar_position: 8
---

## `fromFile`

This method is used to parse the given JSON file to Java object.

```java
import static com.github.wasiqb.boyka.utils.JsonParser.fromFile;
. . .
Users users = fromFile ("/path/to/file.json", Users.class);
```

## `toFile`

This method is used to write the given JSON object to the given file.

```java
import static com.github.wasiqb.boyka.utils.JsonParser.toFile;
. . .
toFile (users, "/path/to/file.json");
```

## `toString`

This method is used to convert the given object to JSON string.

```java
import static com.github.wasiqb.boyka.utils.JsonParser.toString;
. . .
System.out.println (toString (users));
```
