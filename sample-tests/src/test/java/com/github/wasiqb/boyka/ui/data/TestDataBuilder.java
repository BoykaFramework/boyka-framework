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

import com.github.javafaker.Faker;

/**
 * This class builds the data for registering user and setting billing data.
 *
 * @author Faisal Khatri
 * @since 8/3/2022
 **/
public class TestDataBuilder {

    private static final Faker FAKER = Faker.instance ();

    public static BillingData getBillingData () {
        return BillingData.builder ()
            .firstName (FAKER.name ()
                .firstName ())
            .lastName (FAKER.name ()
                .lastName ())
            .addressLineOne (FAKER.address ()
                .streetAddress ())
            .city (FAKER.address ()
                .cityName ())
            .postCode (FAKER.address ()
                .zipCode ())
            .country ("India")
            .state ("Maharashtra")
            .build ();
    }

    public static RegisterUserData getRegisterUserData () {
        return RegisterUserData.builder ()
            .firstName (FAKER.name ()
                .firstName ())
            .lastName (FAKER.name ()
                .lastName ())
            .email (FAKER.internet ()
                .emailAddress ())
            .telephone (String.valueOf (FAKER.number ()
                .numberBetween (9000000000L, 9999999999L)))
            .build ();
    }
}
