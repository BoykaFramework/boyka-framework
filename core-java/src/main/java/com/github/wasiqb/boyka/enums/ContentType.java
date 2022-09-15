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

package com.github.wasiqb.boyka.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Request Content Types.
 *
 * @author Wasiq Bhamla
 * @since 04-Mar-2022
 */
@AllArgsConstructor
@Getter
public enum ContentType {
    /**
     * Application/x-www-form-urlencoded.
     */
    FORM_URLENCODED ("application/x-www-form-urlencoded"),
    /**
     * Application/json.
     */
    JSON ("application/json"),
    /**
     * Multipart/form-data.
     */
    MULTIPART_FORM_DATA ("multipart/form-data"),
    /**
     * Plain text.
     */
    PLAIN_TEXT ("text/plain");

    private final String type;
}
