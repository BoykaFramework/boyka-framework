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
    private final ApiSetting           apiSetting;
    private       JsonPath             jsonPath;
    private final RequestSpecification request;

    public ApiManager (final String apiKey) {
        this.apiSetting = loadSetting ().getApiSetting (apiKey);
        this.request = given ().contentType (JSON)
            .accept (JSON);
        setupApi ();
    }

    public ApiManager addHeader (final String name, final String value) {
        this.request.header (new Header (name, value));
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

    public StringSubject verifyTextField (final String expression) {
        return assertThat (this.jsonPath.getString (expression));
    }

    private ApiManager getResponse (final Function<RequestSpecification, Response> execute) {
        logRequest ();
        final Response response = execute.apply (this.request);
        logResponse (response);
        this.jsonPath = from (response.asString ());
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