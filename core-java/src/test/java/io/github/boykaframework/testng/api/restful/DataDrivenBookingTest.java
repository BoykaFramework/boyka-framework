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

package io.github.boykaframework.testng.api.restful;

import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;

import java.text.DecimalFormat;

import io.github.boykaframework.actions.api.ApiActions;
import io.github.boykaframework.actions.interfaces.data.IDataRow;
import io.github.boykaframework.builders.ApiRequest;
import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.enums.RequestMethod;
import io.github.boykaframework.testng.api.restful.data.BookingDataProviders;
import io.github.boykaframework.testng.api.restful.pojo.BookingData;
import io.github.boykaframework.testng.api.restful.pojo.BookingDates;
import io.github.boykaframework.testng.api.restful.pojo.BookingTestData;
import lombok.SneakyThrows;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Booking tests driven by Test data Excel file.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
public class DataDrivenBookingTest {
    @BeforeClass
    public void setupClass () {
        createSession (PlatformType.API, "test_restfulbooker");
    }

    @AfterClass
    public void tearDownTestClass () {
        clearSession ();
    }

    @SneakyThrows
    @Test (dataProviderClass = BookingDataProviders.class, dataProvider = "getBookingData")
    public void testBooking (final IDataRow row) {
        final var depositPaid = row.cell ("DepositPaid")
            .toString ()
            .equalsIgnoreCase ("yes");
        final var formatter = new DecimalFormat ("#0.0");
        final var bookingData = BookingData.builder ()
            .firstname (row.cell ("FirstName"))
            .lastname (row.cell ("LastName"))
            .totalprice (formatter.parse (row.cell ("TotalPrice")
                    .toString ())
                .intValue ())
            .depositpaid (depositPaid)
            .bookingdates (BookingDates.builder ()
                .checkin (row.cell ("CheckInDate")
                    .toString ())
                .checkout (row.cell ("CheckOutDate")
                    .toString ())
                .build ())
            .additionalneeds (row.cell ("AdditionalNeeds"))
            .build ();

        testBooking (bookingData);
    }

    @Test (dataProviderClass = BookingDataProviders.class, dataProvider = "getBookingDataObject")
    public void testBookingObject (final BookingTestData bookingTestData) {
        final var depositPaid = bookingTestData.getDepositPaid ();
        final var bookingData = BookingData.builder ()
            .firstname (bookingTestData.getFirstName ())
            .lastname (bookingTestData.getLastName ())
            .totalprice (bookingTestData.getTotalPrice ()
                .intValue ())
            .depositpaid (Boolean.parseBoolean (depositPaid))
            .bookingdates (BookingDates.builder ()
                .checkin (bookingTestData.getCheckInDate ())
                .checkout (bookingTestData.getCheckOutDate ())
                .build ())
            .additionalneeds (bookingTestData.getAdditionalNeeds ())
            .build ();

        testBooking (bookingData);
    }

    private void testBooking (final BookingData bookingData) {
        final var request = ApiRequest.createRequest ()
            .method (RequestMethod.POST)
            .header ("Accept", "application/json")
            .path ("/booking")
            .bodyObject (bookingData)
            .create ();

        final var response = ApiActions.withRequest (request)
            .execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyStatusMessage ()
            .isEqualTo ("OK");
        response.verifySchema ("create-booking-schema.json");
        response.verifyTextField ("bookingid")
            .isNotNull ();
        response.verifyTextField ("booking.firstname")
            .isEqualTo (bookingData.getFirstname ());
        response.verifyBooleanField ("booking.depositpaid")
            .isEqualTo (bookingData.isDepositpaid ());
        response.verifyHeader ("Content-Type")
            .isEqualTo ("application/json; charset=utf-8");
    }
}
