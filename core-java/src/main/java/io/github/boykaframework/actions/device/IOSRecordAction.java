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

package io.github.boykaframework.actions.device;

import static io.appium.java_client.ios.IOSStartScreenRecordingOptions.startScreenRecordingOptions;
import static io.appium.java_client.ios.IOSStopScreenRecordingOptions.stopScreenRecordingOptions;
import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.VideoUtil.saveVideo;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;

import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.github.boykaframework.config.ui.VideoSetting;

/**
 * iOS specific video recording action implementation.
 *
 * @author Wasiq Bhamla
 * @since 16-Sep-2025
 */
class IOSRecordAction implements VideoRecordAction {
    @SuppressWarnings ("unchecked")
    @Override
    public IOSStartScreenRecordingOptions getRecordingOptions (final VideoSetting setting) {
        final var iosSetting = setting.getIos ();
        final var options = startScreenRecordingOptions ();
        if (!isNull (iosSetting)) {
            options.withFps (iosSetting.getFps ())
                .withVideoQuality (iosSetting.getQuality ())
                .withVideoType (iosSetting.getCodec ());
        }
        options.withTimeLimit (ofSeconds (setting.getTimeLimit ()));
        if (!isNull (setting.getSize ())) {
            options.withVideoScale (setting.getSize ());
        }
        return options;
    }

    @Override
    public void startRecording () {
        final var mobileSetting = getSession ().getMobileSetting ()
            .getDevice ();
        final var setting = mobileSetting.getVideo ();
        if (mobileSetting.getType () != CLOUD && setting.isEnabled ()) {
            final var screen = (CanRecordScreen) getSession ().getDriver ();
            final var options = getRecordingOptions (setting);
            screen.startRecordingScreen (options);
        }
    }

    @Override
    public void stopRecording () {
        final var iosSetting = getSession ().getMobileSetting ()
            .getDevice ();
        final var setting = iosSetting.getVideo ();
        if (iosSetting.getType () != CLOUD && setting.isEnabled ()) {
            final var screen = (CanRecordScreen) getSession ().getDriver ();
            final var options = stopScreenRecordingOptions ();
            final var content = screen.stopRecordingScreen (options);
            saveVideo (content, setting);
        }
    }
}
