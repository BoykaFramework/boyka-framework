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

package com.github.wasiqb.boyka.enums;

import java.util.Arrays;

import com.github.wasiqb.boyka.actions.interfaces.listeners.BoykaListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.api.IApiActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IAlertActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IContextActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.ICookieActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IDeviceActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IDriverActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IFrameActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.INavigateActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.drivers.IWindowActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IClickableActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IDropDownActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IElementActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IFingerActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.IFingersActionsListener;
import com.github.wasiqb.boyka.actions.interfaces.listeners.elements.ITextBoxActionsListener;
import lombok.Getter;

/**
 * List all the available listener types in the framework.
 *
 * @author Wasiq Bhamla
 * @since 07-Apr-2023
 */
@Getter
public enum ListenerType {
    /**
     * Alert Actions listener.
     */
    ALERT_ACTION (IAlertActionsListener.class),
    /**
     * API actions listener.
     */
    API_ACTION (IApiActionsListener.class),
    /**
     * Mouse actions listener.
     */
    CLICKABLE_ACTION (IClickableActionsListener.class),
    /**
     * Context actions listener.
     */
    CONTEXT_ACTION (IContextActionsListener.class),
    /**
     * Cookies action listener.
     */
    COOKIE_ACTION (ICookieActionsListener.class),
    /**
     * Driver action listener.
     */
    DRIVER_ACTION (IDriverActionsListener.class),
    /**
     * Device action listener.
     */
    DEVICE_ACTION (IDeviceActionsListener.class),
    /**
     * Drop down action listener.
     */
    DROP_DOWN_ACTION (IDropDownActionsListener.class),
    /**
     * Element action listener.
     */
    ELEMENT_ACTION (IElementActionsListener.class),
    /**
     * Fingers action listener.
     */
    FINGERS_ACTION (IFingersActionsListener.class),
    /**
     * Finger action listener.
     */
    FINGER_ACTION (IFingerActionsListener.class),
    /**
     * Frame action listener.
     */
    FRAME_ACTION (IFrameActionsListener.class),
    /**
     * Navigate action listener.
     */
    NAVIGATE_ACTION (INavigateActionsListener.class),
    /**
     * Text Box action listener.
     */
    TEXT_BOX_ACTION (ITextBoxActionsListener.class),
    /**
     * Window action listener.
     */
    WINDOW_ACTION (IWindowActionsListener.class);

    /**
     * Convert provided listener class to Listener type enum.
     *
     * @param className Class name of probable listener interface
     *
     * @return Listener type item.
     */
    public static ListenerType valueOf (final Class<?> className) {
        return Arrays.stream (values ())
            .filter (l -> l.getClassName ()
                .equals (className))
            .findFirst ()
            .orElse (null);
    }

    private final Class<? extends BoykaListener> className;

    ListenerType (final Class<? extends BoykaListener> className) {
        this.className = className;
    }
}
