package com.github.wasiqb.boyka.actions;

import static com.github.wasiqb.boyka.actions.CommonActions.getDriverAttribute;
import static com.github.wasiqb.boyka.actions.CommonActions.performMobileGestures;
import static com.github.wasiqb.boyka.actions.ElementActions.isDisplayed;
import static com.github.wasiqb.boyka.enums.Message.ELEMENT_NOT_FOUND;
import static com.github.wasiqb.boyka.enums.SwipeDirection.DOWN;
import static com.github.wasiqb.boyka.enums.SwipeDirection.LEFT;
import static com.github.wasiqb.boyka.enums.SwipeDirection.RIGHT;
import static com.github.wasiqb.boyka.enums.SwipeDirection.UP;
import static com.github.wasiqb.boyka.sessions.ParallelSession.getSession;
import static com.github.wasiqb.boyka.utils.ErrorHandler.throwError;
import static java.util.Collections.singletonList;
import static org.apache.logging.log4j.LogManager.getLogger;

import com.github.wasiqb.boyka.builders.Locator;
import com.github.wasiqb.boyka.config.ui.mobile.device.SwipeSetting;
import org.apache.logging.log4j.Logger;

/**
 * Handle all swipe actions for Mobile.
 *
 * @author Waisq Bhamla
 * @since 17-Dec-2022
 */
public final class SwipeActions {
    private static final SwipeActions INSTANCE = new SwipeActions ();
    private static final Logger       LOGGER   = getLogger ();

    /**
     * Swipe actions class instance
     *
     * @return instance of {@link SwipeActions}
     */
    public static SwipeActions swipeActions () {
        return INSTANCE;
    }

    private final SwipeSetting setting;

    private SwipeActions () {
        this.setting = getSession ().getMobileSetting ()
            .getDevice ()
            .getSwipe ();
    }

    /**
     * Swipe down on the Mobile screen starting from center of the screen.
     */
    public void down () {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping down on Mobile devices.");
        final var swipeUpSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (DOWN)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }

    /**
     * Swipe left on the Mobile screen starting from center of the screen.
     */
    public void left () {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping up on Mobile devices.");
        final var swipeUpSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (LEFT)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }

    /**
     * Swipe right on the Mobile screen starting from center of the screen.
     */
    public void right () {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping up on Mobile devices.");
        final var swipeUpSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (RIGHT)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }

    /**
     * Swipe up until element is displayed.
     *
     * @param locator Element locator
     */
    public void till (final Locator locator) {
        LOGGER.traceEntry ();
        final var maxSwipe = this.setting.getMaxSwipeUntilFound ();
        var swipeCounts = 0;
        while (!isDisplayed (locator) && swipeCounts++ < maxSwipe) {
            up ();
        }
        if (!isDisplayed (locator)) {
            throwError (ELEMENT_NOT_FOUND, locator, getSession ().getPlatformType ());
        }
        LOGGER.traceExit ();
    }

    /**
     * Swipe up on the Mobile screen starting from center of the screen.
     */
    public void up () {
        LOGGER.traceEntry ();
        LOGGER.info ("Swiping up on Mobile devices.");
        final var swipeUpSequence = getDriverAttribute (driver -> FingerGestureBuilder.init ()
            .direction (UP)
            .build ()
            .swipe (), null);
        performMobileGestures (singletonList (swipeUpSequence));
        LOGGER.traceExit ();
    }
}
