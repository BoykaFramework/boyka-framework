package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.sessions.ParallelSession.getDriver;

public final class DriverActions {
    public static void navigateTo (final String url) {
        getDriver ().get (url);
    }

    private DriverActions () {
        // Utility class.
    }
}
