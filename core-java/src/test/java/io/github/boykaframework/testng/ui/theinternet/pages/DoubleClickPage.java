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

package io.github.boykaframework.testng.ui.theinternet.pages;

import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.id;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Double click page.
 *
 * @author Wasiq Bhamla
 * @since 27-Jul-2022
 */
@Getter
public class DoubleClickPage {
    private static final DoubleClickPage DOUBLE_CLICK_PAGE = new DoubleClickPage ();

    /**
     * Double click page instance.
     *
     * @return Double click menu
     */
    public static DoubleClickPage doubleClickPage () {
        return DOUBLE_CLICK_PAGE;
    }

    private final Locator clickHold   = Locator.buildLocator ()
        .web (id ("click-box"))
        .name ("Click Hold")
        .build ();
    private final Locator doubleClick = Locator.buildLocator ()
        .web (id ("double-click"))
        .name ("Double Click")
        .build ();
    private final Locator hoverButton = Locator.buildLocator ()
        .web (className ("dropbtn"))
        .name ("Hover Button")
        .build ();
    private final Locator hoverMenu   = Locator.buildLocator ()
        .web (className ("dropdown-content"))
        .name ("Hover Menu")
        .build ();
}
