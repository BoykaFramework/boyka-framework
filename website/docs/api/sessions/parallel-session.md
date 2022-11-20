---
title: ParallelSession
sidebar_position: 2
slug: /sessions/parallel-session
---

## `clearSession` {#clear-session}

This method clears the current session.

```java
import static com.github.wasiqb.boyka.sessions.ParallelSession.clearSession;
. . .
clearSession ();
```

## `getSession` {#get-session}

This method gets the current session.

```java
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
. . .
var session = getSession ();
```

## `setDriver (driver)` {#set-driver}

This method sets the `WebDriver` instance for the session.

```java
import static com.github.wasiqb.boyka.sessions.ParallelSession.setDriver;
. . .
setDriver (new ChromeDriver (url, capabilities));
```
