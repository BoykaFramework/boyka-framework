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

package io.github.boykaframework.testng.api.restful;

import static io.github.boykaframework.actions.api.ApiActions.withRequest;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.testng.api.restful.data.BookingRequestData.getBookingData;
import static io.github.boykaframework.testng.api.restful.requests.BookingRequest.createBooking;
import static io.github.boykaframework.testng.api.restful.requests.BookingRequest.deleteBooking;
import static io.github.boykaframework.testng.api.restful.requests.BookingRequest.getBooking;

import io.github.boykaframework.exception.FrameworkError;
import io.github.boykaframework.testng.api.restful.data.BookingRequestData;
import io.github.boykaframework.testng.api.restful.pojo.BookingData;
import io.github.boykaframework.testng.api.restful.requests.BookingRequest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
@Epic ("API Test")
@Feature ("Restful Booker Test class")
public class RestfulBookerEndToEndTests {
    private static final String BOOKING_ID = "bookingId";

    private BookingData newBooking;

    /**
     * Setup API Test.
     */
    @Description ("Setup Test Class")
    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        createSession (API, "test_restfulbooker");
        this.newBooking = getBookingData ();
    }

    /**
     * Clean up Test class.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        clearSession ();
    }

    @Story ("Create Booking with POST request")
    @Test (description = "Test for creating new booking with POST request")
    public void testCreateBooking () {
        final var request = createBooking (this.newBooking);
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-schema.json");
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyBooleanField ("booking.depositpaid")
            .isTrue ();
        response.verifyHeader ("Content-Type")
            .isEqualTo ("application/json; charset=utf-8");

        final var bookingId = response.getResponseData ("bookingid");
        getSession ().setSharedData (BOOKING_ID, bookingId);
    }

    @Story ("Delete Booking")
    @Test (description = "Test for Deleting a booking using DELETE request")
    public void testDeleteBooking () {
        final var request = deleteBooking (getSession ().getSharedData (BOOKING_ID));
        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (201);
    }

    @Story ("Get deleted Booking")
    @Test (description = "Test for checking deleted booking using GET request")
    public void testDeletedBooking () {
        final var request = getBooking (getSession ().getSharedData (BOOKING_ID));
        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (404);
    }

    @Story ("Get Created Booking")
    @Test (description = "Test for retrieving booking using GET request")
    public void testGetBooking () {
        final var request = getBooking (getSession ().getSharedData (BOOKING_ID));
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (this.newBooking.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (this.newBooking.getLastname ());
    }

    @Story ("Json Validation Exception tests")
    @Test (description = "Tests for file not found exception", expectedExceptions = FrameworkError.class)
    public void testJsonSchemaFileException () {
        final var request = createBooking (this.newBooking);
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-scheme.json");
    }

    @Story ("Update Booking")
    @Test (description = "Test for Updating booking using PUT request")
    public void testUpdateBooking () {
        final var updateBookingData = this.newBooking;

        final var request = BookingRequest.updateBooking (getSession ().getSharedData (BOOKING_ID), updateBookingData);
        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (updateBookingData.getFirstname ());
        response.verifyTextField ("lastname")
            .isEqualTo (updateBookingData.getLastname ());
    }

    @Story ("Update Partial Booking")
    @Test (description = "Test for partial updating booking using PATCH request")
    public void testUpdatePartialBooking () {
        final var partialBookingData = BookingRequestData.getPartialBookingData ();

        final var request = BookingRequest.updatePartialBooking (getSession ().getSharedData (BOOKING_ID),
            partialBookingData);
        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("firstname")
            .isEqualTo (partialBookingData.getFirstname ());
        response.verifyIntField ("totalprice")
            .isEqualTo (partialBookingData.getTotalprice ());
    }
}
