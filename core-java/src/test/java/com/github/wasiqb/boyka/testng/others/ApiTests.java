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

package com.github.wasiqb.boyka.testng.others;

import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.manager.ApiManager.withRequest;

import com.github.wasiqb.boyka.exception.FrameworkError;
import org.testng.annotations.Test;

/**
 * Other API related tests
 *
 * @author Wasiq Bhamla
 * @since 27-Aug-2022
 */
public class ApiTests {
    /**
     * Tests invalid config key.
     */
    @Test (description = "Test Invalid API config key", expectedExceptions = FrameworkError.class)
    public void testInvalidApiConfigKey () {
        final var userRequest = createRequest ().configKey ("test_req")
            .method (GET)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .create ();
        withRequest (userRequest).execute ();
    }
}
