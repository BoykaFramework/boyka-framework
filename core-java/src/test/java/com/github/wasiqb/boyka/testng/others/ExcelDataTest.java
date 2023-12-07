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

package com.github.wasiqb.boyka.testng.others;

import static com.github.wasiqb.boyka.actions.data.TestDataAction.withData;
import static com.google.common.truth.Truth.assertWithMessage;

import org.testng.annotations.Test;

/**
 * Test excel data handling.
 *
 * @author Wasiq Bhamla
 * @since 7-Dec-2023
 */
public class ExcelDataTest {
    /**
     * Test if all the data from Excel sheet is read correctly.
     */
    @Test
    public void testExcelData () {
        final String bookingFirstName = withData ("TestData").inBlock ("Bookings")
            .row (0)
            .cell ("FirstName");
        assertWithMessage ("Booking First Name").that (bookingFirstName)
            .isEqualTo ("Wasiq");

        final String employeeFirstName = withData ("TestData").inBlock ("Employees")
            .row (0)
            .cell ("FirstName");
        assertWithMessage ("Employee Rows Count").that (employeeFirstName)
            .isEqualTo ("Wasiq");
    }

    /**
     * Test if all the data from Excel sheet row is read correctly.
     */
    @Test
    public void testExcelDataColumnCount () {
        final var bookingCols = withData ("TestData").inBlock ("Bookings")
            .row (0)
            .cells ();
        assertWithMessage ("Booking Column Count").that (bookingCols.size ())
            .isEqualTo (8);

        final var employeeCols = withData ("TestData").inBlock ("Employees")
            .row (0)
            .cells ();
        assertWithMessage ("Employee Column Count").that (employeeCols.size ())
            .isEqualTo (7);
    }

    /**
     * Test if all the data from Excel sheet is read correctly.
     */
    @Test
    public void testExcelDataCount () {
        final var bookingRows = withData ("TestData").inBlock ("Bookings")
            .rows ();
        assertWithMessage ("Booking Rows Count").that (bookingRows.size ())
            .isEqualTo (2);

        final var employeeRows = withData ("TestData").inBlock ("Employees")
            .rows ();
        assertWithMessage ("Employee Rows Count").that (employeeRows.size ())
            .isEqualTo (3);
    }
}
