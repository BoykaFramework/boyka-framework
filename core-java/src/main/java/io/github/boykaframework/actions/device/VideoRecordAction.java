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

import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.github.boykaframework.config.ui.VideoSetting;

/**
 * Handles all the Video recording action.
 *
 * @author Wasiq Bhamla
 * @since 16-Sep-2025
 */
public interface VideoRecordAction {
    /**
     * Get the recording options set for device.
     *
     * @param setting video settings
     * @param <T> type of options
     *
     * @return recording options
     */
    <T extends BaseStartScreenRecordingOptions<T>> T getRecordingOptions (VideoSetting setting);

    /**
     * Start video recording on device.
     */
    void startRecording ();

    /**
     * Stop video recording on device and save the video file.
     */
    void stopRecording ();
}
