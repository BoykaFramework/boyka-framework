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

package io.github.boykaframework.actions.drivers;

import static io.github.boykaframework.actions.drivers.WindowActions.onWindow;
import static io.github.boykaframework.enums.Message.RECORDING_NOT_STARTED;
import static io.github.boykaframework.enums.Message.RECORDING_NOT_STOPPED;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.ErrorHandler.handleAndThrow;
import static java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment;
import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static org.apache.commons.io.FileUtils.createParentDirectories;
import static org.apache.logging.log4j.LogManager.getLogger;
import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.github.boykaframework.config.ui.web.VideoSetting;
import org.apache.logging.log4j.Logger;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.Registry;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;

class CustomVideoRecorder extends ScreenRecorder {
    private static final Logger         LOGGER           = getLogger ();
    private static final VideoSetting   RECORDER_SETTING = getSession ().getWebSetting ()
        .getVideo ();
    private static       ScreenRecorder screenRecorder;

    static void startRecording () {
        if (checkIfEnabled ()) {
            LOGGER.info ("Started Video screen recording...");
            final var file = new File (RECORDER_SETTING.getPath ());

            final var screenSize = onWindow ().viewportSize ();
            final var width = screenSize.getWidth ();
            final var height = screenSize.getHeight ();

            final var captureSize = new Rectangle (0, 0, width, height);

            final var gc = getLocalGraphicsEnvironment ().getDefaultScreenDevice ()
                .getDefaultConfiguration ();

            try {
                final Format fileFormat = new Format (MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI);
                final Format screenFormat = new Format (MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey,
                    ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE, CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                    DepthKey, 24, FrameRateKey, Rational.valueOf (15), QualityKey, 1.0f, KeyFrameIntervalKey,
                    (15 * 60));
                final Format mouseFormat = new Format (MediaTypeKey, FormatKeys.MediaType.VIDEO, EncodingKey,
                    ScreenRecorder.ENCODING_BLACK_CURSOR, FrameRateKey, Rational.valueOf (30));

                screenRecorder = new ScreenRecorder (gc, null, fileFormat, screenFormat, mouseFormat, null, file);
                screenRecorder.start ();
            } catch (final IOException | AWTException e) {
                handleAndThrow (RECORDING_NOT_STARTED, e);
            }
        } else {
            LOGGER.warn ("Video screen recording is Disabled, cannot start...");
        }
    }

    static void stopRecording () {
        if (checkIfEnabled ()) {
            LOGGER.info ("Stopping Video screen recording...");
            try {
                screenRecorder.stop ();
            } catch (final IOException e) {
                handleAndThrow (RECORDING_NOT_STOPPED, e);
            }
        } else {
            LOGGER.warn ("Video screen recording is Disabled, cannot stop...");
        }
    }

    private static boolean checkIfEnabled () {
        return !isNull (RECORDER_SETTING) && RECORDER_SETTING.isEnabled ();
    }

    private final String prefix;

    private CustomVideoRecorder (final GraphicsConfiguration cfg, final Rectangle captureArea, final Format fileFormat,
        final Format screenFormat, final Format mouseFormat, final Format audioFormat, final File movieFolder)
        throws IOException, AWTException {
        super (cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFolder);
        this.prefix = RECORDER_SETTING.getPrefix ();
    }

    @Override
    protected File createMovieFile (final Format fileFormat) throws IOException {
        if (!this.movieFolder.exists ()) {
            createParentDirectories (this.movieFolder);
        } else if (!this.movieFolder.isDirectory ()) {
            throw new IOException (format ("\"{0}\" is not a directory.", this.movieFolder));
        }
        final var dateFormat = new SimpleDateFormat ("yyyyMMdd-HHmmss");
        return new File (this.movieFolder, format ("{0}-{1}.{2}", this.prefix, dateFormat.format (new Date ()),
            Registry.getInstance ()
                .getExtension (fileFormat)));
    }
}
