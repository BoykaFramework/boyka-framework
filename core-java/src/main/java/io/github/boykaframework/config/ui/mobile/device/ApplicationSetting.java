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

package io.github.boykaframework.config.ui.mobile.device;

import static io.github.boykaframework.enums.ApplicationType.NATIVE;

import io.github.boykaframework.config.BoykaConfig;
import io.github.boykaframework.enums.ApplicationType;
import io.github.boykaframework.enums.Browser;
import lombok.Data;

/**
 * Application specific settings.
 *
 * @author Wasiq Bhamla
 * @since 13-Sept-2022
 */
@Data
public class ApplicationSetting implements BoykaConfig {
    private String          baseUrl;
    private Browser         browser;
    private String          bundleId;
    private Integer         chromeDriverPort = 0;
    private boolean         external;
    private Integer         installTimeout   = 30;
    private String          path;
    private ApplicationType type             = NATIVE;
    private String          waitActivity;
    private Integer         waitTimeout      = 30;
}
