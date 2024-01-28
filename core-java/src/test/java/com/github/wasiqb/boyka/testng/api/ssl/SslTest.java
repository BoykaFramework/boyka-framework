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

package com.github.wasiqb.boyka.testng.api.ssl;

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
 * Test Bad SSL API.
 *
 * @author Wasiq Bhamla
 * @since 28-Jan-2024
 */
public class SslTest {
    /**
     * Set up class.
     */
    @BeforeClass
    public void setupClass () {
        createSession (API, "test_bad_ssl");
    }

    /**
     * Tear down the class.
     */
    @AfterClass
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test Bad SSL API.
     */
    @Test
    public void testBadSsl () {
        final var request = ApiRequest.createRequest ()
            .method (GET)
            .create ();
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
    }
}
