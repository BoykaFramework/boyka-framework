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

package io.github.boykaframework.config.logging;

import lombok.Data;

/**
 * Logger related settings
 *
 * @author Wasiq Bhamla
 * @since 02-Apr-2025
 */
@Data
public class LoggerSetting {
    private ArchiveSetting archive     = new ArchiveSetting ();
    private boolean        consoleLogs = true;
    private String         logsFolder  = "./logs";
}
