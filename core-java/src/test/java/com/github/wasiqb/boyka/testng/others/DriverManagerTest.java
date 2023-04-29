/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

import static com.github.wasiqb.boyka.enums.PlatformType.WEB;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.Test;

/**
 * Test class for testing Driver manager class.
 *
 * @author Wasiq Bhamla
 * @since 29-Jul-2022
 */
public class DriverManagerTest {
    private static final String PERSONA = "DriverManagerTest";

    /**
     * Test method to verify empty browser in config.
     */
    @Test (description = "Test Web empty browser in config", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Browser type cannot be empty in the config...")
    public void testEmptyBrowserInConfig () {
        try {
            createSession (PERSONA, WEB, "test_local_empty_browser");
        } finally {
            clearSession ();
        }
    }

    /**
     * Test method to verify invalid config key.
     */
    @Test (description = "Test Web invalid config key", expectedExceptions = FrameworkError.class)
    public void testInvalidWebConfigKey () {
        try {
            createSession (PERSONA, WEB, "test_lambda_test_no_pa");
        } finally {
            clearSession ();
        }
    }

    /**
     * Test method to verify empty browser in config.
     */
    @Test (description = "Test Web null browser in config")
    public void testNullBrowserInConfig () {
        try {
            createSession (PERSONA, WEB, "test_local_null_browser");
        } finally {
            clearSession ();
        }
    }

    /**
     * Test method to verify null cloud Host.
     */
    @Test (description = "Test Null cloud Host", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Host name is required for remote driver execution...")
    public void testNullCloudHost () {
        try {
            createSession (PERSONA, WEB, "test_lambda_test_no_host");
        } finally {
            clearSession ();
        }
    }

    /**
     * Test method to verify null cloud password.
     */
    @Test (description = "Test Null cloud password", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Password is required for cloud execution...")
    public void testNullCloudPassword () {
        try {
            createSession (PERSONA, WEB, "test_lambda_test_no_password");
        } finally {
            clearSession ();
        }
    }

    /**
     * Test method to verify null cloud username.
     */
    @Test (description = "Test Null cloud username", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "User name is required for cloud execution...")
    public void testNullCloudUsername () {
        try {
            createSession (PERSONA, WEB, "test_lambda_test_no_username");
        } finally {
            clearSession ();
        }
    }
}
