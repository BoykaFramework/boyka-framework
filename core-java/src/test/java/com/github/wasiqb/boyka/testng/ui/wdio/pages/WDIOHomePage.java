package com.github.wasiqb.boyka.testng.ui.wdio.pages;

import static io.appium.java_client.AppiumBy.accessibilityId;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;

/**
 * WDIO Sample app home page
 *
 * @author Wasiq Bhamla
 * @since 24-Dec-2022
 */
@Getter
public class WDIOHomePage {
    private static final WDIOHomePage WDIO_HOME_PAGE = new WDIOHomePage ();

    /**
     * Home page instance.
     *
     * @return {@link WDIOHomePage}
     */
    public static WDIOHomePage wdioHomePage () {
        return WDIO_HOME_PAGE;
    }

    private final Locator dragTab    = Locator.buildLocator ()
        .android (accessibilityId ("Drag"))
        .ios (accessibilityId ("Drag"))
        .name ("Drag Tab")
        .build ();
    private final Locator loginTab   = Locator.buildLocator ()
        .name ("Login Tab")
        .android (accessibilityId ("Login"))
        .ios (accessibilityId ("Login"))
        .build ();
    private final Locator webViewTab = Locator.buildLocator ()
        .android (accessibilityId ("Webview"))
        .ios (accessibilityId ("Webview"))
        .name ("Drag Tab")
        .build ();
}
