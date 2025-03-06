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

package io.github.boykaframework.testng.api.postman;

import static io.github.boykaframework.actions.api.ApiActions.withRequest;
import static io.github.boykaframework.enums.ContentType.MULTIPART_FORM_DATA;
import static io.github.boykaframework.enums.ContentType.PLAIN_TEXT;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.enums.RequestMethod.GET;
import static io.github.boykaframework.enums.RequestMethod.POST;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static java.lang.System.getProperty;
import static java.nio.file.Path.of;
import static java.text.MessageFormat.format;

import io.github.boykaframework.builders.ApiRequest;
import io.github.boykaframework.exception.FrameworkError;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class to test Postman related samples.
 *
 * @author Wasiq Bhamla
 * @since 09-Mar-2023
 */
public class ApiPostmanTest {
    /**
     * Setup API Test.
     */
    @BeforeClass (description = "Setup test class")
    public void setupTestClass () {
        createSession (API, "test_postman");
    }

    /**
     * Clean up Test class.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        clearSession ();
    }

    /**
     * Test form data request body related API request.
     */
    @Test (description = "Test Form body POST request")
    public void testFormBodyRequest () {
        final var fileName = "test-file.txt";
        final var request = ApiRequest.createRequest ()
            .contentType (MULTIPART_FORM_DATA)
            .formBody ("strange", "boom")
            .formBody ("test", "abc")
            .formBody ("profile", of (getProperty ("user.dir"), "src/test/resources", fileName).toString ())
            .method (POST)
            .path ("/post")
            .create ();

        final var response = withRequest (request).execute ();
        response.verifyStatusCode ()
            .isEqualTo (200);

        response.verifyTextField ("form.strange")
            .isEqualTo ("boom");
        response.verifyTextField (format ("files[\"{0}\"]", fileName))
            .isNotEmpty ();
    }

    /**
     * Test plain text body in GET method.
     */
    @Test (expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Error setting request method: method GET must not have a request body.")
    public void testPlainTextGetRequest () {
        final var requestBody = """
            Duis posuere augue vel cursus pharetra. In luctus a ex nec pretium. Praesent neque quam, tincidunt nec leo eget, rutrum vehicula magna.
            Maecenas consequat elementum elit, id semper sem tristique et. Integer pulvinar enim quis consectetur interdum volutpat.
            """;
        final var request = ApiRequest.createRequest ()
            .contentType (PLAIN_TEXT)
            .method (GET)
            .body (requestBody)
            .queryParam ("test", "123")
            .path ("/get")
            .create ();
        final var response = withRequest (request).execute ();

        response.verifyStatusCode ()
            .isEqualTo (200);
    }
}
