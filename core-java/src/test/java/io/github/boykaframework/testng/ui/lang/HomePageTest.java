/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

package io.github.boykaframework.testng.ui.lang;

import static io.github.boykaframework.enums.PlatformType.WEB;
import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.lang.actions.HomePageAction.verifyPageTitle;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Home Page Test for language.
 *
 * @author Wasiq Bhamla
 * @since 19-Mar-2025
 */
public class HomePageTest {
    private String targetLanguage;

    /**
     * Setting up Test class.
     *
     * @param driverKey Config key
     * @param language Target Language
     */
    @Parameters ({ "driverKey", "language" })
    @BeforeTest
    public void setupTest (final String driverKey, final String language) {
        createSession (WEB, driverKey);
        this.targetLanguage = language;
    }

    /**
     * Tear down Test class.
     */
    @AfterTest
    public void tearDownTest () {
        clearSession ();
    }

    /**
     * Test Home page in different languages.
     */
    @Test (description = "Test Home page in different language")
    public void testHomePage () {
        verifyPageTitle (this.targetLanguage);
    }
}
