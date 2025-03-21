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

package io.github.boykaframework.actions.data;

import static io.github.boykaframework.enums.ListenerType.LANGUAGE_ACTION;
import static io.github.boykaframework.manager.ParallelSession.getSession;
import static io.github.boykaframework.utils.JsonUtil.fromFile;
import static java.lang.System.getProperty;
import static java.text.MessageFormat.format;
import static java.util.Optional.ofNullable;

import java.nio.file.Path;

import io.github.boykaframework.actions.interfaces.data.ILanguageAction;
import io.github.boykaframework.actions.interfaces.listeners.data.ILanguageActionListener;
import io.github.boykaframework.config.LanguageSetting;

/**
 * Handles language data files.
 *
 * @author Wasiq Bhamla
 * @since 15-Mar-2025
 */
public class LanguageAction implements ILanguageAction {
    /**
     * Handles language content.
     *
     * @return Language action
     */
    public static LanguageAction withLanguage () {
        return new LanguageAction ();
    }

    private final String                  filePath;
    private final ILanguageActionListener listener;

    private LanguageAction () {
        this.listener = getSession ().getListener (LANGUAGE_ACTION);

        final var setting = getLanguageSettings ();
        final var fileName = setting.getLanguage ()
            .name ()
            .toLowerCase ();
        this.filePath = Path.of (getProperty ("user.dir"), "src/test/resources", setting.getPath (),
                format ("{0}.json", fileName))
            .toString ();
    }

    @Override
    public <T> T getLanguage (final Class<T> clazz) {
        ofNullable (this.listener).ifPresent (ILanguageActionListener::onGetLanguage);
        return fromFile (this.filePath, clazz);
    }

    private LanguageSetting getLanguageSettings () {
        return switch (getSession ().getPlatformType ()) {
            case ANDROID, IOS -> getSession ().getMobileSetting ()
                .getDevice ()
                .getLanguage ();
            case API -> getSession ().getApiSetting ()
                .getLanguage ();
            case WEB -> getSession ().getWebSetting ()
                .getLanguage ();
        };
    }
}
