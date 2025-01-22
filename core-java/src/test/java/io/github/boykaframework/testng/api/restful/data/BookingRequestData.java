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

package io.github.boykaframework.testng.api.restful.data;

import java.util.concurrent.TimeUnit;

import io.github.boykaframework.testng.api.restful.pojo.BookingData;
import io.github.boykaframework.testng.api.restful.pojo.BookingDates;
import net.datafaker.Faker;

/**
 * Booking Data builder class.
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
public final class BookingRequestData {
    private static final Faker FAKER = new Faker ();

    /**
     * Build booking data
     *
     * @return {@link BookingData} instance
     */
    public static BookingData getBookingData () {
        final var dateTime = FAKER.timeAndDate ();
        return BookingData.builder ()
            .firstname (FAKER.name ()
                .firstName ())
            .lastname (FAKER.name ()
                .lastName ())
            .totalprice (FAKER.number ()
                .numberBetween (1, 2000))
            .depositpaid (true)
            .bookingdates (BookingDates.builder ()
                .checkin (dateTime.past (20, TimeUnit.DAYS)
                    .toString ())
                .checkout (dateTime.future (5, TimeUnit.DAYS)
                    .toString ())
                .build ())
            .additionalneeds ("Breakfast")
            .build ();
    }

    /**
     * Build partial booking
     *
     * @return {@link BookingData} instance
     */
    public static BookingData getPartialBookingData () {
        return BookingData.builder ()
            .firstname (FAKER.name ()
                .firstName ())
            .totalprice (FAKER.number ()
                .numberBetween (100, 5000))
            .build ();
    }

    private BookingRequestData () {
        // Utility Class.
    }
}
