---
title: CookieActions
sidebar_position: 2
---

## Static methods

### `withCookies` {#with-cookies}

This will return `ICookieActions` which will expose different methods to handle Cookies.

```java
ICookieActions cookiesActions = CookieActions.withCookies ();
```

## Instance methods

### `cookie (name)` {#cookie-name}

This method will get the cookie from the browser based on it's name.

```java
import static com.github.wasiqb.boyka.actions.drivers.CookieActions.withCookies;
import org.openqa.selenium.Cookie;
. . .
Cookie c = withCookies ().cookie ("cookie-name");
```

### `cookies`

This method will get all the cookie from the browser and return all the cookie names as list.

```java
import static com.github.wasiqb.boyka.actions.drivers.CookieActions.withCookies;
. . .
List<String> cookieNames = withCookies ().cookies ();
```

### `deleteAll` {#delete-all}

This method will delete all the cookies from the browser.

```java
import static com.github.wasiqb.boyka.actions.drivers.CookieActions.withCookies;
. . .
withCookies ().deleteAll ();
```

### `delete (name)`

This method will delete the cookie from the browser based on it's name.

```java
import static com.github.wasiqb.boyka.actions.drivers.CookieActions.withCookies;
. . .
withCookies ().delete ("cookie-name");
```
