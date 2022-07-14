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
import static com.github.wasiqb.boyka.enums.RequestMethod.DELETE;
import static com.github.wasiqb.boyka.enums.RequestMethod.GET;
import static com.github.wasiqb.boyka.enums.RequestMethod.PATCH;
import static com.github.wasiqb.boyka.enums.RequestMethod.POST;
import static com.github.wasiqb.boyka.enums.RequestMethod.PUT;
import static com.github.wasiqb.boyka.manager.ApiManager.execute;

import com.github.wasiqb.boyka.builders.ApiRequest;
import com.github.wasiqb.boyka.builders.ApiResponse;
import com.github.wasiqb.boyka.testng.api.requests.User;
import org.testng.annotations.Test;

/**
 * Test class for testing Api manager class.
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
public class ApiTest {
    private static final String API_CONFIG_KEY = "test_reqres";

    /**
     * Method to test DELETE request.
     */
    @Test (description = "Test DELETE request", priority = 5)
    public void testDeleteUser () {
        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (DELETE)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .create ();

        execute (request).verifyStatusCode ()
            .isEqualTo (204);
    }

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

        final ApiResponse response = execute (request);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifySchema ("get-user-schema.json");
        response.verifyHeader ("content-type")
            .isEqualTo ("application/json; charset=utf-8");
        response.verifyTextField ("data.first_name")
            .isEqualTo ("Janet");
        response.verifyTextField ("data.last_name")
            .isEqualTo ("Weaver");
    }

    /**
     * Method to test GET request with Query params.
     */
    @Test (description = "Test GET request with Query params", priority = 6)
    public void testGetUserPerPage () {
        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (GET)
            .path ("/users")
            .queryParam ("page", "2")
            .create ();

        final ApiResponse response = execute (request);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyIntField ("page")
            .isEqualTo (2);
        response.verifyIntField ("per_page")
            .isEqualTo (6);
        response.verifyIntField ("total")
            .isEqualTo (12);
        response.verifyIntField ("total_pages")
            .isEqualTo (2);
    }

    /**
     * Method to test PATCH request.
     */
    @Test (description = "Test PATCH request", priority = 4)
    public void testPatchUser () {
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();

        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (PATCH)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .bodyObject (user)
            .create ();

        final ApiResponse response = execute (request);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifySchema ("patch-user-schema.json");
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("updatedAt")
            .isNotNull ();
    }

    /**
     * Method to test PUT request.
     */
    @Test (description = "Test PUT request", priority = 3)
    public void testPutUser () {
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();

        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (PUT)
            .path ("/users/${userId}")
            .pathParam ("userId", "2")
            .bodyObject (user)
            .create ();

        final ApiResponse response = execute (request);
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifySchema ("put-user-schema.json");
        response.verifyTextField ("name")
            .isEqualTo (user.getName ());
        response.verifyTextField ("job")
            .isEqualTo (user.getJob ());
        response.verifyTextField ("updatedAt")
            .isNotNull ();
    }

    /**
     * Method to test POST request.
     */
    @Test (description = "Test POST request for creating a new pet", priority = 1)
    public void testUserCreation () {
        final User user = User.createUser ()
            .name ("Wasiq")
            .job ("Software Engineer")
            .create ();

        final ApiRequest request = createRequest ().configKey (API_CONFIG_KEY)
            .method (POST)
            .path ("/users")
            .bodyObject (user)
            .create ();

        final ApiResponse response = execute (request);
        response.verifyStatusCode ()
            .isEqualTo (201);
        response.verifySchema ("create-user-schema.json");
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
