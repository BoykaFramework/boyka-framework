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

import static com.github.wasiqb.boyka.enums.ApplicationType.API;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.sessions.ParallelSession.clearSession;

import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.Test;

/**
 * Test class for testing Driver manager class.
 *
 * @author Wasiq Bhamla
 * @since 29-Jul-2022
 */
public class DriverManagerTest {
    /**
     * Method to test create driver.
     */
    @Test (description = "Test Create Driver", expectedExceptions = FrameworkError.class)
    public void testCreateDriver () {
        try {
            createDriver (API, "test_local_chrome");
        } finally {
            clearSession ();
        }
    }
}
