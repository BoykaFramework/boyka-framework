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

package io.github.boykaframework.config.ui.desktop.machine;

import lombok.Data;

/**
 * Desktop application setting.
 *
 * @author Wasiq Bhamla
 * @since 24-May-2025
 */
@Data
public class ApplicationSetting {
    private String  bundleId;
    private boolean skipAppKill;
}
