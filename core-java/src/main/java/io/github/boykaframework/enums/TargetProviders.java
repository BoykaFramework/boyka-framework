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

package io.github.boykaframework.enums;

import static io.github.boykaframework.enums.Protocol.HTTP;
import static io.github.boykaframework.enums.Protocol.HTTPS;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Supported cloud providers.
 *
 * @author Wasiq Bhamla
 * @since 24-Feb-2022
 */
@Getter
@AllArgsConstructor
public enum TargetProviders {
    /**
     * BrowserStack.
     */
    BROWSER_STACK ("hub-cloud.browserstack.com", "bstack", HTTPS),
    /**
     * Lambda Test for Mobile.
     */
    LAMBDA_TEST_MOBILE ("mobile-hub.lambdatest.com", "lt", HTTPS),
    /**
     * Lambda Test for Web.
     */
    LAMBDA_TEST_WEB ("hub.lambdatest.com", "lt", HTTPS),
    /**
     * None.
     */
    LOCAL ("127.0.0.1", EMPTY, HTTP);

    private final String   host;
    private final String   prefix;
    private final Protocol protocol;
}
