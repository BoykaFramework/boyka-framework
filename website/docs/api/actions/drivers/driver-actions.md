---
title: DriverActions
sidebar_position: 4
---

## Static methods

### `withDriver` {#with-driver}

This will return `IDriverActions` which will expose different methods to handle all other driver actions.

```java
IDriverActions driverActions = DriverActions.withDriver ();
```

## Instance methods

### `executeScript (script, args[])` {#execute-script}

This method will execute the JS script and returns the result.

```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
. . .
String output = withDriver ().executeScript ("alert('Hello World');");
```

### `pressKeys (keys)` {#press-keys}

 This method will simulate the given keys being pressed.

 ```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import org.openqa.selenium.Keys;

withDriver ().pressKeys (Keys.ENTER, "Hello World");
 ```

### `pause` {#pause}

This method is used to navigate to the given URL.

```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static java.time.Duration.ofMillis;
. . .
withDriver ().pause (ofMillis (100));
```

### `saveLogs` {#save-logs}

This method will save all the logs captured by the Driver.

```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
. . .
withDriver ().saveLogs ();
```

### `waitUntil` {#wait-until}

This method will wait for any given condition to be true. It takes in Selenium WebDrivers `ExpectedCondition<Boolean>` object as parameter.

```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
. . .
withDriver ().waitUntil (ExpectedConditions.urlMatches (URL));
```

### `waitUntil(condition, timeout)` {#wait-until-with-timeout}

This method will wait for any given condition to be true. It takes in Selenium WebDrivers `ExpectedCondition<Boolean>` object as parameter.

```java
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
. . .
withDriver ().waitUntil (ExpectedConditions.urlMatches (URL), Duration.ofSeconds(10));
```
