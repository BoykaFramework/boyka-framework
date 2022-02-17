package com.github.wasiqb.boyka.utils;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder ().setFieldNamingPolicy (LOWER_CASE_WITH_UNDERSCORES)
            .create ();
    }

    @SuppressWarnings ("UnstableApiUsage")
    public static <T> T fromFile (final String filePath) {
        try (final Reader reader = new FileReader (filePath)) {
            return GSON.fromJson (reader, new TypeToken<T> () {
            }.getType ());
        } catch (final IOException e) {
            e.printStackTrace ();
        }
        return null;
    }

    public static <T> void toFile (final T data, final String filePath) {
        try (final FileWriter writer = new FileWriter (filePath)) {
            GSON.toJson (data, writer);
        } catch (final IOException e) {
            e.printStackTrace ();
        }
    }

    private JsonParser () {
        // Util class.
    }
}
