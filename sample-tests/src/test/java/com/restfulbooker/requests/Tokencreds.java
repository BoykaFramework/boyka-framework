package com.restfulbooker.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * @author Faisal Khatri
 * @since 22/07/2022
 */
@Getter
@Builder
public class Tokencreds {
    private String username;
    private String password;
}
