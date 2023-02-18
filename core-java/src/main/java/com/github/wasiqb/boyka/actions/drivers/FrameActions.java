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

package com.github.wasiqb.boyka.actions.drivers;

import static com.github.wasiqb.boyka.actions.CommonActions.performDriverAction;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.actions.interfaces.drivers.IFrameActions;
import org.apache.logging.log4j.Logger;

public class FrameActions implements IFrameActions {
    private static final IFrameActions FRAME_ACTIONS = new FrameActions ();
    private static final Logger        LOGGER        = getLogger ();

    public static IFrameActions onFrame () {
        return FRAME_ACTIONS;
    }

    @Override
    public void switchTo (final String frameName) {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to frame: {}", frameName);
        performDriverAction (driver -> driver.switchTo ()
            .frame (frameName));
        LOGGER.traceExit ();
    }

    @Override
    public void switchToParent () {
        LOGGER.traceEntry ();
        LOGGER.info ("Switching to main frame...");
        performDriverAction (driver -> driver.switchTo ()
            .parentFrame ());
        LOGGER.traceExit ();
    }
}
