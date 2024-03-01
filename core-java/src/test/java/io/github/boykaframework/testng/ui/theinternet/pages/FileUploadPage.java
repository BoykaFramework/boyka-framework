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
 * File Upload page class
 *
 * @author Wasiq Bhamla
 * @since 14-Mar-2023
 */
@Getter
public class FileUploadPage {
    private static final FileUploadPage FILE_UPLOAD_PAGE = new FileUploadPage ();

    /**
     * Gets File upload page class instance
     *
     * @return File upload page
     */
    public static FileUploadPage fileUploadPage () {
        return FILE_UPLOAD_PAGE;
    }

    private final Locator fileSubmit      = Locator.buildLocator ()
        .name ("File Submit Button")
        .web (id ("file-submit"))
        .build ();
    private final Locator fileUploadInput = Locator.buildLocator ()
        .name ("File Upload Input")
        .web (id ("file-upload"))
        .build ();
    private final Locator successTitle    = Locator.buildLocator ()
        .name ("Success Title")
        .web (tagName ("h3"))
        .build ();
}
