package com.restfulbooker.requests;

/**
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
