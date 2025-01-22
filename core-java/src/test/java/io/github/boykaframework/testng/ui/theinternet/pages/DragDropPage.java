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

import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Drag drop page.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
@Getter
public class DragDropPage {
    private static final DragDropPage DRAG_DROP_PAGE = new DragDropPage ();

    /**
     * Drag and drop page instance.
     *
     * @return {@link DragDropPage}
     */
    public static DragDropPage dragDropPage () {
        return DRAG_DROP_PAGE;
    }

    private final Locator draggable = Locator.buildLocator ()
        .web (id ("draggable"))
        .name ("Draggable")
        .build ();
    private final Locator droppable = Locator.buildLocator ()
        .web (id ("droppable"))
        .name ("Droppable")
        .build ();
    private final Locator header    = Locator.buildLocator ()
        .web (tagName ("b"))
        .name ("Header")
        .parent (this.droppable)
        .build ();
}
