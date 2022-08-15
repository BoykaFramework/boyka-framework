/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.ui.data;

import lombok.Builder;
import lombok.Getter;

/**
 * Billing data fields
 *
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
