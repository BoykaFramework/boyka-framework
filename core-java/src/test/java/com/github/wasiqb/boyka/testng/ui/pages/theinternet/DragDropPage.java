/*
 * MIT License
 *
 * Copyright (c) 2022, Wasiq Bhamla
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 */

package com.github.wasiqb.boyka.testng.ui.pages.theinternet;

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * Drag drop page.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
@Getter
public class DragDropPage {
    /**
     * Drag and drop page instance.
     */
    public static DragDropPage dragDropPage () {
        return new DragDropPage ();
    }

    private final Locator draggable = Locator.buildLocator ()
        .web (id ("draggable"))
        .build ();
    private final Locator droppable = Locator.buildLocator ()
        .web (id ("droppable"))
        .build ();
    private final Locator header    = Locator.buildLocator ()
        .web (tagName ("b"))
        .parent (this.droppable)
        .build ();
}
