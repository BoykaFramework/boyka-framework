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

import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;

import com.github.wasiqb.boyka.testng.api.requests.BookingData;
import com.github.wasiqb.boyka.testng.api.requests.BookingDataBuilder;
import com.github.wasiqb.boyka.testng.api.requests.BookingRequest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
public class RestfulBookerEndToEndTests {
    private String             bookingId;
    private BookingDataBuilder dataBuilder;
    private BookingData        newBooking;

    @BeforeTest
    public void setup () {
        this.dataBuilder = new BookingDataBuilder ();
        this.newBooking = this.dataBuilder.bookingDataBuilder ();
    }

    @Test (description = "Test for creating new booking with POST request")
    public void testCreateBooking () {
        final var response = withRequest (BookingRequest.createBooking (this.newBooking)).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        this.bookingId = response.getResponseData ("bookingid");
    }

    @Test (description = "Test for Deleting a booking using DELETE request")
    public void testDeleteBooking () {
        final var response = withRequest (BookingRequest.deleteBooking (this.bookingId)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (201);
    }

    @Test (description = "Test for checking deleted booking using GET request")
    public void testDeletedBooking () {
        final var response = withRequest (BookingRequest.getBooking (this.bookingId)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (404);
    }

    @Test (description = "Test for retrieving booking using GET request")
    public void testGetBooking () {
        final var response = withRequest (BookingRequest.getBooking (this.bookingId)).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (this.newBooking.getLastname ());
    }

    @Test (description = "Test for Updating booking using PUT request")
    public void testUpdateBooking () {
        final var updateBookingData = this.dataBuilder.bookingDataBuilder ();

        final var response = withRequest (BookingRequest.updateBooking (this.bookingId, updateBookingData)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (updateBookingData.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (updateBookingData.getLastname ());
    }

    @Test (description = "Test for partial updating booking using PATCH request")
    public void testUpdatePartialBooking () {
        final var partialBookingData = this.dataBuilder.partialBookingBuilder ();

        final var response = withRequest (
            BookingRequest.updatePartialBooking (this.bookingId, partialBookingData)).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (partialBookingData.getFirstname ());
        response.verifyIntField ("totalprice")
            .isEqualTo (partialBookingData.getTotalprice ());
    }
}
