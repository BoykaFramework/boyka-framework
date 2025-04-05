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

package io.github.boykaframework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * All iOS permissions
 *
 * @author Wasiq Bhamla
 * @since 22-Dec-2024
 */
@AllArgsConstructor
@Getter
public enum Permissions {
    /**
     * Calendar
     */
    CALENDAR ("calendar"),
    /**
     * Camera
     */
    CAMERA ("camera"),
    /**
     * Contacts
     */
    CONTACTS ("contacts"),
    /**
     * Face ID
     */
    FACE_ID ("faceid"),
    /**
     * Health
     */
    HEALTH ("health"),
    /**
     * Home Kit
     */
    HOME_KIT ("homekit"),
    /**
     * Location
     */
    LOCATION ("location"),
    /**
     * Media library
     */
    MEDIA_LIBRARY ("medialibrary"),
    /**
     * Microphone
     */
    MICROPHONE ("microphone"),
    /**
     * Motion
     */
    MOTION ("motion"),
    /**
     * Notification
     */
    NOTIFICATIONS ("notifications"),
    /**
     * Reminders
     */
    REMINDERS ("reminders"),
    /**
     * Siri
     */
    SIRI ("siri"),
    /**
     * Speech
     */
    SPEECH ("speech"),
    /**
     * User tracking
     */
    USER_TRACKING ("userTracking");

    private final String name;
}
