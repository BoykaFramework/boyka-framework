/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.api.postman;

import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;

import com.github.wasiqb.boyka.builders.ApiRequest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test cookies in API.
 *
 * @author Wasiq Bhamla
 * @since 09-Feb-2024
 */
public class ApiCookiesTest {
    /**
     * Setup API Test.
     */
    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        createSession (API, "test_postman");
    }

    /**
     * Clean up Test class.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        clearSession ();
    }

    /**
     * Test Get Cookies.
     */
    @Test (dependsOnMethods = "testSetCookies")
    public void testGetCookies () {
        final var request = ApiRequest.createRequest ()
            .method (GET)
            .path ("/cookies")
            .create ();
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("cookies.foo1")
            .isNotEmpty ();
    }

    /**
     * Test Set Cookies.
     */
    @Test
    public void testSetCookies () {
        final var request = ApiRequest.createRequest ()
            .method (GET)
            .path ("/cookies/set")
            .queryParam ("foo1", "bar1")
            .queryParam ("foo2", "bar2")
            .create ();
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyHeader ("set-cookie")
            .isNotEmpty ();
    }
}
