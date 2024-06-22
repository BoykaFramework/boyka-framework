/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.builders;

import static com.google.common.truth.Truth.assertWithMessage;
import static com.jayway.jsonpath.JsonPath.compile;
import static com.jayway.jsonpath.JsonPath.parse;
import static com.networknt.schema.JsonSchemaFactory.getInstance;
import static com.networknt.schema.SpecVersion.VersionFlag.V7;
import static io.github.boykaframework.enums.Message.ERROR_READING_FILE;
import static io.github.boykaframework.enums.Message.INVALID_HEADER_KEY;
import static io.github.boykaframework.enums.Message.NO_BODY_TO_PARSE;
import static io.github.boykaframework.enums.Message.RESPONSE_SCHEMA_NOT_MATCHING;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.ErrorHandler.throwError;
import static java.lang.System.getProperty;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.StringSubject;
import com.jayway.jsonpath.DocumentContext;
import io.github.boykaframework.config.api.ApiSetting;
import io.github.boykaframework.config.api.CommonApiSetting;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.logging.log4j.Logger;

/**
 * Response container class.
 *
 * @author Wasiq Bhamla
 * @since 04-Mar-2022
 */
@ToString
@Getter
@Builder (builderMethodName = "createResponse", buildMethodName = "create")
public class ApiResponse {
    private static final Logger LOGGER = getLogger ();

    private ApiSetting          apiSetting;
    private String              body;
    private CommonApiSetting    commonApiSetting;
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
        LOGGER.info ("Get response data for expression: {}", expression);
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
        LOGGER.traceEntry ();
        LOGGER.info ("Get response data for expression: {} and type: {}", expression, type);
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
        LOGGER.info ("Verifying boolean field for expression: {}", expression);
        LOGGER.traceExit ();
        return assertWithMessage (expression).that (getResponseData (expression, Boolean.class));
    }

    /**
     * Verify header in response.
     *
     * @param key header key
     *
     * @return {@link StringSubject} instance
     */
    public StringSubject verifyHeader (final String key) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying response Header: {}", key);
        LOGGER.traceExit ();
        if (!getHeaders ().containsKey (key)) {
            throwError (INVALID_HEADER_KEY, key);
        }
        return assertWithMessage (key).that (getHeaders ().get (key));
    }

    /**
     * Verify integer field in request body
     *
     * @param expression JsonPath expression
     *
     * @return {@link IntegerSubject} instance
     */
    public IntegerSubject verifyIntField (final String expression) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying integer field for expression: {}", expression);
        LOGGER.traceExit ();
        return assertWithMessage (expression).that (getResponseData (expression, Integer.class));
    }

    /**
     * Verify schema of response.
     *
     * @param schemaName String expression
     */
    public void verifySchema (final String schemaName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying Response Schema");
        try (
            final var inputStream = new FileInputStream (Path.of (getProperty ("user.dir"), "/src/test/resources",
                    requireNonNullElse (this.apiSetting.getSchemaPath (), this.commonApiSetting.getSchemaPath ()),
                    schemaName)
                .toFile ())) {
            final var factory = getInstance (V7);
            final var jsonSchema = factory.getSchema (inputStream);
            final var errors = jsonSchema.validate (new ObjectMapper ().readTree (this.body));

            assertWithMessage (RESPONSE_SCHEMA_NOT_MATCHING.getMessageText ()).that (errors)
                .isEmpty ();
        } catch (final IOException e) {
            handleAndThrow (ERROR_READING_FILE, e, schemaName);
        }
        LOGGER.info ("API response schema validation successfully verified");
        LOGGER.traceExit ();
    }

    /**
     * Verify status code in response.
     *
     * @return {@link IntegerSubject} instance
     */
    public IntegerSubject verifyStatusCode () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying Status Code");
        LOGGER.traceExit ();
        return assertWithMessage ("Status Code").that (getStatusCode ());
    }

    /**
     * Verify status message in response.
     *
     * @return {@link StringSubject} instance
     */
    public StringSubject verifyStatusMessage () {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying Status Message");
        LOGGER.traceExit ();
        return assertWithMessage ("Status Message").that (getStatusMessage ());
    }

    /**
     * Verify string field in request body
     *
     * @param expression JsonPath expression
     *
     * @return {@link StringSubject} instance
     */
    public StringSubject verifyTextField (final String expression) {
        LOGGER.traceEntry ();
        LOGGER.info ("Verifying text field for expression: {}", expression);
        LOGGER.traceExit ();
        return assertWithMessage (expression).that (getResponseData (expression));
    }

    private DocumentContext jsonPath () {
        LOGGER.traceEntry ();
        return LOGGER.traceExit (parse (requireNonNull (this.body, NO_BODY_TO_PARSE.getMessageText ())));
    }
}
