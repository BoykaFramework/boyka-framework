/*
 * MIT License
 *
 * Copyright (c) 2025, Boyka Framework
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

import static java.text.MessageFormat.format;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.tagName;

import io.github.boykaframework.builders.Locator;
import lombok.Getter;

/**
 * Shadow example page.
 *
 * @author Wasiq Bhamla
 * @since 13-Jan-2025
 */
@Getter
public class ShadowRootPage {
    private static final ShadowRootPage SHADOW_ROOT_PAGE = new ShadowRootPage ();

    /**
     * Gets the page instance.
     *
     * @return Page instance
     */
    public static ShadowRootPage shadowRootPage () {
        return SHADOW_ROOT_PAGE;
    }

    private final Locator smartMenu = Locator.buildLocator ()
        .name ("Smart Menu")
        .web (tagName ("smart-ui-menu"))
        .shadowRoot (true)
        .build ();

    private ShadowRootPage () {
        // Singleton class
    }

    /**
     * Gets menu.
     *
     * @param menuName Menu name
     *
     * @return Locator.
     */
    public Locator getMenu (final String menuName) {
        return Locator.buildLocator ()
            .name (menuName)
            .parent (this.smartMenu)
            .web (cssSelector (format ("smart-menu-items-group[label=\"{0}\"]", menuName)))
            .build ();
    }

    /**
     * Gets menu item from the menu.
     *
     * @param menuName Menu name
     * @param itemName Item name
     *
     * @return Locator.
     */
    public Locator getMenuItem (final String menuName, final String itemName) {
        return Locator.buildLocator ()
            .name (itemName)
            .parent (getMenu (menuName))
            .web (cssSelector (format ("smart-menu-item[label=\"{0}\"]", itemName)))
            .build ();
    }
}
