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

package com.github.wasiqb.boyka.parser;

import static com.github.wasiqb.boyka.enums.Message.ERROR_CALLING_SETTER;
import static com.github.wasiqb.boyka.enums.Message.ERROR_NO_CTOR;
import static com.github.wasiqb.boyka.enums.Message.ERROR_READING_FILE;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Parses Excel file.
 *
 * @author Wasiq Bhamla
 * @since 26-Nov-2023
 */
public class ExcelParser implements IDataParser {
    @Override
    public <T> List<T> parse (final String fileName, final String blockName, final Class<T> dataType) {
        final var dataSet = parse (fileName, blockName);
        try {
            final var ctor = dataType.getConstructor ();
            return getDataFromFile (dataSet, ctor);
        } catch (final NoSuchMethodException e) {
            throwError (ERROR_NO_CTOR, dataType.getSimpleName ());
        }
        return List.of ();
    }

    @Override
    public List<Object[]> parse (final String fileName, final String blockName) {
        final var setting = getSession ().getSetting ()
            .getData ();
        var filePath = Path.of (getProperty ("user.dir"), "src/test/resources", setting.getPath ())
            .toAbsolutePath ();
        if (setting.isExternal ()) {
            filePath = Path.of (setting.getPath ())
                .toAbsolutePath ();
        }
        final var dataFileName = Path.of (filePath.toString (), format ("{0}.{1}", fileName, setting.getExtension ()))
            .toFile ();
        return getDataFromFile (dataFileName, blockName);
    }

    private Object getColumnData (final XSSFCell column) {
        if (isNull (column)) {
            return null;
        }
        return switch (column.getCellType ()) {
            case NUMERIC -> column.getNumericCellValue ();
            case STRING -> column.getStringCellValue ();
            case BOOLEAN -> column.getBooleanCellValue ();
            case _NONE, FORMULA, BLANK, ERROR -> null;
        };
    }

    private List<Object[]> getDataFromFile (final File dataFileName, final String blockName) {
        final List<Object[]> result = new ArrayList<> ();
        try (final var workbook = new XSSFWorkbook (dataFileName)) {
            final var sheet = workbook.getSheet (blockName);
            final var rowCount = sheet.getPhysicalNumberOfRows ();
            for (var rowIndex = 0; rowIndex < rowCount; rowIndex++) {
                final var row = sheet.getRow (rowIndex);
                final var colCount = row.getPhysicalNumberOfCells ();
                final var rowData = new Object[colCount];
                for (var colIndex = 0; colIndex < colCount; colIndex++) {
                    final var column = row.getCell (colIndex);
                    final var value = getColumnData (column);
                    if (!isNull (value)) {
                        rowData[colIndex] = value;
                    }
                }
                result.add (rowData);
            }
        } catch (final IOException | InvalidFormatException e) {
            throwError (ERROR_READING_FILE, dataFileName.getPath ());
        }
        return result;
    }

    private <T> List<T> getDataFromFile (final List<Object[]> data, final Constructor<T> dataCtor) {
        final List<T> result = new ArrayList<> ();
        final var headers = stream (data.get (0)).map (Object::toString)
            .toList ();
        for (var row = 1; row < data.size (); row++) {
            var header = "";
            try {
                final var dataObject = dataCtor.newInstance ();
                for (var col = 0; col < headers.size (); col++) {
                    header = headers.get (col);
                    final var value = data.get (row)[col];
                    final var setter = dataObject.getClass ()
                        .getMethod (format ("set{0}", capitalize (header)), String.class);
                    setter.invoke (dataObject, value.toString ());
                }
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException |
                           NoSuchMethodException e) {
                throwError (ERROR_CALLING_SETTER, format ("set{0}", capitalize (header)), dataCtor.getClass ()
                    .getSimpleName ());
            }
        }
        return result;
    }
}
