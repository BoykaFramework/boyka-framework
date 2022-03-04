/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.enums.Messages.ERROR_READING_FILE;
import static com.github.wasiqb.boyka.enums.Messages.ERROR_WRITING_FILE;
import static com.github.wasiqb.boyka.enums.Messages.NO_JSON_FILE_FOUND;
import static com.google.common.reflect.TypeToken.of;
import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static java.text.MessageFormat.format;
import static java.util.Objects.requireNonNull;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.github.wasiqb.boyka.exception.FrameworkError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Parser class to read and write json files.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public class JsonParser {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder ().setFieldNamingPolicy (LOWER_CASE_WITH_UNDERSCORES)
            .create ();
    }

    /**
     * Reads the JSON file.
     *
     * @param filePath the file path to be read
     * @param objectClass the class of the object where data will be saved
     * @param <T> the type of the object
     *
     * @return the object instance
     */
    @SuppressWarnings ("UnstableApiUsage")
    public static <T> T fromFile (final String filePath, final Class<T> objectClass) {
        final var path = requireNonNull (SettingUtils.class.getClassLoader ()
            .getResource (filePath), NO_JSON_FILE_FOUND.getMessage ());
        try (final Reader reader = new FileReader (path.getPath ())) {
            return GSON.fromJson (reader, of (objectClass).getType ());
        } catch (final IOException e) {
            throw new FrameworkError (format (ERROR_READING_FILE.getMessage (), path.getPath ()), e);
        }
    }

    /**
     * Writes the object to the JSON file.
     *
     * @param data the object to be written
     * @param filePath the file path to be written
     * @param <T> the type of the object
     */
    public static <T> void toFile (final T data, final String filePath) {
        try (final FileWriter writer = new FileWriter (filePath)) {
            GSON.toJson (data, writer);
        } catch (final IOException e) {
            throw new FrameworkError (format (ERROR_WRITING_FILE.getMessage (), filePath), e);
        }
    }

    /**
     * Converts the object to JSON.
     *
     * @param data the object to be converted
     * @param <T> the type of the object
     *
     * @return the JSON string
     */
    public static <T> String toString (final T data) {
        return GSON.toJson (data);
    }

    private JsonParser () {
        // Utility class.
    }
}
