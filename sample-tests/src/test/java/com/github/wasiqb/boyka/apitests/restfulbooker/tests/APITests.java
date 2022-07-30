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

package com.github.wasiqb.boyka.apitests.restfulbooker.tests;

import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.DELETE;
import static com.github.wasiqb.boyka.enums.RequestMethod.PATCH;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.enums.RequestMethod.PUT;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.apitests.restfulbooker.requests.PartialBookingData;
import com.github.wasiqb.boyka.apitests.restfulbooker.requests.TokenBuilder;
import com.github.wasiqb.boyka.apitests.restfulbooker.requests.Tokencreds;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.apitests.restfulbooker.requests.BookingData;
import com.github.wasiqb.boyka.apitests.restfulbooker.requests.BookingDataBuilder;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Api Tests with RestfulBooker.
 *
 * @author Faisal Khatri
 * @since 22/07/2022
 */
public class APITests {

    private static final String             API_CONFIG_KEY = "test_restfulbooker";
    private              String             bookingId;
    private              BookingData        newBooking;
    private BookingData        updatedBooking;
    private Tokencreds         tokenCreds;
    private PartialBookingData partialUpdateBooking;

    @BeforeTest
    public void setupTest () {
        final BookingDataBuilder builder = new BookingDataBuilder ();
        TokenBuilder buildToken = new TokenBuilder ();
        this.newBooking = builder.bookingDataBuilder ();
        updatedBooking = builder.bookingDataBuilder ();
        partialUpdateBooking = builder.partialBookingBuilder ();
        tokenCreds = buildToken.tokenBuilder ();
    }

    @Test
    public void testCreateBooking () {
        final var createBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (POST)
            .header ("Accept", "application/json")
            .path ("/booking")  // FIXME: Needs to make Path optional.
            .bodyObject (this.newBooking)
            .create ();

        final ApiResponse response = execute (createBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("booking.lastname")
            .isEqualTo (this.newBooking.getLastname ());
        bookingId = response.getResponseData ("bookingid");
    }
    @Test
    public void testGetBooking () {
        final var getBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (GET)
            .header ("Accept", "application/json")
            .path ("/booking/${id}")
            .pathParam ("id", bookingId)
            .create ();

        final ApiResponse response = execute (getBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (newBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (newBooking.getLastname ());
    }
    @Test
    public void testUpdateBooking () {
        final var updateBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (PUT)
            .header ("Accept", "application/json")
            .header ("Cookie", "token=" + generateToken ())
            .path ("/booking/${id}")
            .bodyObject (updatedBooking)
            .pathParam ("id", bookingId)
            .create ();

        final ApiResponse response = execute (updateBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (updatedBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (updatedBooking.getLastname ());
    }

    @Test
    public void testUpdatePartialBooking () {
        final var partialUpdateBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (PATCH)
            .header ("Accept", "application/json")
            .header ("Cookie", "token=" + generateToken ())
            .path ("/booking/${id}")
            .bodyObject (partialUpdateBooking)
            .pathParam ("id", bookingId)
            .create ();

        final ApiResponse response = execute (partialUpdateBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (partialUpdateBooking.getFirstname ());
        response.verifyIntField ("totalprice")
            .isEqualTo (partialUpdateBooking.getTotalprice ());
    }

    @Test
    public void testDeleteBooking () {
        final var partialUpdateBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (DELETE)
            .header ("Accept", "application/json")
            .header ("Cookie", "token=" + generateToken ())
            .path ("/booking/${id}")
            .pathParam ("id", bookingId)
            .create ();

        final ApiResponse response = execute (partialUpdateBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (201);
    }
    @Test
    public void testDeletedBooking () {
        final var getDeletedBookingRequest = createRequest ().configKey (API_CONFIG_KEY)
            .method (GET)
            .header ("Accept", "application/json")
            .path ("/booking/${id}")
            .pathParam ("id", bookingId)
            .create ();

        final ApiResponse response = execute (getDeletedBookingRequest);
        response.verifyStatusCode ()
            .isEqualTo (404);
    }

    private String generateToken () {
        final var generateTokenRequest = createRequest ().configKey (API_CONFIG_KEY)
            .header ("Accept", "application/json")
            .method (POST)
            .path ("/auth")
            .bodyObject (tokenCreds)
            .create ();

        ApiResponse response = execute (generateTokenRequest);
        return response.getResponseData ("token");
    }
}
