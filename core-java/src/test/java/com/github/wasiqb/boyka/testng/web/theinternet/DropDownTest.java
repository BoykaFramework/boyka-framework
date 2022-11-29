/*
 * MIT License
 *
 * Copyright (c) 2022 Wasiq Bhamla
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

package com.github.wasiqb.boyka.testng.web.theinternet;

import static com.github.wasiqb.boyka.actions.DriverActions.navigate;
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectAll;
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByIndex;
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByText;
import static com.github.wasiqb.boyka.actions.DropDownActions.deselectByValue;
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByIndex;
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByText;
import static com.github.wasiqb.boyka.actions.DropDownActions.selectByValue;
import static com.github.wasiqb.boyka.actions.VerifyDropDownActions.verifySelectedItem;
import static com.github.wasiqb.boyka.actions.VerifyDropDownActions.verifySelectedItems;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.theinternet.pages.DropDownPage.dropDownPage;
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
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupClass (final PlatformType appType, final String driverKey) {
        createDriver (appType, driverKey);
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
        selectByIndex (dropDownPage ().getSuperHeroes (), 3);
        selectByIndex (dropDownPage ().getSuperHeroes (), 4);
        verifySelectedItems (dropDownPage ().getSuperHeroes ()).containsExactly ("Batman", "Batwoman");
        deselectAll (dropDownPage ().getSuperHeroes ());
        verifySelectedItems (dropDownPage ().getSuperHeroes ()).isEmpty ();
    }

    /**
     * Test dropdown deselect by index.
     */
    @Test (description = "Verify deselect dropdown value by index")
    public void testDeselectByIndex () {
        selectByIndex (dropDownPage ().getSuperHeroes (), 4);
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo ("Batwoman");
        deselectByIndex (dropDownPage ().getSuperHeroes (), 4);
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by text.
     */
    @Test (description = "Verify deselect dropdown value by text")
    public void testDeselectByText () {
        selectByText (dropDownPage ().getSuperHeroes (), "Aquaman");
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo ("Aquaman");
        deselectByText (dropDownPage ().getSuperHeroes (), "Aquaman");
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by value.
     */
    @Test (description = "Verify deselect dropdown value by value")
    public void testDeselectByValue () {
        selectByValue (dropDownPage ().getSuperHeroes (), "bt");
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo ("Batman");
        deselectByValue (dropDownPage ().getSuperHeroes (), "bt");
        verifySelectedItem (dropDownPage ().getSuperHeroes ()).isEqualTo (EMPTY);
    }

    /**
     * Test multiple select.
     */
    @Test (description = "Verify multi select dropdown values")
    public void testMultiSelect () {
        selectByValue (dropDownPage ().getSuperHeroes (), "ta");
        selectByIndex (dropDownPage ().getSuperHeroes (), 3);
        selectByText (dropDownPage ().getSuperHeroes (), "Black Panther");
        verifySelectedItems (dropDownPage ().getSuperHeroes ()).containsExactly ("The Avengers", "Batman",
            "Black Panther");
    }

    /**
     * Test select by index.
     */
    @Test (description = "Verify select dropdown value by index")
    public void testSelectByIndex () {
        selectByIndex (dropDownPage ().getFruits (), 3);
        verifySelectedItem (dropDownPage ().getFruits ()).isEqualTo ("Orange");
    }

    /**
     * Test select by text.
     */
    @Test (description = "Verify select dropdown value by text")
    public void testSelectByText () {
        selectByText (dropDownPage ().getFruits (), "Apple");
        verifySelectedItem (dropDownPage ().getFruits ()).isEqualTo ("Apple");
    }

    /**
     * Test select by value.
     */
    @Test (description = "Verify select dropdown value by value")
    public void testSelectByValue () {
        selectByValue (dropDownPage ().getFruits (), "1");
        verifySelectedItem (dropDownPage ().getFruits ()).isEqualTo ("Mango");
    }
}
