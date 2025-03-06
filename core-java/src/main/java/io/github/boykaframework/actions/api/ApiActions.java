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

package io.github.boykaframework.actions.api;

import static io.github.boykaframework.enums.ContentType.JSON;
import static io.github.boykaframework.enums.ContentType.OCTET_STREAM;
import static io.github.boykaframework.enums.ListenerType.API_ACTION;
import static io.github.boykaframework.enums.Message.AUTH_PASSWORD_REQUIRED;
import static io.github.boykaframework.enums.Message.BASE_URL_EMPTY;
import static io.github.boykaframework.enums.Message.CONTENT_TYPE_NOT_SET;
import static io.github.boykaframework.enums.Message.ERROR_EXECUTING_REQUEST;
import static io.github.boykaframework.enums.Message.ERROR_PARSING_REQUEST_BODY;
import static io.github.boykaframework.enums.Message.ERROR_PARSING_RESPONSE_BODY;
import static io.github.boykaframework.enums.Message.ERROR_SETTING_REQUEST_METHOD;
import static io.github.boykaframework.enums.Message.SSL_ERROR;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static io.github.boykaframework.utils.StringUtils.interpolate;
import static io.github.boykaframework.utils.StringUtils.isValidPath;
import static io.github.boykaframework.utils.Validator.requireNonEmpty;
import static java.nio.file.Path.of;
import static java.text.MessageFormat.format;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;
import static java.util.Optional.ofNullable;
import static okhttp3.Credentials.basic;
import static okhttp3.MediaType.parse;
import static okhttp3.MultipartBody.FORM;
import static okhttp3.RequestBody.create;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.moczul.ok2curl.Configuration;
import com.moczul.ok2curl.CurlCommandGenerator;
import io.github.boykaframework.actions.interfaces.api.IApiActions;
import io.github.boykaframework.actions.interfaces.listeners.api.IApiActionsListener;
import io.github.boykaframework.builders.ApiRequest;
import io.github.boykaframework.builders.ApiResponse;
import io.github.boykaframework.config.api.ApiSetting;
import io.github.boykaframework.config.api.CommonApiSetting;
import io.github.boykaframework.config.api.LogSetting;
import io.github.boykaframework.enums.ContentType;
import io.github.boykaframework.enums.RequestMethod;
import io.github.boykaframework.utils.JsonUtil;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
public final class ApiActions implements IApiActions {
    private static final Logger LOGGER = getLogger ();

    /**
     * Handle API request
     *
     * @param request {@link ApiRequest} object to handle
     *
     * @return {@link IApiActions} implementation instance
     */
    public static IApiActions withRequest (final ApiRequest request) {
        return new ApiActions (request);
    }

    private final ApiRequest          apiRequest;
    private final ApiSetting          apiSetting;
    private final OkHttpClient        client;
    private final CommonApiSetting    commonApiSetting;
    private final IApiActionsListener listener;
    private final LogSetting          logSetting;
    private       MediaType           mediaType;
    private final Map<String, String> pathParams;
    private final Request.Builder     request;
    private       RequestBody         requestBody;
    private       ApiResponse         response;

    private ApiActions (final ApiRequest apiRequest) {
        LOGGER.traceEntry ();

        this.listener = getSession ().getListener (API_ACTION);
        this.apiRequest = apiRequest;
        this.pathParams = new HashMap<> ();
        this.commonApiSetting = getSession ().getCommonApiSetting ();
        this.apiSetting = getSession ().getApiSetting ();

        final var builder = getApiBuilder ();
        this.client = builder.build ();
        this.logSetting = requireNonNullElse (this.apiSetting.getLogging (), this.commonApiSetting.getLogging ());
        this.request = new Request.Builder ();
        LOGGER.traceExit ();
    }

    @Override
    public ApiResponse execute () {
        LOGGER.traceEntry ();

        fillMap (requireNonNullElse (this.apiRequest.getHeaders (), new HashMap<> ()), this::addHeader, true);
        fillMap (requireNonNullElse (this.apiRequest.getPathParams (), new HashMap<> ()), this::pathParam, false);

        final var responseResult = this.contentType (this.apiRequest.getContentType ())
            .basicAuth (this.apiRequest.getUserName (), this.apiRequest.getPassword ())
            .body (requireNonNullElse (this.apiRequest.getBody (), EMPTY))
            .body (this.apiRequest.getBodyObject ())
            .body (this.apiRequest.getFormBodies ())
            .method (this.apiRequest.getMethod ())
            .getResponse (this.apiRequest.getQueryParams (), this.apiRequest.getPath ());

        ofNullable (this.listener).ifPresent (l -> l.onExecute (responseResult));
        return LOGGER.traceExit (responseResult);
    }

