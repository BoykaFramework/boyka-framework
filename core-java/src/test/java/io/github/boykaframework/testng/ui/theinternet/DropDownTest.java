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

package io.github.boykaframework.testng.ui.theinternet;

import static io.github.boykaframework.manager.ParallelSession.clearSession;
import static io.github.boykaframework.manager.ParallelSession.createSession;
import static io.github.boykaframework.testng.ui.theinternet.pages.DropDownPage.dropDownPage;
import static org.apache.commons.lang3.StringUtils.EMPTY;

import io.github.boykaframework.actions.drivers.NavigateActions;
import io.github.boykaframework.actions.elements.DropDownActions;
import io.github.boykaframework.enums.PlatformType;
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
        createSession ("DropDownTest", platformType, driverKey);
        NavigateActions.navigate ()
            .to (URL);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownClass () {
        clearSession ();
    }

    /**
     * Test dropdown deselect all.
     */
    @Test (description = "Verify deselect all dropdown values")
    public void testDeselectAll () {
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByIndex (3);
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByIndex (4);
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItems ()
            .containsExactly ("Batman", "Batwoman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .deselectAll ();
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItems ()
            .isEmpty ();
    }

    /**
     * Test dropdown deselect by index.
     */
    @Test (description = "Verify deselect dropdown value by index")
    public void testDeselectByIndex () {
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByIndex (4);
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo ("Batwoman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .deselectByIndex (4);
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by text.
     */
    @Test (description = "Verify deselect dropdown value by text")
    public void testDeselectByText () {
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByText ("Aquaman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo ("Aquaman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .deselectByText ("Aquaman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test dropdown deselect by value.
     */
    @Test (description = "Verify deselect dropdown value by value")
    public void testDeselectByValue () {
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByValue ("bt");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo ("Batman");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .deselectByValue ("bt");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItem ()
            .isEqualTo (EMPTY);
    }

    /**
     * Test multiple select.
     */
    @Test (description = "Verify multi select dropdown values")
    public void testMultiSelect () {
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByValue ("ta");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByIndex (3);
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .selectByText ("Black Panther");
        DropDownActions.onDropDown (dropDownPage ().getSuperHeroes ())
            .verifySelectedItems ()
            .containsExactly ("The Avengers", "Batman", "Black Panther");
    }

    /**
     * Test select by index.
     */
    @Test (description = "Verify select dropdown value by index")
    public void testSelectByIndex () {
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .selectByIndex (3);
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .verifySelectedItem ()
            .isEqualTo ("Orange");
    }

    /**
     * Test select by text.
     */
    @Test (description = "Verify select dropdown value by text")
    public void testSelectByText () {
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .selectByText ("Apple");
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .verifySelectedItem ()
            .isEqualTo ("Apple");
    }

    /**
     * Test select by value.
     */
    @Test (description = "Verify select dropdown value by value")
    public void testSelectByValue () {
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .selectByValue ("1");
        DropDownActions.onDropDown (dropDownPage ().getFruits ())
            .verifySelectedItem ()
            .isEqualTo ("Mango");
    }
}
