package com.github.wasiqb.boyka.uitests.testdata;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Faisal Khatri
 * @since 8/3/2022
 **/

@Builder
@Getter
public class BillingData {
    private String addressLineOne;
    private String city;
    private String country;
    private String firstName;
    private String lastName;
    private String postCode;
    private String state;
}
