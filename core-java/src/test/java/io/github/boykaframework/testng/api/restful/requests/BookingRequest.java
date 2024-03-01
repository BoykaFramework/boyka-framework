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

package io.github.boykaframework.testng.api.restful.requests;

import static io.github.boykaframework.builders.ApiRequest.createRequest;
import static io.github.boykaframework.testng.api.restful.data.AuthRequestData.getTokenData;
import static java.text.MessageFormat.format;

import io.github.boykaframework.actions.api.ApiActions;
import io.github.boykaframework.builders.ApiRequest;
import io.github.boykaframework.enums.RequestMethod;
import io.github.boykaframework.testng.api.restful.pojo.BookingData;

/**
 * Booking request class
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
public final class BookingRequest {
    public static ApiRequest createBooking (final BookingData requestBody) {
        return createRequest ().method (RequestMethod.POST)
            .header ("Accept", "application/json")
            .path ("/booking")
            .bodyObject (requestBody)
            .create ();
    }

    public static ApiRequest deleteBooking (final String id) {
        return createRequest ().method (RequestMethod.DELETE)
            .header ("Content-Type", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .pathParam ("id", id)
            .create ();
    }

    public static ApiRequest getBooking (final String id) {
        return createRequest ().method (RequestMethod.GET)
            .header ("Accept", "application/json")
            .path ("/booking/${id}")
            .pathParam ("id", id)
            .create ();
    }

    public static ApiRequest updateBooking (final String id, final BookingData requestBody) {
        return createRequest ().method (RequestMethod.PUT)
            .header ("Accept", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .bodyObject (requestBody)
            .pathParam ("id", id)
            .create ();
    }

    public static ApiRequest updatePartialBooking (final String id, final BookingData requestBody) {
        return createRequest ().method (RequestMethod.PATCH)
            .header ("Accept", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .bodyObject (requestBody)
            .pathParam ("id", id)
            .create ();
    }

    private static String generateToken () {
        final var generateTokenRequest = createRequest ().header ("Accept", "application/json")
            .method (RequestMethod.POST)
            .path ("/auth")
            .bodyObject (getTokenData ())
            .create ();

        final var response = ApiActions.withRequest (generateTokenRequest)
            .execute ();
        return response.getResponseData ("token");
    }

    private BookingRequest () {
        // Utility Class.
    }
}
