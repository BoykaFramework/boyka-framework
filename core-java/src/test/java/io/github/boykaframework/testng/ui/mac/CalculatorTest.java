/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package io.github.boykaframework.testng.ui.mac;

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

/**
 * Mac Calculator Test.
 *
 * @author Wasiq Bhamla
 * @since 09-May-2025
 */
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
