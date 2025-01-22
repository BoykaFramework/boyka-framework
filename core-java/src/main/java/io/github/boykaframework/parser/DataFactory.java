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

package io.github.boykaframework.parser;

import static io.github.boykaframework.manager.ParallelSession.getSession;

import io.github.boykaframework.enums.TestDataSource;

/**
 * Test Data factory.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
public final class DataFactory {
    /**
     * Get Test data parser.
     *
     * @return {@link IDataParser} instance.
     */
    public static IDataParser getParser () {
        final var setting = getSession ().getSetting ()
            .getData ();
        if (setting.getType () == TestDataSource.EXCEL) {
            return new ExcelParser ();
        }
        return null;
    }

    private DataFactory () {
        // Utility class.
    }
}
