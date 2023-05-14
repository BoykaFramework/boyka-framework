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

package com.github.wasiqb.boyka.api;

import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;
import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.enums.RequestMethod.DELETE;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.enums.RequestMethod.PATCH;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.enums.RequestMethod.PUT;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static java.text.MessageFormat.format;

import com.github.wasiqb.boyka.api.requests.BookingData;
import com.github.wasiqb.boyka.api.requests.BookingDataBuilder;
import com.github.wasiqb.boyka.api.requests.PartialBookingData;
import com.github.wasiqb.boyka.api.requests.TokenBuilder;
import com.github.wasiqb.boyka.api.requests.Tokencreds;
import com.github.wasiqb.boyka.builders.ApiResponse;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * API Tests with RestfulBooker APIs.
 *
 * @author Faisal Khatri
 * @since 22/07/2022
 */
public class ApiTests {

    private static final String BOOKING_ID = "bookingid";

    private BookingData        newBooking;
    private PartialBookingData partialUpdateBooking;
    private Tokencreds         tokenCreds;
    private BookingData        updatedBooking;

    @BeforeTest (description = "Setting up the API tests")
    public void setupTest () {
        createSession (API, "test_restfulbooker");
        final BookingDataBuilder builder = new BookingDataBuilder ();
        final TokenBuilder buildToken = new TokenBuilder ();
        this.newBooking = builder.bookingDataBuilder ();
        this.updatedBooking = builder.bookingDataBuilder ();
        this.partialUpdateBooking = builder.partialBookingBuilder ();
        this.tokenCreds = buildToken.tokenBuilder ();
    }

    @AfterTest
    public void tearDownTest () {
        clearSession ();
    }

    @Test (description = "Test for creating new booking with POST request")
    public void testCreateBooking () {
        final var createBookingRequest = createRequest ().method (POST)
            .header ("Accept", "application/json")
            .path ("/booking")
            .bodyObject (this.newBooking)
            .create ();

        final ApiResponse response = withRequest (createBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("booking.lastname")
            .isEqualTo (this.newBooking.getLastname ());
        getSession ().setSharedData (BOOKING_ID, response.getResponseData (BOOKING_ID));
    }

    @Test (description = "Test for Deleting a booking using DELETE request")
    public void testDeleteBooking () {
        final var deleteBookingRequest = createRequest ().method (DELETE)
            .header ("Content-Type", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .pathParam ("id", getSession ().getSharedData (BOOKING_ID))
            .create ();

        final ApiResponse response = withRequest (deleteBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (201);
    }

    @Test (description = "Test for checking deleted booking using GET request")
    public void testDeletedBooking () {
        final var getDeletedBookingRequest = createRequest ().method (GET)
            .header ("Accept", "application/json")
            .path ("/booking/${id}")
            .pathParam ("id", getSession ().getSharedData (BOOKING_ID))
            .create ();

        final ApiResponse response = withRequest (getDeletedBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (404);
    }

    @Test (description = "Test for retrieving booking using GET request")
    public void testGetBooking () {
        final var getBookingRequest = createRequest ().method (GET)
            .header ("Accept", "application/json")
            .path ("/booking/${id}")
            .pathParam ("id", getSession ().getSharedData (BOOKING_ID))
            .create ();

        final ApiResponse response = withRequest (getBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (this.newBooking.getLastname ());
    }

    @Test (description = "Test for Updating booking using PUT request")
    public void testUpdateBooking () {
        final var updateBookingRequest = createRequest ().method (PUT)
            .header ("Accept", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .bodyObject (this.updatedBooking)
            .pathParam ("id", getSession ().getSharedData (BOOKING_ID))
            .create ();

        final ApiResponse response = withRequest (updateBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.updatedBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (this.updatedBooking.getLastname ());
    }

    @Test (description = "Test for partial updating booking using PATCH request")
    public void testUpdatePartialBooking () {
        final var partialUpdateBookingRequest = createRequest ().method (PATCH)
            .header ("Accept", "application/json")
            .header ("Cookie", format ("token={0}", generateToken ()))
            .path ("/booking/${id}")
            .bodyObject (this.partialUpdateBooking)
            .pathParam ("id", getSession ().getSharedData (BOOKING_ID))
            .create ();

        final ApiResponse response = withRequest (partialUpdateBookingRequest).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.partialUpdateBooking.getFirstname ());
        response.verifyIntField ("totalprice")
            .isEqualTo (this.partialUpdateBooking.getTotalprice ());
    }

    private String generateToken () {
        final var generateTokenRequest = createRequest ().header ("Accept", "application/json")
            .method (POST)
            .path ("/auth")
            .bodyObject (this.tokenCreds)
            .create ();

        final ApiResponse response = withRequest (generateTokenRequest).execute ();
        return response.getResponseData ("token");
    }
}