    private void addHeader (final String name, final String value) {
        LOGGER.traceEntry ("Parameter: {}, {}", name, value);
        this.request.header (name, value);
        LOGGER.traceExit ();
    }

    private ApiActions basicAuth (final String userName, final String password) {
        if (!isNull (userName)) {
            LOGGER.traceEntry ("Parameters: userName={}", userName);
            final var credentials = basic (userName,
                requireNonNull (password, AUTH_PASSWORD_REQUIRED.getMessageText ()));
            addHeader ("Authorization", credentials);
        }
        return LOGGER.traceExit (this);
    }

    private <T> ApiActions body (final T body) {
        LOGGER.traceEntry ();
        if (!isNull (body)) {
            body (JsonUtil.toString (body));
        }
        return LOGGER.traceExit (this);
    }

    private ApiActions body (final String body) {
        LOGGER.traceEntry ();
        this.requestBody = create (body, requireNonNull (this.mediaType, CONTENT_TYPE_NOT_SET.getMessageText ()));
        return LOGGER.traceExit (this);
    }

    private ApiActions body (final Map<String, String> bodyMap) {
        LOGGER.traceEntry ();
        if (!bodyMap.isEmpty ()) {
            final var formBody = new MultipartBody.Builder ().setType (FORM);
            bodyMap.forEach ((key, value) -> {
                LOGGER.debug ("Form Data: {}, {}", key, value);
                if (isValidPath (value)) {
                    final var filePath = of (value).toFile ();
                    formBody.addFormDataPart (key, filePath.getName (),
                        create (filePath, parse (OCTET_STREAM.getType ())));
                } else {
                    formBody.addFormDataPart (key, value);
                }
            });
            this.requestBody = formBody.build ();
        }
        return LOGGER.traceExit (this);
    }

    private ApiActions contentType (final ContentType contentType) {
        LOGGER.traceEntry ("Parameter : {}", contentType);
        this.mediaType = parse (requireNonNullElse (contentType, JSON).getType ());
        return LOGGER.traceExit (this);
    }

    private void fillMap (final Map<String, String> map, final BiConsumer<String, String> action,
        final boolean skipOnEmpty) {
        map.forEach ((k, v) -> {
            if (!skipOnEmpty) {
                action.accept (k, isNotEmpty (v) ? v : EMPTY);
            } else if (isNotEmpty (v)) {
                action.accept (k, v);
            }
        });
    }

    private OkHttpClient.Builder getApiBuilder () {
        final var timeout = requireNonNullElse (this.apiSetting.getTimeout (), this.commonApiSetting.getTimeout ());
        final var builder = new OkHttpClient.Builder ().connectTimeout (ofSeconds (timeout.getConnectionTimeout ()))
            .readTimeout (ofSeconds (timeout.getReadTimeout ()))
            .writeTimeout (ofSeconds (timeout.getWriteTimeout ()));
        if (!this.apiSetting.isValidateSsl () || !this.commonApiSetting.isValidateSsl ()) {
            builder.sslSocketFactory (requireNonNull (getSslContext ()).getSocketFactory (),
                (X509TrustManager) getTrustedCertificates ()[0]);
        }
        if (!this.apiSetting.isVerifyHostName () || !this.commonApiSetting.isVerifyHostName ()) {
            builder.hostnameVerifier ((hostname, session) -> true);
        }
        return builder;
    }

    private String getRequestBody (final RequestBody body) {
        LOGGER.traceEntry ();
        final var buffer = new Buffer ();
        try {
            requireNonNullElse (body, create ("{}", parse (JSON.getType ()))).writeTo (buffer);
        } catch (final IOException e) {
            handleAndThrow (ERROR_PARSING_REQUEST_BODY, e);
        }
        return LOGGER.traceExit (buffer.readUtf8 ());
    }

    private ApiResponse getResponse (final Map<String, String> queryParams, final String path) {
        LOGGER.traceEntry ("Parameters: {}", path);
        try {
            final var urlBuilder = requireNonNull (HttpUrl.parse (getUrl (path))).newBuilder ();
            queryParams.forEach (urlBuilder::addQueryParameter);
            this.response = parseResponse (this.client.newCall (this.request.url (urlBuilder.build ())
                    .build ())
                .execute ());
            logRequest ();
            logResponse ();
        } catch (final IOException e) {
            handleAndThrow (ERROR_EXECUTING_REQUEST, e);
        }
        return LOGGER.traceExit (this.response);
    }

