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

package com.github.wasiqb.boyka.api.requests;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Faker;

/**
 * This class provides data for creating and updating booking
 *
 * @author Faisal Khatri
 * @since 22/07/2022
 */
public class BookingDataBuilder {
    public BookingData bookingDataBuilder () {
        final var faker = Faker.instance ();
        final var formatter = new SimpleDateFormat ("YYYY-MM-dd");
        return BookingData.builder ()
            .firstname (faker.name ()
                .firstName ())
            .lastname (faker.name ()
                .lastName ())
            .totalprice (faker.number ()
                .numberBetween (1, 2000))
            .depositpaid (true)
            .bookingdates (BookingDates.builder ()
                .checkin (formatter.format (faker.date ()
                    .past (20, TimeUnit.DAYS)))
                .checkout (formatter.format (faker.date ()
                    .future (5, TimeUnit.DAYS)))
                .build ())
            .additionalneeds ("Breakfast")
            .build ();
    }

    public PartialBookingData partialBookingBuilder () {
        final var faker = Faker.instance ();
        return PartialBookingData.builder ()
            .firstname (faker.name ()
                .firstName ())
            .totalprice (faker.number ()
                .numberBetween (100, 5000))
            .build ();
    }
}
