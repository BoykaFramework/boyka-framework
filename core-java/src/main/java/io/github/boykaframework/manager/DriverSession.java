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

package io.github.boykaframework.manager;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.apache.logging.log4j.LogManager.getLogger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import io.github.boykaframework.actions.interfaces.listeners.BoykaListener;
import io.github.boykaframework.config.FrameworkSetting;
import io.github.boykaframework.config.TestDataSetting;
import io.github.boykaframework.config.api.ApiSetting;
import io.github.boykaframework.config.api.CommonApiSetting;
import io.github.boykaframework.config.ui.mobile.MobileSetting;
import io.github.boykaframework.config.ui.web.WebSetting;
import io.github.boykaframework.enums.ListenerType;
import io.github.boykaframework.enums.Message;
import io.github.boykaframework.enums.PlatformType;
import io.github.boykaframework.utils.ErrorHandler;
import io.github.boykaframework.utils.SettingUtils;
import lombok.Data;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Driver session class containing everything needed to handle current session.
 *
 * @param <D> {@link WebDriver}
 *
 * @author Wasiq Bhamla
 * @since 19-Feb-2022
 */
@SuppressWarnings ("unchecked")
@Data
public class DriverSession<D extends WebDriver> {
    private static final ImmutableSet<ClassPath.ClassInfo> ALL_CLASSES = getAllClasses ();
    private static final Logger                            LOGGER      = getLogger ();

    private static ImmutableSet<ClassPath.ClassInfo> getAllClasses () {
        ImmutableSet<ClassPath.ClassInfo> result = null;
        try {
            result = ClassPath.from (ClassLoader.getSystemClassLoader ())
                .getAllClasses ();
        } catch (final IOException e) {
            ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e);
        }
        return result;
    }

    private       String                                            configKey;
    private       D                                                 driver;
    private       Map<ListenerType, Class<? extends BoykaListener>> listeners;
    private       PlatformType                                      platformType;
    private       ServiceManager                                    serviceManager;
    private final FrameworkSetting                                  setting;
    private       Map<String, Object>                               sharedData;
    private       WebDriverWait                                     wait;

    /**
     * Driver session constructor.
     */
    DriverSession () {
        this.setting = SettingUtils.loadSetting ();
        this.sharedData = new HashMap<> ();
        this.listeners = new EnumMap<> (ListenerType.class);
        LOGGER.traceExit ();
    }

    /**
     * Clear all the listeners.
     */
    public void clearListeners () {
        this.listeners.clear ();
    }

    /**
     * Clears all the shared data for the session
     */
    public void clearSharedData () {
        this.sharedData.clear ();
    }

    /**
     * Gets API specific settings
     *
     * @return {@link ApiSetting} instance
     */
    public ApiSetting getApiSetting () {
        return this.setting.getApiSetting (this.configKey);
    }

    /**
     * Gets Common API specific settings
     *
     * @return {@link CommonApiSetting} instance
     */
    public CommonApiSetting getCommonApiSetting () {
        return this.setting.getCommonSetting ()
            .getApi ();
    }

    /**
     * Gets the listener for provided listener type.
     *
     * @param listenerType {@link ListenerType}
     * @param <T> Type of the listener
     *
     * @return Listener object.
     */
    public <T extends BoykaListener> T getListener (final ListenerType listenerType) {
        T result = null;
        if (this.listeners.isEmpty ()) {
            loadAllListeners ();
        }
        final var listener = this.listeners.get (listenerType);
        if (isNull (listener)) {
            return null;
        }
        try {
            final var constructor = listener.getConstructor ();
            result = (T) constructor.newInstance ();
        } catch (final NoSuchMethodException | InstantiationException | IllegalAccessException |
                       InvocationTargetException e) {
            ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e, listener);
        }
        return result;
    }

    /**
     * Gets Current Mobile settings
     *
     * @return Mobile setting
     */
    public MobileSetting getMobileSetting () {
        return this.setting.getUi ()
            .getMobileSetting (this.configKey);
    }

    /**
     * Gets the shared data.
     *
     * @param key Key of data to be retrieved
     * @param <T> Type of data
     *
     * @return Saved data
     */
    public <T> T getSharedData (final String key) {
        return (T) this.sharedData.get (key);
    }

    /**
     * Gets the test data settings.
     *
     * @return Test data setting.
     */
    public TestDataSetting getTestDataSetting () {
        return this.setting.getData ();
    }

    /**
     * Gets current Web settings
     *
     * @return Web Setting
     */
    public WebSetting getWebSetting () {
        return this.setting.getUi ()
            .getWebSetting (this.configKey);
    }

    /**
     * Removes the shared data.
     *
     * @param key Key to shared data
     */
    public void removeSharedData (final String key) {
        this.sharedData.remove (key);
    }

    /**
     * Save shared data.
     *
     * @param key Key of data
     * @param data Data to be saved
     * @param <T> Type of data
     */
    public <T> void setSharedData (final String key, final T data) {
        this.sharedData.put (key, data);
    }

    private void addListeners (final ArrayList<String> listenerList, final String packageName) {
        if (isNotEmpty (packageName)) {
            ALL_CLASSES.stream ()
                .filter (clazz -> clazz.getPackageName ()
                    .startsWith (packageName))
                .map (ClassPath.ClassInfo::getName)
                .forEach (listenerList::add);
        }
    }

    private void loadAllListeners () {
        final var packageName = getSetting ().getListenersPackage ();
        final var listenerList = new ArrayList<String> ();
        addListeners (listenerList, packageName);
        loadAllListeners (listenerList);
    }

    private <T extends BoykaListener> void loadAllListeners (final List<String> listenerList) {
        listenerList.forEach (listener -> {
            try {
                final var cls = (Class<T>) Class.forName (listener);
                final var interfaceType = cls.getInterfaces ()[0];
                final var listenerType = ListenerType.valueOf (interfaceType);
                final var isBoykaListener = BoykaListener.class.isAssignableFrom (cls);
                if (isBoykaListener && !this.listeners.containsKey (listenerType)) {
                    this.listeners.put (listenerType, cls);
                }
            } catch (final ClassNotFoundException e) {
                ErrorHandler.handleAndThrow (Message.INVALID_LISTENER_FOUND, e, listener);
            }
        });
    }
}
