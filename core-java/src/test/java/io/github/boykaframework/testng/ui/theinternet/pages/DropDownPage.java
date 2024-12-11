/*
 * MIT License
 *
 * Copyright (c) 2024, Wasiq Bhamla
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

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * DropDown page.
 *
 * @author Wasiq Bhamla
 * @since 30-Jul-2022
 */
@Getter
public class DropDownPage {
    private static final DropDownPage DROP_DOWN_PAGE = new DropDownPage ();

    /**
     * Drag and drop page instance.
     *
     * @return {@link DropDownPage}
     */
    public static DropDownPage dropDownPage () {
        return DROP_DOWN_PAGE;
    }

    private final Locator fruits      = Locator.buildLocator ()
        .web (id ("fruits"))
        .name ("Fruits")
        .build ();
    private final Locator fruitList   = Locator.buildLocator ()
        .name ("Fruit List")
        .web (tagName ("options"))
        .parent (this.fruits)
        .build ();
    private final Locator superHeroes = Locator.buildLocator ()
        .web (id ("superheros"))
        .name ("Super Heroes")
        .build ();
}
