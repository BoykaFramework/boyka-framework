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

package io.github.boykaframework.testng.api.restful.data;

import static io.github.boykaframework.actions.data.TestDataAction.withData;

import java.util.Iterator;

import io.github.boykaframework.actions.interfaces.data.ITestDataAction;
import io.github.boykaframework.testng.api.restful.pojo.BookingTestData;
import org.testng.annotations.DataProvider;

/**
 * Contains Data providers for the Tests
 *
 * @author Wasiq Bhamla
 * @since 23-Feb-2024
 */
public final class BookingDataProviders {
    private static final ITestDataAction DATA;

    static {
        DATA = withData ("BookingData").inBlock ("Bookings");
    }

    @DataProvider
    public static Iterator<Object[]> getBookingData () {
        final var rows = DATA.rows ();
        return rows.stream ()
            .map (d -> new Object[] { d })
            .toList ()
            .iterator ();
    }

    @DataProvider
    public static Iterator<Object[]> getBookingDataObject () {
        final var rows = DATA.get (BookingTestData.class);
        return rows.stream ()
            .map (d -> new Object[] { d })
            .toList ()
            .iterator ();
    }

    private BookingDataProviders () {
        // Utility Class.
    }
}
