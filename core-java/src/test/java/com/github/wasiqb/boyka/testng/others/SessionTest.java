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
import static com.github.wasiqb.boyka.manager.ParallelSession.clearAllSessions;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Test class for testing Parallel Session class.
 *
 * @author Wasiq Bhamla
 * @since 18-Oct-2023
 */
public class SessionTest {
    private static final String PERSONA = "SessionTest";

    /**
     * Clear any open sessions.
     */
    @AfterMethod
    public void teardownMethod () {
        clearAllSessions ();
    }

    /**
     * Test duplicate clear session.
     */
    @Test (description = "Test duplicate clear session", expectedExceptions = FrameworkError.class)
    public void testDuplicateClearSession () {
        createSession (PERSONA, WEB, "test_local_chrome");
        clearSession ();
        clearSession ();
    }

    /**
     * Test Duplicate Session creation.
     */
    @Test (description = "Test Duplicate Session creation", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Session is already created for .SessionTest. persona...")
    public void testDuplicateSessionCreation () {
        createSession (PERSONA, WEB, "test_local_chrome");
        createSession (PERSONA, WEB, "test_local_chrome");
        clearSession ();
    }

    /**
     * Test session creation with Null persona.
     */
    @Test (description = "Test session creation with Null persona", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Session Persona cannot be empty or null...")
    public void testSessionCreationWithNullPersona () {
        createSession (null, WEB, "test_local_chrome");
    }
}
