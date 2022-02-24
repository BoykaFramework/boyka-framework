package com.github.wasiqb.boyka.sessions;

import com.github.wasiqb.boyka.config.FrameworkSetting;
import com.github.wasiqb.boyka.enums.ApplicationType;
import org.openqa.selenium.WebDriver;

public final class ParallelSession {
    private static final ThreadLocal<DriverSession<? extends WebDriver>> SESSION = new ThreadLocal<> ();

    public static void clear () {
        SESSION.remove ();
    }

    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> D getDriver () {
        return (D) getSession ().getDriver ();
    }

    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> DriverSession<D> getSession () {
        return (DriverSession<D>) SESSION.get ();
    }

    public static <D extends WebDriver> void setDriver (final ApplicationType applicationType, final D driver,
        final FrameworkSetting setting) {
        SESSION.set (new DriverSession<> (applicationType, driver, setting));
    }

    private ParallelSession () {
        // Utility class
    }
}
