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
     * @param <T> Type of cell data
     *
     * @return cell data.
     */
    <T> T cell (int index);

    /**
     * Get cell data.
     *
     * @param name Cell name.
     * @param <T> Type of cell data
     *
     * @return cell data.
     */
    <T> T cell (String name);

    /**
     * Get all the cells in the row.
     *
     * @param <T> Type of cell data
     *
     * @return List of cells.
     */
    <T> List<T> cells ();

    /**
     * Checks if the row is empty.
     *
     * @return true, if row has all cells empty.
     */
    boolean isEmpty ();
}
