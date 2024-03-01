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

package io.github.boykaframework.parser;

import java.util.List;

/**
 * Process data from different sources.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
public interface IDataParser {
    /**
     * Parse data from source to class object.
     *
     * @param fileName Test data file name
     * @param blockName Test data block name
     * @param dataType Class data type.
     * @param <T> Type of data.
     *
     * @return Data object.
     */
    <T> List<T> parse (String fileName, String blockName, Class<T> dataType);

    /**
     * Parse data from source and get list of data.
     *
     * @param fileName Test data file name
     * @param blockName Test data block name
     *
     * @return List of test data.
     */
    List<Object[]> parse (String fileName, String blockName);
}
