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

import static io.github.boykaframework.utils.StringUtils.interpolate;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * GSON String placeholder deserializer.
 *
 * @author Wasiq Bhamla
 * @since 04-Feb-2025
 */
class StringPlaceholderDeserializer implements JsonDeserializer<String> {
    @Override
    public String deserialize (final JsonElement jsonElement, final Type type,
        final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        final var placeholderValue = jsonElement.getAsString ();
        return interpolate (placeholderValue);
    }
}
