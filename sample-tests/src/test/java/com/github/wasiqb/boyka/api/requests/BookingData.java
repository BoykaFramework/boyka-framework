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

import lombok.Builder;
import lombok.Getter;

/**
 * Fields for creating new booking.
 *
 * @author Faisal Khatri
 * @since 22/07/2022
 */
@Getter
@Builder
public class BookingData {
    private String       additionalneeds;
    private BookingDates bookingdates;
    private boolean      depositpaid;
    private String       firstname;
    private String       lastname;
    private int          totalprice;
}
