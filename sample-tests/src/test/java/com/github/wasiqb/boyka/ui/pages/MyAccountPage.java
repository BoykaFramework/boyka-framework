package com.github.wasiqb.boyka.ui.pages;

import com.github.wasiqb.boyka.builders.Locator;
import lombok.Getter;
import org.openqa.selenium.By;

/**
 * @author Faisal Khatri
 * @since 8/2/2022
 **/
@Getter
public class MyAccountPage {

    public static MyAccountPage myAccountPage () {
        return new MyAccountPage ();
    }

    private final Locator pageHeader = Locator.buildLocator ()
        .web (By.tagName ("h2"))
        .build ();

}

