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

/**
 * API manager to handle all API request executions.
 *
 * @author Wasiq Bhamla
 * @since 17-Feb-2022
 */
public final class ApiManager {
    private static final String URL_PATTERN = "{0}{1}{2}";

    /**
     * Execute API request.
     *
     * @param request {@link ApiRequest} created instance
     *
     * @return {@link ApiResponse}
     */
    public static ApiResponse execute (final ApiRequest request) {
        final var manager = new ApiManager (request.getConfigKey ());
        requireNonNullElse (request.getHeaders (), new HashMap<String, String> ()).forEach (manager::addHeader);
        requireNonNullElse (request.getPathParams (), new HashMap<String, String> ()).forEach (manager::pathParam);
        return manager.contentType (request.getContentType ())
            .basicAuth (request.getUserName (), request.getPassword ())
            .body (requireNonNullElse (request.getBody (), EMPTY))
            .body (request.getBodyObject ())
            .method (request.getMethod ())
            .getResponse (request.getPath ());
    }

    private final ApiSetting          apiSetting;
    private final OkHttpClient        client;
    private       MediaType           mediaType;
    private final Map<String, String> pathParams;
    private final Request.Builder     request;
    private       RequestBody         requestBody;
    private       ApiResponse         response;

    private ApiManager (final String apiKey) {
        this.pathParams = new HashMap<> ();
        this.apiSetting = loadSetting ().getApiSetting (apiKey);
        this.client = new OkHttpClient.Builder ().connectTimeout (ofSeconds (this.apiSetting.getConnectionTimeout ()))
            .readTimeout (ofSeconds (this.apiSetting.getReadTimeout ()))
            .writeTimeout (ofSeconds (this.apiSetting.getWriteTimeout ()))
            .build ();
        this.request = new Request.Builder ();
    }

    private void addHeader (final String name, final String value) {
        this.request.header (name, value);
    }

    private ApiManager basicAuth (final String userName, final String password) {
        if (userName != null) {
            final var credentials = basic (userName, requireNonNull (password, AUTH_PASSWORD_REQUIRED.getMessage ()));
            addHeader ("Authorization", credentials);
        }
        return this;
    }

    private <T> ApiManager body (final T body) {
        if (body != null) {
            this.requestBody = create (JsonParser.toString (body),
                requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        }
        return this;
    }

    private ApiManager body (final String body) {
        this.requestBody = create (body, requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        return this;
    }

    private ApiManager contentType (final ContentType contentType) {
        this.mediaType = parse (requireNonNullElse (contentType, JSON).getType ());
        return this;
    }

    private ApiResponse getResponse (final String path) {
        try {
            this.response = parseResponse (this.client.newCall (this.request.url (getUrl (path))
                    .build ())
                .execute ());
            logRequest ();
            logResponse ();
        } catch (final IOException e) {
            throw new FrameworkError (ERROR_EXECUTING_REQUEST.getMessage (), e);
        }
        return this.response;
    }

    private String getUrl (final String path) {
        var hostName = this.apiSetting.getBaseUri ();
        if (this.apiSetting.getPort () > 0) {
            hostName = format ("{0}:{1}", hostName, this.apiSetting.getPort ());
        }
        return format (URL_PATTERN, hostName, this.apiSetting.getBasePath (), interpolate (path, this.pathParams));
    }

    private void logRequest () {
        if (this.apiSetting.getLogging ()
            .isRequest ()) {
            final var req = this.response.getRequest ();
            System.out.println (req.getPath ());
            System.out.println (req.getBody ());
        }
    }

    private void logResponse () {
        if (this.apiSetting.getLogging ()
            .isResponse ()) {
            System.out.println (this.response.getStatusCode ());
            System.out.println (this.response.getStatusMessage ());
            System.out.println (this.response.getBody ());
        }
    }

    private ApiManager method (final RequestMethod method) {
        if (method != RequestMethod.GET) {
            this.request.method (method.name (), requireNonNull (this.requestBody));
        }
        return this;
    }

    private ApiRequest parseRequest (final Request request) {
        final var headers = new HashMap<String, String> ();
        request.headers ()
            .forEach (entry -> headers.put (entry.getFirst (), entry.getSecond ()));
        return ApiRequest.createRequest ()
            .body (requireNonNullElse (request.body (), EMPTY).toString ())
            .method (RequestMethod.valueOf (request.method ()))
            .headers (headers)
            .path (request.url ()
                .toString ())
            .create ();
    }

    private ApiResponse parseResponse (final Response res) {
        if (res == null) {
            return null;
        }
        final var headers = new HashMap<String, String> ();
        res.headers ()
            .forEach (entry -> headers.put (entry.getFirst (), entry.getSecond ()));
        try {
            return ApiResponse.createResponse ()
                .request (parseRequest (res.request ()))
                .statusCode (res.code ())
                .statusMessage (res.message ())
                .sentRequestAt (res.sentRequestAtMillis ())
                .headers (headers)
                .networkResponse (parseResponse (res.networkResponse ()))
                .previousResponse (parseResponse (res.priorResponse ()))
                .receivedResponseAt (res.receivedResponseAtMillis ())
                .body (requireNonNullElse (res.body (),
                    ResponseBody.create (EMPTY, MediaType.parse (JSON.getType ()))).string ())
                .create ();
        } catch (final IOException e) {
            throw new FrameworkError (ERROR_PARSING_RESPONSE_BODY.getMessage (), e);
        }
    }

    private void pathParam (final String param, final String value) {
        // TODO: Query Params: https://stackoverflow.com/a/43029045/5320558
        this.pathParams.put (param, value);
    }
}