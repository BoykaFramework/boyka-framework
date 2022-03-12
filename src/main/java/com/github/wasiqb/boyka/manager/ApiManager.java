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

package com.github.wasiqb.boyka.manager;

import static com.github.wasiqb.boyka.enums.ContentType.JSON;
import static com.github.wasiqb.boyka.enums.Messages.AUTH_PASSWORD_REQUIRED;
import static com.github.wasiqb.boyka.enums.Messages.CONTENT_TYPE_MOT_SET;
import static com.github.wasiqb.boyka.enums.Messages.ERROR_EXECUTING_REQUEST;
import static com.github.wasiqb.boyka.enums.Messages.ERROR_PARSING_REQUEST_BODY;
import static com.github.wasiqb.boyka.enums.Messages.ERROR_PARSING_RESPONSE_BODY;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static okhttp3.Credentials.basic;
import static okhttp3.MediaType.parse;
import static okhttp3.RequestBody.create;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.config.api.ApiSetting;
import com.github.wasiqb.boyka.enums.ContentType;
import com.github.wasiqb.boyka.enums.RequestMethod;
import com.github.wasiqb.boyka.exception.FrameworkError;
import com.github.wasiqb.boyka.utils.JsonParser;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import org.apache.logging.log4j.Logger;

/**
 * API manager to handle all API request executions.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class ApiManager {
    private static final Logger LOGGER      = getLogger ();
    private static final String URL_PATTERN = "{0}{1}{2}";

    /**
     * Execute API request.
     *
     * @param request {@link ApiRequest} created instance
     *
     * @return {@link ApiResponse}
     */
    public static ApiResponse execute (final ApiRequest request) {
        LOGGER.traceEntry ();
        LOGGER.info ("Executing API request: {}", request);
        final var manager = new ApiManager (request.getConfigKey ());
        requireNonNullElse (request.getHeaders (), new HashMap<String, String> ()).forEach (manager::addHeader);
        requireNonNullElse (request.getPathParams (), new HashMap<String, String> ()).forEach (manager::pathParam);
        return LOGGER.traceExit (manager.contentType (request.getContentType ())
            .basicAuth (request.getUserName (), request.getPassword ())
            .body (requireNonNullElse (request.getBody (), EMPTY))
            .body (request.getBodyObject ())
            .method (request.getMethod ())
            .getResponse (request.getPath ()));
    }

    private final ApiSetting          apiSetting;
    private final OkHttpClient        client;
    private       MediaType           mediaType;
    private final Map<String, String> pathParams;
    private final Request.Builder     request;
    private       RequestBody         requestBody;
    private       ApiResponse         response;

    private ApiManager (final String apiKey) {
        LOGGER.traceEntry ("Parameter : {}", apiKey);
        this.pathParams = new HashMap<> ();
        this.apiSetting = loadSetting ().getApiSetting (apiKey);
        this.client = new OkHttpClient.Builder ().connectTimeout (ofSeconds (this.apiSetting.getConnectionTimeout ()))
            .readTimeout (ofSeconds (this.apiSetting.getReadTimeout ()))
            .writeTimeout (ofSeconds (this.apiSetting.getWriteTimeout ()))
            .build ();
        this.request = new Request.Builder ();
        LOGGER.traceExit ();
    }

    private void addHeader (final String name, final String value) {
        LOGGER.traceEntry ("Parameter: {}, {}", name, value);
        this.request.header (name, value);
        LOGGER.traceExit ();
    }

    private ApiManager basicAuth (final String userName, final String password) {
        if (userName != null) {
            LOGGER.traceEntry ("Parameters: userName={}", userName);
            final var credentials = basic (userName, requireNonNull (password, AUTH_PASSWORD_REQUIRED.getMessage ()));
            addHeader ("Authorization", credentials);
        }
        return LOGGER.traceExit (this);
    }

    private <T> ApiManager body (final T body) {
        LOGGER.traceEntry ();
        if (body != null) {
            this.requestBody = create (JsonParser.toString (body),
                requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        }
        return LOGGER.traceExit (this);
    }

    private ApiManager body (final String body) {
        LOGGER.traceEntry ();
        this.requestBody = create (body, requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        return LOGGER.traceExit (this);
    }

    private ApiManager contentType (final ContentType contentType) {
        LOGGER.traceEntry ("Parameter : {}", contentType);
        this.mediaType = parse (requireNonNullElse (contentType, JSON).getType ());
        return LOGGER.traceExit (this);
    }

    private String getRequestBody (final RequestBody body) {
        LOGGER.traceEntry ();
        final var buffer = new Buffer ();
        try {
            requireNonNullElse (body, create ("{}", parse (JSON.getType ()))).writeTo (buffer);
        } catch (final IOException e) {
            LOGGER.catching (e);
            throw new FrameworkError (ERROR_PARSING_REQUEST_BODY.getMessage (), e);
        }
        return LOGGER.traceExit (buffer.readUtf8 ());
    }

    private ApiResponse getResponse (final String path) {
        LOGGER.traceEntry ("Parameters: {}", path);
        try {
            this.response = parseResponse (this.client.newCall (this.request.url (getUrl (path))
                    .build ())
                .execute ());
            logRequest ();
            logResponse ();
        } catch (final IOException e) {
            LOGGER.catching (e);
            throw new FrameworkError (ERROR_EXECUTING_REQUEST.getMessage (), e);
        }
        return LOGGER.traceExit (this.response);
    }

    private String getUrl (final String path) {
        LOGGER.traceEntry ("Parameter: {}", path);
        var hostName = this.apiSetting.getBaseUri ();
        if (this.apiSetting.getPort () > 0) {
            hostName = format ("{0}:{1}", hostName, this.apiSetting.getPort ());
        }
        return LOGGER.traceExit (
            format (URL_PATTERN, hostName, this.apiSetting.getBasePath (), interpolate (path, this.pathParams)));
    }

    private void logRequest () {
        LOGGER.traceEntry ();
        if (this.apiSetting.getLogging ()
            .isRequest ()) {
            final var req = this.response.getRequest ();
            LOGGER.info ("Request URL: {}", req.getPath ());
            req.getHeaders ()
                .forEach ((key, value) -> LOGGER.info ("Request Header: {} => {}", key, value));
            req.getPathParams ()
                .forEach ((key, value) -> LOGGER.info ("Request Path Param: {} => {}", key, value));
            if (isNotEmpty (req.getBody ())) {
                LOGGER.info ("Request Body: {}", req.getBody ());
            }
        }
        LOGGER.traceExit ();
    }

    private void logResponse () {
        LOGGER.traceEntry ();
        if (this.apiSetting.getLogging ()
            .isResponse ()) {
            LOGGER.info ("Status Code: {}", this.response.getStatusCode ());
            if (isNotEmpty (this.response.getBody ())) {
                LOGGER.info ("Status Message: {}", this.response.getStatusMessage ());
            }
            this.response.getHeaders ()
                .forEach ((k, v) -> LOGGER.info ("Response Header: {} => {}", k, v));
            if (isNotEmpty (this.response.getBody ())) {
                LOGGER.info ("Response Body: {}", this.response.getBody ());
            }
        }
        LOGGER.traceExit ();
    }

    private ApiManager method (final RequestMethod method) {
        LOGGER.traceEntry ("Parameter: {}", method);
        if (method != RequestMethod.GET) {
            this.request.method (method.name (), requireNonNull (this.requestBody));
        }
        return LOGGER.traceExit (this);
    }

    private ApiRequest parseRequest (final Request request) {
        LOGGER.traceEntry ();
        final var headers = new HashMap<String, String> ();
        request.headers ()
            .forEach (entry -> headers.put (entry.getFirst (), entry.getSecond ()));
        return LOGGER.traceExit (ApiRequest.createRequest ()
            .body (getRequestBody (request.body ()))
            .method (RequestMethod.valueOf (request.method ()))
            .headers (headers)
            .path (request.url ()
                .toString ())
            .create ());
    }

    private ApiResponse parseResponse (final Response res) {
        LOGGER.traceEntry ();
        if (res == null) {
            return null;
        }
        final var headers = new HashMap<String, String> ();
        res.headers ()
            .forEach (entry -> headers.put (entry.getFirst (), entry.getSecond ()));
        try {
            return LOGGER.traceExit (ApiResponse.createResponse ()
                .request (parseRequest (res.request ()))
                .statusCode (res.code ())
                .statusMessage (res.message ())
                .sentRequestAt (res.sentRequestAtMillis ())
                .headers (headers)
                .networkResponse (parseResponse (res.networkResponse ()))
                .previousResponse (parseResponse (res.priorResponse ()))
                .receivedResponseAt (res.receivedResponseAtMillis ())
                .body (requireNonNullElse (res.body (), ResponseBody.create (EMPTY, parse (JSON.getType ()))).string ())
                .create ());
        } catch (final IOException e) {
            LOGGER.catching (e);
            throw new FrameworkError (ERROR_PARSING_RESPONSE_BODY.getMessage (), e);
        }
    }

    private void pathParam (final String param, final String value) {
        LOGGER.traceEntry ("Parameter: {}, {}", param, value);
        this.pathParams.put (param, value);
        LOGGER.traceExit ();
    }
}