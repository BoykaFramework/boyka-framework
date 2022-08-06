package com.github.wasiqb.boyka.api.requests;

/**
 * This class provides the data for generating new token.
 * @author Faisal Khatri
 * @since 22/07/2022
 */
public class TokenBuilder {
    public Tokencreds tokenBuilder () {
        return Tokencreds.builder ()
            .username ("admin")
            .password ("password123")
            .build ();
    }

}
