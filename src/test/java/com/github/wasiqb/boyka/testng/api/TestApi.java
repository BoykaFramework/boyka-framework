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

package com.github.wasiqb.boyka.testng.api;

import static com.github.wasiqb.boyka.manager.ApiManager.createRequest;

import com.github.wasiqb.boyka.manager.ApiManager;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
public class TestApi {
    /**
     * Method to test GET request.
     */
    @Test (description = "Test GET request")
    public void testGetMethod () {
        final ApiManager response = createRequest ("test_reqres").pathParam ("userId", "2")
            .get ("/users/${userId}");
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyIntField ("data.id")
            .isEqualTo (2);
    }
}