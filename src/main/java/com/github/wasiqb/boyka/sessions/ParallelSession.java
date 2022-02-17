package com.github.wasiqb.boyka.sessions;

import org.openqa.selenium.WebDriver;

public class ParallelSession {
    private static final ThreadLocal<Session<? extends WebDriver>> SESSION = new ThreadLocal<> ();

    public static void clear () {
        SESSION.remove ();
    }

    @SuppressWarnings ("unchecked")
    public static <D extends WebDriver> D getDriver () {
        return (D) SESSION.get ()
            .getDriver ();
    }

    public static <D extends WebDriver> void setDriver (final D driver) {
        SESSION.set (new Session<> (driver));
    }
}
