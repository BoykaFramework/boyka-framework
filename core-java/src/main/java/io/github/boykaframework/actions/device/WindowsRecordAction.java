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

import static io.appium.java_client.windows.WindowsStopScreenRecordingOptions.stopScreenRecordingOptions;
import static io.github.boykaframework.actions.drivers.DriverActions.withDriver;
import static io.github.boykaframework.enums.DeviceType.CLOUD;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.VideoUtil.saveVideo;
import static java.time.Duration.ofSeconds;

import io.appium.java_client.windows.WindowsStartScreenRecordingOptions;
import io.github.boykaframework.config.ui.VideoSetting;

/**
 * Handles all the Video recording action on Windows machine.
 *
 * @author Wasiq Bhamla
 * @since 16-Sep-2025
 */
class WindowsRecordAction implements VideoRecordAction {
    @SuppressWarnings ("unchecked")
    @Override
    public WindowsStartScreenRecordingOptions getRecordingOptions (final VideoSetting setting) {
        final var windowsSetting = setting.getWindows ();
        final var options = WindowsStartScreenRecordingOptions.startScreenRecordingOptions ();
        options.withTimeLimit (ofSeconds (setting.getTimeLimit ()));
        options.withFps (windowsSetting.getFps ());
        options.withPreset (windowsSetting.getPreset ()
            .getName ());
        options.withAudioInput (windowsSetting.getAudioDevice ());

        if (windowsSetting.isCaptureClicks ()) {
            options.enableCursorCapture ();
        }
        if (windowsSetting.isCaptureClicks ()) {
            options.enableClicksCapture ();
        }
        if (windowsSetting.isForcedRestart ()) {
            options.enableForcedRestart ();
        } else {
            options.disableForcedRestart ();
        }
        return options;
    }

    @Override
    public void startRecording () {
        final var machineSetting = getSession ().getDesktopSetting ()
            .getMachine ();
        final var setting = machineSetting.getVideo ();
        if (machineSetting.getType () != CLOUD && setting.isEnabled ()) {
            final var options = getRecordingOptions (setting);
            withDriver ().executeScript ("windows: startRecordingScreen", options.build ());
        }
    }

    @Override
    public void stopRecording () {
        final var machineSetting = getSession ().getDesktopSetting ()
            .getMachine ();
        final var setting = machineSetting.getVideo ();
        if (machineSetting.getType () != CLOUD && setting.isEnabled ()) {
            final var options = stopScreenRecordingOptions ();
            final var content = withDriver ().executeScript ("windows: stopRecordingScreen", options.build ());
            saveVideo (content.toString (), setting);
        }
    }
}
