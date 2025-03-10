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

package io.github.boykaframework.testng.api.others;

import static io.github.boykaframework.actions.api.ApiActions.withRequest;
import static io.github.boykaframework.builders.ApiRequest.createRequest;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.enums.RequestMethod.GET;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.api.restful.requests.BookingRequest.createBooking;
import static io.github.boykaframework.testng.api.restful.requests.BookingRequest.getBooking;

import io.github.boykaframework.enums.RequestMethod;
import io.github.boykaframework.exception.FrameworkError;
import io.github.boykaframework.testng.api.restful.data.BookingRequestData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Other API related tests
 *
 * @author Wasiq Bhamla
 * @since 27-Aug-2022
 */
public class ApiTests {
    @AfterMethod
    public void tearDownMethod () {
        clearSession ();
    }

    @Test (description = "Test Empty path param")
    public void testEmptyPathParam () {
        createSession (API, "test_restfulbooker");
        final var request = getBooking (null);
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
    }

    /**
     * Tests invalid config key.
     */
    @Test (description = "Test Invalid API config key", expectedExceptions = FrameworkError.class)
    public void testInvalidApiConfigKey () {
        createSession (API, "test_req");
        final var userRequest = createRequest ().method (GET)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .create ();
        withRequest (userRequest).execute ();
    }

    @Test
    public void testNullHeader () {
        createSession (API, "test_restfulbooker");
        final var request = createRequest ().method (RequestMethod.POST)
            .header ("Accept", "application/json")
            .header ("Content-Type", null)
            .path ("/booking")
            .bodyObject (createBooking (BookingRequestData.getBookingData ()))
            .create ();

        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (500);
    }
}
