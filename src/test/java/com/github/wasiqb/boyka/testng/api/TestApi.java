package com.github.wasiqb.boyka.testng.api;

import com.github.wasiqb.boyka.manager.ApiManager;
import org.testng.annotations.Test;

public class TestApi {
    @Test
    public void testGetMethod () {
        final ApiManager response = ApiManager.createRequest ("test_reqres")
            .pathParam ("userId", 2)
            .get ("/users/{userId}");
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyIntField ("data.id")
            .isEqualTo (2);
    }
}