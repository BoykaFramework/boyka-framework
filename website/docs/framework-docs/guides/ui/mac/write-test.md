---
title: 4️⃣ Write MacOS Test class
sidebar_position: 6
---

Using the Calculator page actions, you can write your test class to interact with the page. The actions are static methods that can be used to perform various operations on the page elements.

## Example

```java
import static io.github.boykaframework.actions.device.DeviceActions.onDevice;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.enums.PlatformType.MAC;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.mac.actions.CalculatorActions.verifyAdd;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalculatorTest {
    @AfterMethod
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            onWindow ().takeScreenshot ();
        }
    }

    @BeforeClass
    public void setupTestClass () {
        createSession (MAC, "test_mac");
        onDevice ().startRecording ();
    }

    @AfterClass
    public void tearDownTestClass () {
        onDevice ().stopRecording ();
        withDriver ().saveLogs ();
        clearSession ();
    }

    @Test
    public void testCalculator () {
        verifyAdd (9, 7);
    }
}
```

This test class sets up the environment for running tests on the MacOS platform, including starting a session, recording actions, and taking screenshots on failure. The `verifyAdd` method from the `CalculatorActions` class is called to perform the addition operation and verify the result.
