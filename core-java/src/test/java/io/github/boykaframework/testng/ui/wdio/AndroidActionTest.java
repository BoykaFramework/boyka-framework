/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.ui.wdio;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static io.appium.java_client.android.nativekey.AndroidKey.BACK;
import static io.github.boykaframework.actions.device.AndroidDeviceActions.onAndroidDevice;
import static io.github.boykaframework.actions.device.DeviceActions.onDevice;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.actions.elements.ElementActions.onElement;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.wdio.pages.WDIOHomePage.wdioHomePage;
import static java.lang.System.getProperty;
import static java.nio.file.Path.of;
import static java.util.Objects.isNull;

import io.github.boykaframework.enums.PlatformType;
import lombok.SneakyThrows;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Test class for testing different Android actions.
 *
 * @author Wasiq Bhamla
 * @since 22-Jan-2025
 */
public class AndroidActionTest {
    /**
     * Setup test method to take screenshot after each test method.
     */
    @AfterMethod
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            onWindow ().takeScreenshot ();
        }
    }

    /**
     * Setup test class by initializing driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String driverKey) {
        createSession (platformType, driverKey);
        onDevice ().startRecording ();
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        onDevice ().stopRecording ();
        withDriver ().saveLogs ();
        clearSession ();
    }

    /**
     * Test clipboard.
     */
    @Test
    public void testClipboardText () {
        final var expectedClipboardText = "Hello World!";
        onAndroidDevice ().setClipboardText (expectedClipboardText);
        onAndroidDevice ().verifyClipboardText ()
            .isEqualTo (expectedClipboardText);

        assertThat (onAndroidDevice ().getClipboardText ()).isEqualTo (expectedClipboardText);
    }

    /**
     * Test Put and Pull file.
     */
    @SneakyThrows
    @Test
    public void testFileTransfer () {
        final var imageFile = of (getProperty ("user.dir"), "src/test/resources/data/image/Boyka.png").toFile ();
        final var deviceFilePath = of ("/sdcard/Pictures", imageFile.getName ()).toString ();
        var actualFileBytes = onAndroidDevice ().pullFile (deviceFilePath);

        if (isNull (actualFileBytes)) {
            onAndroidDevice ().putFile (imageFile, deviceFilePath);
            actualFileBytes = onAndroidDevice ().pullFile (deviceFilePath);
        }

        assertWithMessage ("File content").that (actualFileBytes)
            .isNotEmpty ();
    }

    /**
     * Test Open Notification
     */
    @Test
    public void testOpenNotification () {
        onAndroidDevice ().openNotification ();
        onElement (wdioHomePage ().getDragTab ()).verifyIsDisplayed ()
            .isFalse ();
        onAndroidDevice ().pressKey (BACK);
        onElement (wdioHomePage ().getDragTab ()).verifyIsDisplayed ()
            .isTrue ();
    }
}
