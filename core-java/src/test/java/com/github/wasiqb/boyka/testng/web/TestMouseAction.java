package com.github.wasiqb.boyka.testng.web;

import static com.github.wasiqb.boyka.actions.DriverActions.navigateTo;
import static com.github.wasiqb.boyka.actions.MouseActions.clickOn;
import static com.github.wasiqb.boyka.actions.MouseActions.dragDropTo;
import static com.github.wasiqb.boyka.actions.MouseActions.hoverOn;
import static com.github.wasiqb.boyka.actions.VerifyDriverActions.verifyBrowserUrl;
import static com.github.wasiqb.boyka.actions.VerifyElementActions.verifyTextOf;
import static com.github.wasiqb.boyka.manager.DriverManager.closeDriver;
import static com.github.wasiqb.boyka.manager.DriverManager.createDriver;
import static com.github.wasiqb.boyka.testng.web.pages.MouseEvent.mouseEvent;

import com.github.wasiqb.boyka.enums.ApplicationType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestMouseAction {

    private static final String URL = "https://the-internet.herokuapp.com/";

    /**
     * Setup test class by initialising driver.
     *
     * @param appType Application type
     * @param driverKey Driver config key
     */
    @BeforeClass (description = "Setup test class")
    @Parameters ({ "appType", "driverKey" })
    public void setupTestClass (final ApplicationType appType, final String driverKey) {
        createDriver (appType, driverKey);
    }

    /**
     * Tear down test class by closing driver.
     */
    @AfterClass (description = "Tear down test class")
    public void tearDownTestClass () {
        closeDriver ();
    }

    /**
     * Test dragAndDrop functionality.
     */
    @Test (description = "Test dragAndDrop functionality", priority = 1)
    public void testDragAndDrop () {
        navigateTo (URL);
        verifyBrowserUrl ().startsWith (URL);
        clickOn (mouseEvent ().getDragAndDropLink ());
        dragDropTo (mouseEvent ().getDraggable (), mouseEvent ().getDroppable ());
        verifyTextOf (mouseEvent ().getDragAndDropResult ()).isEqualTo ("B");
    }

    /**
     * Test hover functionality.
     */
    @Test (description = "Test hover functionality", priority = 2)
    public void testMouseHover () {
        navigateTo (URL);
        verifyBrowserUrl ().startsWith (URL);
        clickOn (mouseEvent ().getHoverLink ());
        hoverOn (mouseEvent ().getHoverImage ());
        verifyTextOf (mouseEvent ().getHoverResult ()).isEqualTo ("name: user1");
    }

}
