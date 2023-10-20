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

package com.github.wasiqb.boyka.utils;

import static com.github.wasiqb.boyka.enums.Message.ERROR_SAVING_VIDEO;
import static com.github.wasiqb.boyka.manager.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.handleAndThrow;
import static java.text.MessageFormat.format;
import static org.apache.commons.io.FileUtils.writeByteArrayToFile;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;

import com.github.wasiqb.boyka.config.ui.mobile.device.VideoSetting;
import org.apache.logging.log4j.Logger;

/**
 * Video related util methods.
 *
 * @author Wasiq Bhamla
 * @since 14-Oct-2023
 */
public final class VideoUtil {
    private static final Logger LOG = getLogger ();

    /**
     * Save Mobile video content to file.
     *
     * @param content Video content
     */
    public static void saveVideo (final String content) {
        final var decode = Base64.getDecoder ()
            .decode (content);
        final var setting = getSession ().getMobileSetting ()
            .getDevice ()
            .getVideo ();
        saveVideo (decode, setting);
    }

    private static void saveVideo (final byte[] decode, final VideoSetting setting) {
        try {
            final var path = setting.getPath ();
            final var prefix = setting.getPrefix ();
            final var date = new SimpleDateFormat ("yyyyMMdd-HHmmss");
            final var timeStamp = date.format (Calendar.getInstance ()
                .getTime ());
            final var fileName = format ("{0}/{1}-{2}.{3}", path, prefix, timeStamp, "mp4");
            LOG.info ("Saving video recording to [{}] path...", fileName);
            writeByteArrayToFile (new File (fileName), decode);
        } catch (final IOException e) {
            handleAndThrow (ERROR_SAVING_VIDEO, e);
        }
    }

    private VideoUtil () {
        // Util class.
    }
}
