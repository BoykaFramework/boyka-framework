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

import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;

import java.io.IOException;

import com.github.wasiqb.boyka.enums.Message;
import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.Test;

/**
 * Error handling test.
 *
 * @author Wasiq Bhamla
 * @since 28-Jul-2022
 */
public class ErrorHandlingTest {
    /**
     * Test error handling.
     */
    @Test (expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Test error...")
    public void testFrameworkError () {
        handleAndThrow (Message.TEST_ERROR, new IOException ("File not found"));
    }
}
