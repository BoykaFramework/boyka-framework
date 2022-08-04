package com.github.wasiqb.boyka.ui.data;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
@Builder
@Getter
public class RegisterUserData {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String telephone;
}
