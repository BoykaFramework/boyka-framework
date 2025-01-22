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

package io.github.boykaframework.testng.api.ssl;

import static io.github.boykaframework.actions.api.ApiActions.withRequest;
import static io.github.boykaframework.builders.ApiRequest.createRequest;
import static io.github.boykaframework.enums.PlatformType.API;
import static io.github.boykaframework.enums.RequestMethod.GET;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;

import org.testng.annotations.Test;

/**
 * Test Bad SSL API.
 *
 * @author Wasiq Bhamla
 * @since 28-Jan-2024
 */
public class ApiSecurityTest {
    /**
     * Test bad host name.
     */
    @Test
    public void testBadHostName () {
        try {
            createSession (API, "test_bad_host_name_wo_verify_hn");
            final var request = createRequest ().method (GET)
                .create ();
            final var response = withRequest (request).execute ();

            response.verifyStatusCode ()
                .isEqualTo (200);
        } finally {
            clearSession ();
        }
    }

    /**
     * Test Bad SSL API.
     */
    @Test
    public void testBadSsl () {
        try {
            createSession (API, "test_bad_ssl_wo_verify");
            final var request = createRequest ().method (GET)
                .create ();
            final var response = withRequest (request).execute ();

            response.verifyStatusCode ()
                .isEqualTo (200);
        } finally {
            clearSession ();
        }
    }
}
