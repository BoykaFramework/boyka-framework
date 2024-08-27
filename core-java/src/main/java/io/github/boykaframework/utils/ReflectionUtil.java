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

package io.github.boykaframework.utils;

import static io.github.boykaframework.enums.Message.METHOD_NOT_FOUND;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isEmpty;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.github.boykaframework.config.BoykaConfig;

/**
 * Handles Java reflection related helpers
 *
 * @author Wasiq Bhamla
 * @since 26-Aug-2024
 */
public final class ReflectionUtil {
    /**
     * Replaces any null or missing field value with the default object
     *
     * @param source Source object to scan
     * @param defaultObject Default object from where missing values is to be replaced from
     * @param <T> Object type
     *
     * @return New object with all the null and missing fields replaced with default
     */
    public static <T> T replaceEmptyWithCommon (final T source, final T defaultObject) {
        final var sourceMethods = getMethods (source);

        for (final var sourceMethod : sourceMethods) {
            if (sourceMethod.getName ()
                .startsWith ("get")) {
                try {
                    final var defaultMethod = getMethod (sourceMethod, defaultObject);
                    final var sourceValue = getMethodValue (sourceMethod, source);
                    final var defaultValue = getMethodValue (defaultMethod, defaultObject);

                    if (shouldReplaceValue (sourceValue, defaultValue)) {
                        setMethodValue (sourceMethod, source, defaultValue);
                    } else if (sourceValue instanceof BoykaConfig) {
                        replaceEmptyWithCommon (sourceValue, defaultValue);
                    }
                } catch (final NoSuchMethodException e) {
                    handleAndThrow (METHOD_NOT_FOUND, e, sourceMethod.getName (), defaultObject.getClass ()
                        .getSimpleName ());
                }
            }
        }
        return source;
    }

    private static <T> Method getMethod (final Method method, final T object) throws NoSuchMethodException {
        return object.getClass ()
            .getDeclaredMethod (method.getName ());
    }

    @SuppressWarnings ("unchecked")
    private static <T, E> E getMethodValue (final Method method, final T object) {
        if (method.getName ()
            .startsWith ("get")) {
            try {
                return (E) method.invoke (object);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                handleAndThrow (METHOD_NOT_FOUND, e, method.getName ());
            }
        }
        return null;
    }

    private static <T> Method[] getMethods (final T object) {
        return object.getClass ()
            .getDeclaredMethods ();
    }

    private static <T> void setMethodValue (final Method method, final T object, final T value) {
        if (!method.getName ()
            .startsWith ("get") || isNull (value)) {
            return;
        }

        final var setMethodName = "set" + method.getName ()
            .substring (3);
        try {
            final var setter = object.getClass ()
                .getDeclaredMethod (setMethodName, value.getClass ());
            setter.invoke (object, value);
        } catch (final NoSuchMethodException e) {
            handleAndThrow (METHOD_NOT_FOUND, e, method.getName (), object.getClass ()
                .getSimpleName ());
        } catch (final InvocationTargetException | IllegalAccessException e) {
            handleAndThrow (METHOD_NOT_FOUND, e, method.getName ());
        }
    }

    private static boolean shouldReplaceValue (final Object sourceValue, final Object defaultValue) {
        return isNull (sourceValue) || (sourceValue instanceof String && isEmpty (sourceValue.toString ()) && !isEmpty (
            (String) defaultValue)) || (sourceValue instanceof Integer && (int) sourceValue == 0);
    }

    private ReflectionUtil () {
        // Utility class.
    }
}
