/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.sessions;

import static java.time.Duration.ofSeconds;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import lombok.Getter;
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
@Getter
public class DriverSession<D extends WebDriver> {
    private final ApplicationType  applicationType;
    private final D                driver;
    private final FrameworkSetting setting;
    private       WebDriverWait    wait;

    /**
     * Driver session constructor.
     *
     * @param applicationType {@link ApplicationType}
     * @param driver Generic type of {@link WebDriver}
     * @param setting {@link FrameworkSetting} instance
     */
    public DriverSession (final ApplicationType applicationType, final D driver, final FrameworkSetting setting) {
        this.applicationType = applicationType;
        this.setting = setting;
        this.driver = driver;
        setDriverWaits ();
    }

    private void setDriverWaits () {
        final var playback = this.setting.getUi ()
            .getPlayback ();
        final var timeouts = this.driver.manage ()
            .timeouts ();
        timeouts.implicitlyWait (ofSeconds (playback.getImplicitWait ()));
        if (this.applicationType == ApplicationType.WEB) {
            timeouts.pageLoadTimeout (ofSeconds (playback.getPageLoadTimeout ()));
            timeouts.scriptTimeout (ofSeconds (playback.getScriptTimeout ()));
        }
        this.wait = new WebDriverWait (getDriver (), ofSeconds (playback.getExplicitWait ()));
    }
}
