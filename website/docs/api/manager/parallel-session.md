---
title: ParallelSession
sidebar_position: 2
slug: /sessions/parallel-session
---

## `clearAllSessions` {#clear-all-session}

This method clears all the started sessions.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.clearAllSessions;
. . .
clearAllSessions ();
```

## `clearSession` {#clear-session}

This method clears the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
. . .
clearSession ();
```

## `createSession (platformType, configKey)` {#create-session}

This method will create the session for the mentioned persona.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
. . .
createSession (PlatformType.WEB, "test_chrome");
```

## `createSession (persona, platformType, configKey)` {#create-session}

This method will create the session for the mentioned persona.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
. . .
createSession ("User 1", PlatformType.WEB, "test_chrome");
```

## `getCurrentPersona` {#get-current-persona}

This method gets the current session persona name.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getCurrentPersona;
. . .
var sessionPersona = getCurrentPersona ();
```

## `getSession` {#get-session}

This method gets the current [Driver session](/api/sessions/driver-session)

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var session = getSession ();
```

## `switchPersona (persona)` {#switch-persona}

This method will switch the current session context to a different persona. This is best when you are working on multiple user sessions.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.switchPersona;
. . .
switchPersona ("User 2");
```
