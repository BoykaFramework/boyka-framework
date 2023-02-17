/*
 * MIT License
 *
 * Copyright (c) 2023, Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.ui.theinternet;

import static com.github.wasiqb.boyka.actions.DropDownActions.onDropDown;
import static com.github.wasiqb.boyka.actions.NavigateActions.navigate;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.ui.theinternet.pages.DropDownPage.dropDownPage;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * DropDown test.
 *
 * @author Wasiq Bhamla
 * @since 30-Jul-2022
 */
public class DropDownTest {
    private static final String URL = "https://letcode.in/dropdowns";

    /**
     * Setup test class by initialising driver.
     *
     * @param platformType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "platformType", "driverKey" })
    public void setupClass (final PlatformType platformType, final String driverKey) {
        createDriver (platformType, driverKey);
        navigate ().to (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        closeDriver ();
    }

    /**
     * Test dropdown deselect all.
     */
    @Test (description = "Verify deselect all dropdown values")
    public void testDeselectAll () {
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByIndex (3);
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByIndex (4);
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItems ()
            .containsExactly ("Batman", "Batwoman");
        onDropDown (dropDownPage ().getSuperHeroes ()).deselectAll ();
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItems ()
            .isEmpty ();
    }

    /**
     * Test dropdown deselect by index.
     */
    @Test (description = "Verify deselect dropdown value by index")
    public void testDeselectByIndex () {
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByIndex (4);
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo ("Batwoman");
        onDropDown (dropDownPage ().getSuperHeroes ()).deselectByIndex (4);
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by text.
     */
    @Test (description = "Verify deselect dropdown value by text")
    public void testDeselectByText () {
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByText ("Aquaman");
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo ("Aquaman");
        onDropDown (dropDownPage ().getSuperHeroes ()).deselectByText ("Aquaman");
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by value.
     */
    @Test (description = "Verify deselect dropdown value by value")
    public void testDeselectByValue () {
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByValue ("bt");
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo ("Batman");
        onDropDown (dropDownPage ().getSuperHeroes ()).deselectByValue ("bt");
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test multiple select.
     */
    @Test (description = "Verify multi select dropdown values")
    public void testMultiSelect () {
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByValue ("ta");
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByIndex (3);
        onDropDown (dropDownPage ().getSuperHeroes ()).selectByText ("Black Panther");
        onDropDown (dropDownPage ().getSuperHeroes ()).verifySelectedItems ()
            .containsExactly ("The Avengers", "Batman", "Black Panther");
    }

    /**
     * Test select by index.
     */
    @Test (description = "Verify select dropdown value by index")
    public void testSelectByIndex () {
        onDropDown (dropDownPage ().getFruits ()).selectByIndex (3);
        onDropDown (dropDownPage ().getFruits ()).verifySelectedItem ()
            .isEqualTo ("Orange");
    }

    /**
     * Test select by text.
     */
    @Test (description = "Verify select dropdown value by text")
    public void testSelectByText () {
        onDropDown (dropDownPage ().getFruits ()).selectByText ("Apple");
        onDropDown (dropDownPage ().getFruits ()).verifySelectedItem ()
            .isEqualTo ("Apple");
    }

    /**
     * Test select by value.
     */
    @Test (description = "Verify select dropdown value by value")
    public void testSelectByValue () {
        onDropDown (dropDownPage ().getFruits ()).selectByValue ("1");
        onDropDown (dropDownPage ().getFruits ()).verifySelectedItem ()
            .isEqualTo ("Mango");
    }
}
