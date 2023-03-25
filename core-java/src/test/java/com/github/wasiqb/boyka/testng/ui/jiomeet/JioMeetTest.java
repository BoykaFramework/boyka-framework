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

package com.github.wasiqb.boyka.testng.ui.jiomeet;

import static com.github.wasiqb.boyka.actions.drivers.DriverActions.withDriver;
import static com.github.wasiqb.boyka.actions.drivers.NavigateActions.navigate;
import static com.github.wasiqb.boyka.actions.drivers.WindowActions.onWindow;
import static com.github.wasiqb.boyka.actions.elements.ClickableActions.withMouse;
import static com.github.wasiqb.boyka.actions.elements.ElementActions.onElement;
import static com.github.wasiqb.boyka.actions.elements.TextBoxActions.onTextBox;
import static com.github.wasiqb.boyka.manager.ParallelSession.clearAllSessions;
import static com.github.wasiqb.boyka.manager.ParallelSession.createSession;
import static com.github.wasiqb.boyka.manager.ParallelSession.switchPersona;
import static com.github.wasiqb.boyka.testng.ui.jiomeet.pages.GuestJoinPage.guestJoinPage;
import static com.github.wasiqb.boyka.testng.ui.jiomeet.pages.HomePage.homePage;
import static com.github.wasiqb.boyka.testng.ui.jiomeet.pages.MeetingPage.meetingPage;
import static com.github.wasiqb.boyka.testng.ui.jiomeet.pages.SignInPage.signInPage;
import static com.github.wasiqb.boyka.testng.ui.jiomeet.pages.StartMeetingPage.startMeetingPage;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;

import com.github.wasiqb.boyka.enums.PlatformType;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class JioMeetTest {
    private static final String GUEST_PERSONA = "User 2";
    private static final String HOST_PERSONA  = "User 1";

    private String hostName;
    private String meetingUrl;

    @AfterMethod (alwaysRun = true)
    public void afterMethod (final ITestResult result) {
        if (!result.isSuccess ()) {
            onWindow ().takeScreenshot ();
        }
    }

    @BeforeClass (alwaysRun = true)
    @Parameters ({ "platformType", "driverKey" })
    public void setupTestClass (final PlatformType platformType, final String chromeConfig) {
        createSession (HOST_PERSONA, platformType, chromeConfig);
        createSession (GUEST_PERSONA, platformType, chromeConfig);
    }

    @AfterClass (alwaysRun = true)
    public void tearDownClass () {
        clearAllSessions ();
    }

    @Test (dependsOnMethods = "testHostParticipantName")
    public void testEndMeeting () {
        switchPersona (GUEST_PERSONA);
        withMouse (meetingPage ().getEndCallButton ()).click ();

        switchPersona (HOST_PERSONA);
        withMouse (meetingPage ().getEndCallButton ()).click ();
        withMouse (meetingPage ().getEndForAll ()).click ();
        withMouse (meetingPage ().getSkipButton ()).click ();
    }

    @Test (dependsOnMethods = "testGuestStartMeeting")
    public void testGuestParticipantName () {
        withMouse (meetingPage ().getParticipants ()).click ();
        onElement (meetingPage ().getCurrentParticipantName (GUEST_PERSONA)).verifyText ()
            .endsWith ("(You)");
    }

    @Test (dependsOnMethods = "testStartHostMeeting")
    public void testGuestStartMeeting () {
        switchPersona (GUEST_PERSONA);
        navigate ().to (this.meetingUrl);

        onTextBox (guestJoinPage ().getGuestName ()).enterText (GUEST_PERSONA);
        withMouse (guestJoinPage ().getJoinButton ()).click ();

        withDriver ().waitUntil (invisibilityOfElementLocated (homePage ().getLoader ()
            .getLocator ()));
    }

    @Test (dependsOnMethods = "testGuestParticipantName")
    public void testHostParticipantName () {
        switchPersona (HOST_PERSONA);
        withMouse (meetingPage ().getParticipants ()).click ();
        onElement (meetingPage ().getCurrentParticipantName (this.hostName)).verifyText ()
            .endsWith ("(You)");
    }

    @Test
    public void testHostSignIn () {
        switchPersona (HOST_PERSONA);
        navigate ().to ("https://jiomeetpro.jio.com/home");

        withMouse (homePage ().getSignIn ()).click ();
        onTextBox (signInPage ().getEmail ()).enterText ("test-user1@mailinator.com");
        withMouse (signInPage ().getProceedButton ()).click ();
        onTextBox (signInPage ().getPassword ()).enterText ("Admin@1234");
        withMouse (signInPage ().getSignInButton ()).click ();
    }

    @Test (dependsOnMethods = "testHostSignIn")
    public void testStartHostMeeting () {
        withMouse (homePage ().getStartMeeting ()).click ();
        withMouse (startMeetingPage ().getStart ()).click ();

        withDriver ().waitUntil (invisibilityOfElementLocated (homePage ().getLoader ()
            .getLocator ()));

        this.hostName = onElement (meetingPage ().getHostName ()).getText ();
        this.meetingUrl = onElement (meetingPage ().getMeetingLink ()).getText ();
    }
}
