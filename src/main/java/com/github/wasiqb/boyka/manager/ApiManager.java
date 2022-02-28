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

import static com.github.wasiqb.boyka.enums.Messages.CONTENT_TYPE_MOT_SET;
import static com.github.wasiqb.boyka.enums.Messages.ERROR_EXECUTING_REQUEST;
import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static com.github.wasiqb.boyka.utils.StringUtils.interpolate;
import static com.google.common.truth.Truth.assertThat;
import static com.jayway.jsonpath.JsonPath.compile;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.requireNonNull;
import static okhttp3.Credentials.basic;
import static okhttp3.MediaType.parse;
import static okhttp3.RequestBody.create;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.github.wasiqb.boyka.config.api.ApiSetting;
import com.github.wasiqb.boyka.exception.FrameworkError;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.StringSubject;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiManager {
    private static final String URL_PATTERN = "{0}{1}{2}";

    public static ApiManager createRequest (final String key) {
        return new ApiManager (key);
    }

    private final ApiSetting          apiSetting;
    private final OkHttpClient        client;
    private       DocumentContext     jsonPath;
    private       MediaType           mediaType;
    private final Map<String, String> pathParams;
    private final Request.Builder     request;
    private       RequestBody         requestBody;
    private       Response            response;

    private ApiManager (final String apiKey) {
        this.pathParams = new HashMap<> ();
        this.apiSetting = loadSetting ().getApiSetting (apiKey);
        this.client = new OkHttpClient.Builder ().connectTimeout (ofSeconds (this.apiSetting.getConnectionTimeout ()))
            .readTimeout (ofSeconds (this.apiSetting.getReadTimeout ()))
            .writeTimeout (ofSeconds (this.apiSetting.getWriteTimeout ()))
            .build ();
        this.request = new Request.Builder ();
    }

    public ApiManager addHeader (final String name, final String value) {
        this.request.header (name, value);
        return this;
    }

    public ApiManager basicAuth (final String userName, final String password) {
        final var credentials = basic (userName, password);
        addHeader ("Authorization", credentials);
        return this;
    }

    public ApiManager body (final String body) {
        this.requestBody = create (body, requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        return this;
    }

    public <T> ApiManager body (final T body) {
        this.requestBody = create (body.toString (),
            requireNonNull (this.mediaType, CONTENT_TYPE_MOT_SET.getMessage ()));
        return this;
    }

    public ApiManager contentType (final String contentType) {
        this.mediaType = parse (contentType);
        return this;
    }

    public ApiManager delete (final String path) {
        this.request.delete (this.requestBody);
        return getResponse (path);
    }

    public ApiManager get (final String path) {
        this.request.get ();
        return getResponse (path);
    }

    public ApiManager patch (final String path) {
        this.request.patch (this.requestBody);
        return getResponse (path);
    }

    public ApiManager pathParam (final String param, final String value) {
        this.pathParams.put (param, value);
        return this;
    }

    public ApiManager post (final String path) {
        this.request.post (this.requestBody);
        return getResponse (path);
    }

    public ApiManager put (final String path) {
        this.request.put (this.requestBody);
        return getResponse (path);
    }

    public BooleanSubject verifyBooleanField (final String expression) {
        return assertThat (this.jsonPath.read (compile (expression), Boolean.class));
    }

    public IntegerSubject verifyIntField (final String expression) {
        return assertThat (this.jsonPath.read (compile (expression), Integer.class));
    }

    public IntegerSubject verifyStatusCode () {
        return assertThat (this.response.code ());
    }

    public StringSubject verifyStatusMessage () {
        return assertThat (this.response.message ());
    }

    public StringSubject verifyTextField (final String expression) {
        return assertThat (this.jsonPath.read (compile (expression))
            .toString ());
    }

    private ApiManager getResponse (final String path) {
        try {
            this.response = this.client.newCall (this.request.url (getUrl (path))
                    .build ())
                .execute ();
            this.jsonPath = JsonPath.parse (requireNonNull (this.response.body ()).string ());

            logRequest ();
            logResponse ();
        } catch (final IOException e) {
            throw new FrameworkError (ERROR_EXECUTING_REQUEST.getMessage (), e);
        }
        return this;
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
            final var req = this.response.request ();
            System.out.println (req.url ());
            System.out.println (req.body ());
            req.headers ()
                .forEach (System.out::println);
        }
    }

    private void logResponse () {
        if (this.apiSetting.getLogging ()
            .isResponse ()) {
            System.out.println (this.response.code ());
            System.out.println (this.response.message ());
            System.out.println (this.response.body ());
            this.response.headers ()
                .forEach (System.out::println);
        }
    }
}