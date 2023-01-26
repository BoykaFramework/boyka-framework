/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.config.ui.mobile.device;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import lombok.Data;

@Data
public class WDASetting {
    private String  agentPath            = EMPTY;
    private String  bootstrapPath        = EMPTY;
    private int     connectionTimeout    = 60;
    private int     launchTimeout        = 60;
    private int     localPort            = 8100;
    private String  signingId            = EMPTY;
    private int     startupRetries       = 2;
    private int     startupRetryInterval = 10;
    private String  teamId               = EMPTY;
    private String  updateBundleId       = EMPTY;
    private boolean useNew;
    private boolean usePrebuilt;
}