    private SSLContext getSslContext () {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance ("SSL");
            sslContext.init (null, getTrustedCertificates (), new java.security.SecureRandom ());
        } catch (final NoSuchAlgorithmException | KeyManagementException e) {
            handleAndThrow (SSL_ERROR, e, e.getMessage ());
        }
        return sslContext;
    }

    private TrustManager[] getTrustedCertificates () {
        return new TrustManager[] { new X509TrustManager () {
            @Override
            public void checkClientTrusted (final java.security.cert.X509Certificate[] chain, final String authType) {
                // Overriding the default implementation.
            }

            @Override
            public void checkServerTrusted (final java.security.cert.X509Certificate[] chain, final String authType) {
                // Overriding the default implementation.
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers () {
                return new java.security.cert.X509Certificate[] {};
            }
        } };
    }

    private String getUrl () {
        LOGGER.traceEntry ();
        var hostName = requireNonEmpty (this.apiSetting.getBaseUri (), BASE_URL_EMPTY);
        if (this.apiSetting.getPort () > 0) {
            hostName = format ("{0}:{1}", hostName, this.apiSetting.getPort ());
        }
        final var basePath = requireNonNullElse (this.apiSetting.getBasePath (), this.commonApiSetting.getBasePath ());
        return LOGGER.traceExit (format ("{0}{1}", hostName, basePath));
    }

    private String getUrl (final String path) {
        LOGGER.traceEntry ("Parameter: {}", path);
        return LOGGER.traceExit (
            format ("{0}{1}", getUrl (), interpolate (requireNonNullElse (path, EMPTY), this.pathParams)));
    }

    private void logRequest () {
        LOGGER.traceEntry ();
        if (this.logSetting.isEnable () && this.logSetting.isRequest ()) {
            final var req = this.response.getRequest ();
            LOGGER.info ("Request URL: {}", req.getPath ());
            req.getHeaders ()
                .forEach ((key, value) -> LOGGER.info ("Request Header: {} => {}", key, value));
            req.getPathParams ()
                .forEach ((key, value) -> LOGGER.info ("Request Path Param: {} => {}", key, value));
            if (isNotEmpty (req.getBody ())) {
                LOGGER.info ("Request Body: {}", req.getBody ());
            }
            final var curl = new CurlCommandGenerator (new Configuration ()).generate (this.request.build ());
            LOGGER.info ("Request Curl: {} ", curl);
        }
        LOGGER.traceExit ();
    }

    private void logResponse () {
        LOGGER.traceEntry ();
        if (this.logSetting.isEnable () && this.logSetting.isResponse ()) {
            LOGGER.info ("Status Code: {}", this.response.getStatusCode ());
            if (isNotEmpty (this.response.getBody ())) {
                LOGGER.info ("Status Message: {}", this.response.getStatusMessage ());
            }
            this.response.getHeaders ()
                .forEach ((k, v) -> LOGGER.info ("Response Header: {} => {}", k, v));
            final var body = JsonUtil.toString (this.response.getBody ());
            if (isNotEmpty (body)) {
                LOGGER.info ("Response Body: {}", body);
            }
        }
        LOGGER.traceExit ();
    }

    private ApiActions method (final RequestMethod method) {
        LOGGER.traceEntry ("Parameter: {}", method);
        try {
            this.request.method (method.name (), this.requestBody);
        } catch (final IllegalArgumentException e) {
            handleAndThrow (ERROR_SETTING_REQUEST_METHOD, e, e.getMessage ());
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
        ApiResponse apiResponse = null;
        if (!isNull (res)) {
            final var headers = new HashMap<String, String> ();
            res.headers ()
                .forEach (entry -> headers.put (entry.getFirst (), entry.getSecond ()));
            try {
                apiResponse = ApiResponse.createResponse ()
                    .request (parseRequest (res.request ()))
                    .statusCode (res.code ())
                    .statusMessage (res.message ())
                    .sentRequestAt (res.sentRequestAtMillis ())
                    .headers (headers)
                    .networkResponse (parseResponse (res.networkResponse ()))
                    .apiSetting (this.apiSetting)
                    .commonApiSetting (this.commonApiSetting)
                    .networkResponse (parseResponse (res.networkResponse ()))
                    .previousResponse (parseResponse (res.priorResponse ()))
                    .receivedResponseAt (res.receivedResponseAtMillis ())
                    .body (JsonUtil.toString (requireNonNullElse (res.body (),
                        ResponseBody.create (EMPTY, parse (JSON.getType ()))).string ()))
                    .create ();
            } catch (final IOException e) {
                handleAndThrow (ERROR_PARSING_RESPONSE_BODY, e);
            }
        }
        return LOGGER.traceExit (apiResponse);
    }

    private void pathParam (final String param, final String value) {
        LOGGER.traceEntry ("Parameter: {}, {}", param, value);
        this.pathParams.put (param, value);
        LOGGER.traceExit ();
    }
}
