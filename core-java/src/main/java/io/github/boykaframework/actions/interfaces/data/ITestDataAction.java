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

package io.github.boykaframework.actions.interfaces.data;

import java.util.List;

/**
 * Handle test data from different sources.
 *
 * @author Wasiq Bhamla
 * @since 19-Nov-2023
 */
public interface ITestDataAction {
    /**
     * Gets the list test data objects.
     *
     * @param dataClass Data class object.
     * @param <T> Type of test data class.
     *
     * @return List of data objects
     */
    <T> List<T> get (Class<T> dataClass);

    /**
     * Process test data in a particular block or sheet in the test data file.
     *
     * @param blockName Block name of test data
     *
     * @return {@link ITestDataAction} instance
     */
    ITestDataAction inBlock (String blockName);

    /**
     * Gets data row.
     *
     * @param index Row index
     *
     * @return Test data row.
     */
    IDataRow row (int index);

    /**
     * Gets all the rows.
     *
     * @return List of rows.
     */
    List<IDataRow> rows ();
}
