/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

package io.github.boykaframework.utils;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;
import static io.github.boykaframework.utils.StringUtils.interpolate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

/**
 * GSON Map placeholder deserializer.
 *
 * @author Wasiq Bhamla
 * @since 04-Feb-2025
 */
class MapPlaceholderDeserializer implements JsonDeserializer<Map<String, Object>> {
    @Override
    public Map<String, Object> deserialize (final JsonElement jsonElement, final Type type,
        final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var map = jsonElement.getAsJsonObject ();
        final var gson = new GsonBuilder ().setFieldNamingPolicy (LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting ()
            .create ();
        final var parsedMap = interpolateMapValues (map);
        final var parsedJson = gson.toJson (parsedMap);
        return gson.fromJson (parsedJson, type);
    }

    private void interpolateArrayValues (final Map<String, Object> result, final String key,
        final JsonArray jsonArray) {
        final List<Object> list = new ArrayList<> ();
        jsonArray.forEach (jsonElement -> list.add (interpolate (jsonElement.getAsString ())));
        result.put (key, list);
    }

    private Map<String, Object> interpolateMapValues (final JsonObject map) {
        final Map<String, Object> result = new HashMap<> ();
        map.entrySet ()
            .forEach (entry -> {
                final var key = entry.getKey ();
                final var value = entry.getValue ();
                if (value.isJsonObject ()) {
                    result.put (key, interpolateMapValues (value.getAsJsonObject ()));
                } else if (value.isJsonArray ()) {
                    interpolateArrayValues (result, key, value.getAsJsonArray ());
                } else if (value.isJsonPrimitive ()) {
                    interpolatePrimitive (result, key, value.getAsJsonPrimitive ());
                } else if (value.isJsonNull ()) {
                    result.put (key, value.getAsJsonNull ());
                }
            });
        return result;
    }

    private void interpolatePrimitive (final Map<String, Object> result, final String key, final JsonPrimitive value) {
        if (value.isBoolean ()) {
            result.put (key, value.getAsBoolean ());
        } else if (value.isNumber ()) {
            result.put (key, value.getAsNumber ());
        } else if (value.isString ()) {
            result.put (key, interpolate (value.getAsString ()));
        } else if (value.isJsonNull ()) {
            result.put (key, value.getAsJsonNull ());
        }
    }
}
