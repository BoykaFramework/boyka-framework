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

package com.github.wasiqb.boyka.builders;

import static com.github.wasiqb.boyka.enums.Messages.NO_BODY_TO_PARSE;
import static com.google.common.truth.Truth.assertThat;
import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.JsonPath.parse;
import static java.util.Objects.requireNonNull;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.util.Map;

import com.google.common.truth.BooleanSubject;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.StringSubject;
import com.jayway.jsonpath.DocumentContext;
import lombok.Builder;
import lombok.Getter;
import lombok.Singular;
import org.apache.logging.log4j.Logger;

/**
 * Response container class.
 *
 * @author Wasiq Bhamla
 * @since 04-Mar-2022
 */
@Getter
@Builder (builderMethodName = "createResponse", buildMethodName = "create")
public class ApiResponse {
    private static final Logger LOGGER = getLogger ();

    private String              body;
    @Singular
    private Map<String, String> headers;
    private ApiResponse         networkResponse;
    private ApiResponse         previousResponse;
    private long                receivedResponseAt;
    private ApiRequest          request;
    private long                sentRequestAt;
    private int                 statusCode;
    private String              statusMessage;

    /**
     * Get response body field data.
     *
     * @param expression JsonPath expression
     *
     * @return String field data
     */
    public String getResponseData (final String expression) {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (getResponseData (expression, String.class));
    }

    /**
     * Get response data in specified type.
     *
     * @param expression JsonPath expression
     * @param type Data type class
     * @param <T> type of data
     *
     * @return Data in specified type
     */
    public <T> T getResponseData (final String expression, final Class<T> type) {
        LOGGER.traceEntry ("Expression: {}, Type: {}", expression, type);
        return LOGGER.traceExit (jsonPath ().read (compile (expression), type));
    }

    /**
     * Verify boolean field in request body
     *
     * @param expression JsonPath expression
     *
     * @return {@link BooleanSubject} instance
     */
    public BooleanSubject verifyBooleanField (final String expression) {
        LOGGER.traceEntry ();
        LOGGER.traceExit ();
        return assertThat (getResponseData (expression, Boolean.class));
    }

    /**
     * Verify integer field in request body
     *
     * @param expression JsonPath expression
     *
     * @return {@link IntegerSubject} instance
     */
    public IntegerSubject verifyIntField (final String expression) {
        LOGGER.traceEntry ("Expression: {}", expression);
        LOGGER.traceExit ();
        return assertThat (getResponseData (expression, Integer.class));
    }

    /**
     * Verify status code in response.
     *
     * @return {@link IntegerSubject} instance
     */
    public IntegerSubject verifyStatusCode () {
        LOGGER.traceEntry ();
        LOGGER.traceExit ();
        return assertThat (getStatusCode ());
    }

    /**
     * Verify status message in response.
     *
     * @return {@link StringSubject} instance
     */
    public StringSubject verifyStatusMessage () {
        LOGGER.traceEntry ();
        LOGGER.traceExit ();
        return assertThat (getStatusMessage ());
    }

    /**
     * Verify string field in request body
     *
     * @param expression JsonPath expression
     *
     * @return {@link StringSubject} instance
     */
    public StringSubject verifyTextField (final String expression) {
        LOGGER.traceEntry ("Expression: {}", expression);
        LOGGER.traceExit ();
        return assertThat (getResponseData (expression));
    }

    private DocumentContext jsonPath () {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (parse (requireNonNull (this.body, NO_BODY_TO_PARSE.getMessage ())));
    }
}
