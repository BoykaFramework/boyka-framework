/*
 * MIT License
 *
 * Copyright (c) 2024, Boyka Framework
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

package io.github.boykaframework.testng.ui.jiomeet.pages;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

@Getter
public class GuestJoinPage {
    private static final GuestJoinPage GUEST_JOIN_PAGE = new GuestJoinPage ();

    public static GuestJoinPage guestJoinPage () {
        return GUEST_JOIN_PAGE;
    }

    private final Locator guestName  = Locator.buildLocator ()
        .name ("Guest Name")
        .web (id ("name"))
        .build ();
    private final Locator joinButton = Locator.buildLocator ()
        .name ("Join Button")
        .web (cssSelector ("button[type=\"submit\"]"))
        .build ();

    private GuestJoinPage () {
        // Utility class.
    }
}
