package com.github.wasiqb.boyka.exception;

public class FrameworkError extends Error {
    public FrameworkError (final String message) {
        super (message);
    }

    public FrameworkError (final String message, final Throwable cause) {
        super (message, cause);
    }
}
