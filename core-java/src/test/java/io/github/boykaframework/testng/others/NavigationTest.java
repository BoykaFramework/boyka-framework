/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

package io.github.boykaframework.testng.others;

import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;

import io.github.boykaframework.actions.drivers.NavigateActions;
import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.exception.FrameworkError;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Test class for testing browser navigation class.
 *
 * @author Wasiq Bhamla
 * @since 04-Jan-2024
 */
public class NavigationTest {
    /**
     * Setup test class.
     */
    @BeforeClass
    public void setupClass () {
        createSession (PlatformType.WEB, "test_chrome_no_base_url");
    }

    /**
     * Teardown test class.
     */
    @AfterClass
    public void teardownClass () {
        clearSession ();
    }

    /**
     * Test Empty base URL in config.
     */
    @Test (description = "Test Empty base URL in config", expectedExceptions = FrameworkError.class, expectedExceptionsMessageRegExp = "Base URL is not provided in the config...")
    public void testEmptyBrowserInConfig () {
        NavigateActions.navigate ()
            .toBaseUrl ();
    }
}
