---
title: DriverSession
sidebar_position: 1
slug: /sessions/driver-session
---

## `clearSharedData` {#clear-shared-data}

This method will clear all the shared data for the session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
getSession ().clearSharedData ();
```

## `getApiSetting` {#get-api-setting}

This method gets the API settings for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var apiSetting = getSession ().getApiSetting ();
```

## `getMobileSetting` {#get-mobile-setting}

This method gets the Mobile settings for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var mobileSetting = getSession ().getMobileSetting ();
```

## `getSharedData (key)` {#get-shared-data}

This method gets the shared data for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var mobileSetting = getSession ().getSharedData ("sample.key");
```

## `getWebSetting` {#get-web-setting}

This method gets the Web settings for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var webSetting = getSession ().getWebSetting ();
```

## `removeSharedData (key)` {#remove-shared-data}

This method removes the shared data for a particular key for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
var value = getSession ().removeSharedData ("some.key");
```

## `setSharedData (key, value)` {#set-shared-data}

This method sets the shared data for a particular key for the current session.

```java
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
. . .
getSession ().setSharedData ("some.key", "some value");
```
