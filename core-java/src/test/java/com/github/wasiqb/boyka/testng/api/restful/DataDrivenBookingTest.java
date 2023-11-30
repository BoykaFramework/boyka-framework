/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.api.restful;

import static com.github.wasiqb.boyka.actions.api.ApiActions.withRequest;
import static com.github.wasiqb.boyka.actions.data.TestDataAction.withData;
import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.PlatformType.API;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;

import java.util.Iterator;

import com.github.wasiqb.boyka.actions.interfaces.data.IDataRow;
import com.github.wasiqb.boyka.enums.RequestMethod;
import com.github.wasiqb.boyka.testng.api.restful.requests.BookingData;
import com.github.wasiqb.boyka.testng.api.restful.requests.BookingDates;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Booking tests driven by Test data Excel file.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
public class DataDrivenBookingTest {
    private static final String BOOKING_ID = "bookingId";

    @DataProvider
    public Iterator<Object[]> getBookingData () {
        final var rows = withData ("BookingData").inBlock ("Bookings")
            .rows ();
        return rows.stream ()
            .map (d -> new Object[] { d })
            .toList ()
            .iterator ();
    }

    @BeforeClass
    public void setupClass () {
        createSession (API, "test_restfulbooker");
    }

    @AfterClass
    public void tearDownTestClass () {
        clearSession ();
    }

    @Test (dataProvider = "getBookingData")
    public void testBooking (final IDataRow row) {
        final var depositPaid = row.cell ("DepositPaid")
            .toString ()
            .equalsIgnoreCase ("yes");
        final var bookingData = BookingData.builder ()
            .firstname (row.cell ("FirstName"))
            .lastname (row.cell ("LastName"))
            .totalprice (row.cell ("TotalPrice"))
            .depositpaid (depositPaid)
            .bookingdates (BookingDates.builder ()
                .checkin (row.cell ("CheckInDate")
                    .toString ())
                .checkout (row.cell ("CheckOutDate")
                    .toString ())
                .build ())
            .additionalneeds (row.cell ("AdditionalNeeds"))
            .build ();

        final var request = createRequest ().method (RequestMethod.POST)
            .header ("Accept", "application/json")
            .path ("/booking")
            .bodyObject (bookingData)
            .create ();

        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-schema.json");
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (row.cell ("FirstName"));
        response.verifyBooleanField ("booking.depositpaid")
            .isEqualTo (depositPaid);
        response.verifyHeader ("Content-Type")
            .isEqualTo ("application/json; charset=utf-8");
        final var bookingId = response.getResponseData ("bookingid");
        getSession ().setSharedData (BOOKING_ID, bookingId);
    }
}
