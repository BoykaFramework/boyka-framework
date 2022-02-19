package com.github.wasiqb.boyka.testng.api;

import static java.lang.System.getenv;

import com.github.wasiqb.boyka.manager.ApiManager;
import org.testng.annotations.Test;

public class TestApi {
    @Test
    public void testGetMethod () {
        final ApiManager response = ApiManager.createRequest ("test_browserstack")
            .basicAuth (getenv ("CLOUD_USER"), getenv ("CLOUD_KEY"))
            .get ("/plan.json");
        response.verifyStatusCode ()
            .isEqualTo (200);
        response.verifyTextField ("automate_plan")
            .isEqualTo ("Automate Mobile");
    }
}