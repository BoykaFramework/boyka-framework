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

package io.github.boykaframework.enums;

import static java.util.Locale.ENGLISH;
import static java.util.Locale.GERMAN;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Supported Locales
 *
 * @author Wasiq Bhamla
 * @since 15-Mar-2025
 */
@Getter
@AllArgsConstructor
public enum Language {
    /**
     * Arabic language.
     */
    AR (new Locale ("ar", "Arabic")),
    /**
     * English language.
     */
    EN (ENGLISH),
    /**
     * German language.
     */
    GR (GERMAN);

    private final Locale locale;
}
