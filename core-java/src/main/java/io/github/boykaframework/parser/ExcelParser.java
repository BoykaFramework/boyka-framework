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

import static io.github.boykaframework.enums.Message.BLOCK_NAME_REQUIRED;
import static io.github.boykaframework.enums.Message.ERROR_CALLING_SETTER;
import static io.github.boykaframework.enums.Message.ERROR_NO_CTOR;
import static io.github.boykaframework.enums.Message.ERROR_READING_FILE;
import static io.github.boykaframework.enums.Message.ERROR_SETTER_NOT_FOUND;
import static io.github.boykaframework.enums.Message.PATH_NOT_DIRECTORY;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static io.github.boykaframework.utils.Validator.requireNonNull;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Arrays.stream;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.capitalize;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import io.github.boykaframework.utils.ErrorHandler;
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
            handleAndThrow (ERROR_NO_CTOR, e, dataType.getSimpleName ());
        }
        return List.of ();
    }

    @Override
    public List<Object[]> parse (final String fileName, final String blockName) {
        final var setting = getSession ().getTestDataSetting ();
        var filePath = Path.of (getProperty ("user.dir"), "src/test/resources", setting.getPath ())
            .toAbsolutePath ();
        if (setting.isExternal ()) {
            filePath = Path.of (setting.getPath ())
                .toAbsolutePath ();
        }
        if (!filePath.toFile ()
            .isDirectory ()) {
            throwError (PATH_NOT_DIRECTORY, filePath.toString ());
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
            case FORMULA, NUMERIC -> column.getNumericCellValue ();
            case STRING -> column.getStringCellValue ();
            case BOOLEAN -> column.getBooleanCellValue ();
            case _NONE, BLANK, ERROR -> null;
        };
    }

    private List<Object[]> getDataFromFile (final File dataFileName, final String blockName) {
        final List<Object[]> result = new ArrayList<> ();
        try (final var workbook = new XSSFWorkbook (dataFileName)) {
            final var sheet = workbook.getSheet (requireNonNull (blockName, BLOCK_NAME_REQUIRED));
            final var rowCount = sheet.getLastRowNum ();
            for (var rowIndex = sheet.getFirstRowNum (); rowIndex <= rowCount; rowIndex++) {
                final var row = sheet.getRow (rowIndex);
                final var colCount = row.getLastCellNum ();
                final var rowData = new Object[colCount];
                for (var colIndex = row.getFirstCellNum (); colIndex < colCount; colIndex++) {
                    final var column = row.getCell (colIndex);
                    final var value = getColumnData (column);
                    if (!isNull (value)) {
                        rowData[colIndex] = value;
                    }
                }
                if (!stream (rowData).allMatch (Objects::isNull)) {
                    result.add (rowData);
                }
            }
        } catch (final IOException | InvalidFormatException e) {
            handleAndThrow (ERROR_READING_FILE, e, dataFileName.getPath ());
        }
        return result;
    }

    private <T> List<T> getDataFromFile (final List<Object[]> data, final Constructor<T> dataCtor) {
        final List<T> result = new ArrayList<> ();
        final var headers = stream (data.get (0)).map (Object::toString)
            .toList ();
        String methodName;
        for (var row = 1; row < data.size (); row++) {
            var header = "";
            try {
                final var dataObject = dataCtor.newInstance ();
                final var rowData = data.get (row);
                for (var col = 0; col < headers.size (); col++) {
                    header = headers.get (col);
                    final var value = rowData[col];
                    methodName = format ("set{0}", capitalize (header));
                    if (!isNull (value)) {
                        setFieldValue (dataObject, value, methodName);
                    }
                }
                result.add (dataObject);
            } catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
                ErrorHandler.handleAndThrow (ERROR_CALLING_SETTER, e, format ("set{0}", capitalize (header)), dataCtor.getClass ()
                    .getSimpleName ());
            }
        }
        return result;
    }

    private <T> void setFieldValue (final T dataObject, final Object value, final String methodName) {
        final var dataClass = dataObject.getClass ();
        try {
            final Method method;
            if (value instanceof String) {
                method = dataClass.getMethod (methodName, String.class);
                method.invoke (dataObject, value.toString ());
            } else if (value instanceof Integer) {
                method = dataClass.getMethod (methodName, Integer.class);
                method.invoke (dataObject, Integer.parseInt (value.toString ()));
            } else if (value instanceof Double) {
                method = dataClass.getMethod (methodName, Double.class);
                method.invoke (dataObject, Double.parseDouble (value.toString ()));
            }
        } catch (final IllegalAccessException | InvocationTargetException e) {
            ErrorHandler.handleAndThrow (ERROR_CALLING_SETTER, e, methodName, dataClass.getSimpleName ());
        } catch (final NoSuchMethodException e) {
            ErrorHandler.handleAndThrow (ERROR_SETTER_NOT_FOUND, e, methodName, dataClass.getSimpleName (), value.getClass ()
                .getSimpleName ());
        }
    }
}
