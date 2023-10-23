/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.others;

import static com.github.wasiqb.boyka.actions.device.DeviceActions.onDevice;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearAllSessions;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Video recording related tests.
 *
 * @author Wasiq Bhamla
 * @since 23-Oct-2023
 */
public class VideoRecordTest {
    /**
     * Clear all sessions.
     */
    @AfterMethod
    public void teardownMethod () {
        clearAllSessions ();
    }

    /**
     * Test API platform video recording.
     */
    @Test (expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Video recording is not supported for .API. platform...")
    public void testApiVideoRecording () {
        createSession (API, "test_postman");
        onDevice ().startRecording ();
    }
}
