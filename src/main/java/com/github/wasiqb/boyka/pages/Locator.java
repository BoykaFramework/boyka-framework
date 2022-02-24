package com.github.wasiqb.boyka.pages;

import lombok.Builder;
import lombok.Getter;
import org.openqa.selenium.By;

@Getter
@Builder (builderMethodName = "createLocator", buildMethodName = "create")
public class Locator {
    private By      android;
    private By      ios;
    private Locator parent;
    private By      web;
}