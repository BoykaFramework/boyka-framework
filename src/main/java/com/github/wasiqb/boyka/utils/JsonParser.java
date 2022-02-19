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

public class JsonParser {
    private static final Gson GSON;

    static {
        GSON = new GsonBuilder ().setFieldNamingPolicy (LOWER_CASE_WITH_UNDERSCORES)
            .create ();
    }

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

    public static <T> void toFile (final T data, final String filePath) {
        try (final FileWriter writer = new FileWriter (filePath)) {
            GSON.toJson (data, writer);
        } catch (final IOException e) {
            throw new FrameworkError (format (ERROR_WRITING_FILE.getMessage (), filePath), e);
        }
    }

    private JsonParser () {
        // Util class.
    }
}
