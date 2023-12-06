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

package com.github.wasiqb.boyka.actions.interfaces.data;

/**
 * Parses data row from data source.
 *
 * @author Wasiq Bhamla
 * @since 19-Nov-2023
 */
public interface IDataRow {
    /**
     * Get cell data.
     *
     * @param index 1 based index of the cell.
     *
     * @return cell data.
     */
    <T> T cell (int index);

    /**
     * Get cell data.
     *
     * @param name Cell name.
     *
     * @return cell data.
     */
    <T> T cell (String name);

    /**
     * Checks if the row is empty.
     *
     * @return true, if row has all cells empty.
     */
    boolean isEmpty ();
}
