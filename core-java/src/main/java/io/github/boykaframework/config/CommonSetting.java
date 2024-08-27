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

package io.github.boykaframework.config;

import io.github.boykaframework.config.api.ApiSetting;
import io.github.boykaframework.config.ui.CommonUiSetting;
import lombok.Data;

/**
 * Common Settings block.
 *
 * @author Wasiq Bhamla
 * @since 19-Jun-2024
 */
@Data
public class CommonSetting implements BoykaConfig {
    private ApiSetting      api = new ApiSetting ();
    private CommonUiSetting ui  = new CommonUiSetting ();
}
