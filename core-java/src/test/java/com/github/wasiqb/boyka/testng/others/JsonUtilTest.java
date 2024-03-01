/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.others;

import static com.github.wasiqb.boyka.utils.JsonUtil.fromFile;
import static com.google.common.truth.Truth.assertWithMessage;
import static java.lang.System.getProperty;

import java.nio.file.Path;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.exception.FrameworkError;
import com.github.wasiqb.boyka.utils.JsonUtil;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/22/2022
 */
public class JsonUtilTest {
    /**
     * This method verifies the Error when file is not found.
     */
    @Test (expectedExceptions = FrameworkError.class)
    public void testFromFile () {
        final var configPath = Path.of (getProperty ("user.dir"), "boyka-config.json")
            .toString ();
        fromFile (configPath, FrameworkSetting.class);
    }

    /**
     * This method verifies the output of JSON util toString() method.
     */
    @Test
    public void testToString () {
        assertWithMessage ("JsonUtil").that (JsonUtil.toString ("Created"))
            .isEqualTo ("Created");
    }
}
