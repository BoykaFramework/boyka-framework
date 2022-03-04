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

package com.github.wasiqb.boyka.testng.api;

import static com.github.wasiqb.boyka.builders.ApiRequest.createRequest;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.manager.ApiManager;
import com.github.wasiqb.boyka.testng.api.requests.User;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
public class TestApi {
    private static final String API_CONFIG_KEY = "test_reqres";

    /**
     * Method to test GET request.
     */
    @Test (description = "Test GET request", priority = 2)
    public void testGetUser () {
        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (GET)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .create ();

        final ApiResponse response = ApiManager.execute (request);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("data.first_name")
            .isEqualTo ("Janet");
        response.verifyTextField ("data.last_name")
            .isEqualTo ("Weaver");
    }

    /**
     * Method to test POST request.
     */
    @Test (description = "Test POST request for creating a new pet", priority = 1)
    public void testUserCreation () {
        final User user = User.builder ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .build ();

        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (POST)
            .path ("/users")
            .bodyObject (user)
            .create ();

        final ApiResponse response = ApiManager.execute (request);
        response.verifyStatusCode ()
            .isEqualTo (201);
        response.verifyTextField ("id")
            .isNotNull ();
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("createdAt")
            .isNotNull ();
    }
}