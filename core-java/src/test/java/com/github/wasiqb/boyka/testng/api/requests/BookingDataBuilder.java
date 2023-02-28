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

package com.github.wasiqb.boyka.testng.api.requests;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import net.datafaker.Faker;

/**
 * Booking Data builder class.
 *
 * @author Wasiq Bhamla
 * @since 28-Feb-2023
 */
public class BookingDataBuilder {
    private final Faker faker = new Faker ();

    /**
     * Build booking data
     *
     * @return {@link BookingData} instance
     */
    public BookingData bookingDataBuilder () {
        final var formatter = new SimpleDateFormat ("yyyy-MM-dd");
        return BookingData.builder ()
            .firstname (this.faker.name ()
                .firstName ())
            .lastname (this.faker.name ()
                .lastName ())
            .totalprice (this.faker.number ()
                .numberBetween (1, 2000))
            .depositpaid (true)
            .bookingdates (BookingDates.builder ()
                .checkin (formatter.format (this.faker.date ()
                    .past (20, TimeUnit.DAYS)))
                .checkout (formatter.format (this.faker.date ()
                    .future (5, TimeUnit.DAYS)))
                .build ())
            .additionalneeds ("Breakfast")
            .build ();
    }

    /**
     * Build partial booking
     *
     * @return {@link PartialBookingData} instance
     */
    public PartialBookingData partialBookingBuilder () {
        return PartialBookingData.builder ()
            .firstname (this.faker.name ()
                .firstName ())
            .totalprice (this.faker.number ()
                .numberBetween (100, 5000))
            .build ();
    }
}
