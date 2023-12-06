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

package com.github.wasiqb.boyka.actions.data;

import static com.github.wasiqb.boyka.utils.Validator.checkIndex;
import static java.util.Arrays.stream;

import java.util.Objects;

import com.github.wasiqb.boyka.actions.interfaces.data.IDataRow;

/**
 * Gets test data in data row.
 *
 * @author Wasiq Bhamla
 * @since 28-Nov-2023
 */
class DataRow implements IDataRow {
    private final Object[] headers;
    private final Object[] rowData;

    DataRow (final Object[] headers, final Object[] rowData) {
        this.headers = headers;
        this.rowData = rowData;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public <T> T cell (final int index) {
        checkIndex (index, this.rowData.length);
        return (T) this.rowData[index];
    }

    @Override
    public <T> T cell (final String name) {
        var colIndex = 0;
        for (var index = 0; index < this.headers.length; index++) {
            if (this.headers[index].equals (name)) {
                colIndex = index;
                break;
            }
        }
        return cell (colIndex);
    }

    @Override
    public boolean isEmpty () {
        return stream (this.rowData).allMatch (Objects::isNull);
    }
}
