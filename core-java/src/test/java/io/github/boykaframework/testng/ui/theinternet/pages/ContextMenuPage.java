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

import io.github.boykaframework.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * Context menu page.
 *
 * @author Wasiq Bhamla
 * @since 26-Jul-2022
 */
@Getter
public class ContextMenuPage {
    private static final ContextMenuPage CONTEXT_MENU_PAGE = new ContextMenuPage ();

    /**
     * Context menu page instance.
     *
     * @return Context menu page instance
     */
    public static ContextMenuPage contextMenuPage () {
        return CONTEXT_MENU_PAGE;
    }

    private final Locator hotSpot = Locator.buildLocator ()
        .web (By.id ("hot-spot"))
        .name ("Hot Spot")
        .build ();
}
