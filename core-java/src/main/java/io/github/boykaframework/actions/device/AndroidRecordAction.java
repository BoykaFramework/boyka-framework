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

import static io.appium.java_client.android.AndroidStopScreenRecordingOptions.stopScreenRecordingOptions;
import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.VideoUtil.saveVideo;
import static java.time.Duration.ofSeconds;
import static java.util.Objects.isNull;

import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.github.boykaframework.config.ui.VideoSetting;

/**
 * Handles all the Android device specific recording actions.
 *
 * @author Wasiq Bhamla
 * @since 16-Sep-2025
 */
class AndroidRecordAction implements VideoRecordAction {
    @SuppressWarnings ("unchecked")
    @Override
    public AndroidStartScreenRecordingOptions getRecordingOptions (final VideoSetting setting) {
        final var androidSetting = setting.getAndroid ();
        final var options = AndroidStartScreenRecordingOptions.startScreenRecordingOptions ();
        if (!isNull (androidSetting)) {
            options.withBitRate (androidSetting.getBitRate () * 100000);
        }
        options.withVideoSize (setting.getSize ())
            .withTimeLimit (ofSeconds (setting.getTimeLimit ()));
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
        final var androidSetting = getSession ().getMobileSetting ()
            .getDevice ();
        final var setting = androidSetting.getVideo ();
        if (androidSetting.getType () != CLOUD && setting.isEnabled ()) {
            final var screen = (CanRecordScreen) getSession ().getDriver ();
            final var options = stopScreenRecordingOptions ();
            final var content = screen.stopRecordingScreen (options);
            saveVideo (content, setting);
        }
    }
}
