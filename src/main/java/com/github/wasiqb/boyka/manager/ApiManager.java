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

import static com.github.wasiqb.boyka.utils.SettingUtils.loadSetting;
import static com.google.common.truth.Truth.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.path.json.JsonPath.from;

import java.util.function.Function;

import com.github.wasiqb.boyka.config.api.ApiSetting;
import com.google.common.truth.BooleanSubject;
import com.google.common.truth.IntegerSubject;
import com.google.common.truth.StringSubject;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiManager {
    public static ApiManager createRequest (final String key) {
        return new ApiManager (key);
    }

    private final ApiSetting           apiSetting;
    private       JsonPath             jsonPath;
    private final RequestSpecification request;
    private       Response             response;

    private ApiManager (final String apiKey) {
        this.apiSetting = loadSetting ().getApiSetting (apiKey);
        this.request = given ().contentType (JSON)
            .accept (JSON);
        setupApi ();
    }

    public ApiManager addHeader (final String name, final String value) {
        this.request.header (new Header (name, value));
        return this;
    }

    public ApiManager basicAuth (final String userName, final String password) {
        this.request.auth ()
            .basic (userName, password);
        return this;
    }

    public ApiManager body (final String body) {
        this.request.body (body);
        return this;
    }

    public <T> ApiManager body (final T body) {
        this.request.body (body);
        return this;
    }

    public ApiManager delete (final String path) {
        return getResponse (req -> req.delete (path));
    }

    public ApiManager get (final String path) {
        return getResponse (req -> req.get (path));
    }

    public ApiManager pathParam (final String param, final Object value) {
        this.request.pathParam (param, value);
        return this;
    }

    public ApiManager post (final String path) {
        return getResponse (req -> req.post (path));
    }

    public ApiManager put (final String path) {
        return getResponse (req -> req.put (path));
    }

    public BooleanSubject verifyBooleanField (final String expression) {
        return assertThat (this.jsonPath.getBoolean (expression));
    }

    public IntegerSubject verifyIntField (final String expression) {
        return assertThat (this.jsonPath.getInt (expression));
    }

    public IntegerSubject verifyStatusCode () {
        return assertThat (this.response.statusCode ());
    }

    public StringSubject verifyStatusMessage () {
        return assertThat (this.response.statusLine ());
    }

    public StringSubject verifyTextField (final String expression) {
        return assertThat (this.jsonPath.getString (expression));
    }

    private ApiManager getResponse (final Function<RequestSpecification, Response> execute) {
        logRequest ();
        this.response = execute.apply (this.request);
        logResponse (this.response);
        this.jsonPath = from (this.response.asString ());
        return this;
    }

    private void logRequest () {
        if (this.apiSetting.getLogging ()
            .isRequest ()) {
            this.request.log ()
                .all ();
        }
    }

    private void logResponse (final Response response) {
        if (this.apiSetting.getLogging ()
            .isResponse ()) {
            response.then ()
                .log ()
                .all ();
        }
    }

    private void setupApi () {
        this.request.baseUri (this.apiSetting.getBaseUri ())
            .basePath (this.apiSetting.getBasePath ());
        if (this.apiSetting.getPort () > 0) {
            this.request.port (this.apiSetting.getPort ());
        }
    }
}