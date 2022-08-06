package com.github.wasiqb.boyka.api.requests;

import lombok.Builder;
import lombok.Getter;

/**
 * Fields for new token generation
 * @author Faisal Khatri
 * @since 22/07/2022
 */
@Getter
@Builder
public class Tokencreds {
    private String password;
    private String username;
}
