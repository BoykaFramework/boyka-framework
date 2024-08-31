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

import static io.github.boykaframework.enums.Message.METHOD_INVOKE_FAILED;
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
     * @param common Default object from where missing values is to be replaced from
     * @param <T> Object type
     *
     * @return New object with all the null and missing fields replaced with default
     */
    public static <T> T replaceEmptyWithCommon (final T source, final T common) {
        final var sourceMethods = getMethods (source);

        for (final var sourceMethod : sourceMethods) {
            if (sourceMethod.getName ()
                .startsWith ("get") || sourceMethod.getName ()
                .startsWith ("is")) {
                try {
                    final var sourceValue = getMethodValue (sourceMethod, source);
                    final var defaultObject = init (source.getClass ());
                    final var defaultValue = getMethodValue (sourceMethod, defaultObject);
                    final var commonMethod = getMethod (sourceMethod, common);
                    final var commonValue = getMethodValue (commonMethod, common);

                    if (sourceValue instanceof BoykaConfig) {
                        replaceEmptyWithCommon (sourceValue, commonValue);
                    } else if (shouldReplaceValue (sourceValue, commonValue, defaultValue)) {
                        setMethodValue (sourceMethod, source, commonValue, defaultValue);
                    }
                } catch (final NoSuchMethodException e) {
                    handleAndThrow (METHOD_NOT_FOUND, e, sourceMethod.getName (), common.getClass ()
                        .getSimpleName ());
                } catch (final InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    handleAndThrow (METHOD_INVOKE_FAILED, e, sourceMethod.getName ());
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
            .startsWith ("get") || method.getName ()
            .startsWith ("is")) {
            try {
                return (E) method.invoke (object);
            } catch (final IllegalAccessException | InvocationTargetException e) {
                handleAndThrow (METHOD_INVOKE_FAILED, e, method.getName ());
            }
        }
        return null;
    }

    private static <T> Method[] getMethods (final T object) {
        return object.getClass ()
            .getDeclaredMethods ();
    }

    @SuppressWarnings ("unchecked")
    private static <T> T init (final Class<T> cls)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        final var ctor = cls.getDeclaredConstructors ()[0];
        return (T) ctor.newInstance ();
    }

    private static <T> void setMethodValue (final Method method, final T source, final T commonValue,
        final T defaultValue) {
        final var methodName = method.getName ();

        if (!(methodName.startsWith ("get") || methodName.startsWith ("is"))) {
            return;
        }

        final var setMethodName = "set" + (methodName.startsWith ("is")
                                           ? methodName.substring (2)
                                           : methodName.substring (3));
        final T valueToSet;

        if (methodName.startsWith ("is") && source instanceof Boolean && commonValue instanceof Boolean) {
            valueToSet = !commonValue.equals (source) && source.equals (defaultValue) ? commonValue : source;
        } else {
            valueToSet = !isNull (commonValue) && !commonValue.equals (defaultValue) ? commonValue : defaultValue;
        }

        var valueType = valueToSet.getClass ();
        if (valueToSet instanceof Boolean) {
            valueType = Boolean.TYPE;
        } else if (valueToSet instanceof Integer) {
            valueType = Integer.TYPE;
        }

        try {
            final var sourceClass = source.getClass ();
            final var setter = sourceClass.getDeclaredMethod (setMethodName, valueType);
            setter.invoke (source, valueToSet);
        } catch (final NoSuchMethodException e) {
            handleAndThrow (METHOD_NOT_FOUND, e, methodName, source.getClass ()
                .getSimpleName ());
        } catch (final InvocationTargetException | IllegalAccessException e) {
            handleAndThrow (METHOD_INVOKE_FAILED, e, methodName);
        }
    }

    private static <T> boolean shouldReplaceValue (final T sourceValue, final T commonValue, final T defaultValue) {
        if (isNull (sourceValue) && isNull (commonValue) && isNull (defaultValue)) {
            return false;
        }

        if (sourceValue instanceof String) {
            return isEmpty (sourceValue.toString ()) && !isEmpty ((String) commonValue);
        }

        if (sourceValue instanceof Boolean) {
            final var source = (boolean) sourceValue;
            final var common = (boolean) commonValue;
            final var defaultVal = (boolean) defaultValue;
            return (source != common && source == defaultVal);
        }

        if (sourceValue instanceof Integer && (int) sourceValue == 0) {
            if (commonValue instanceof Integer && (int) commonValue != 0) {
                return false;
            }
            return defaultValue instanceof Integer && (int) defaultValue != 0;
        }

        if (sourceValue instanceof Enum<?>) {
            return sourceValue != commonValue && sourceValue == defaultValue;
        }

        return false;
    }

    private ReflectionUtil () {
        // Utility class.
    }
}
