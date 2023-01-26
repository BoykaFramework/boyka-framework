package com.github.wasiqb.boyka.config.ui.mobile.device;

import lombok.Data;

/**
 * Swipe related settings
 *
 * @author Wasiq Bhamla
 * @since 02-Jan-2023
 */
@Data
public class SwipeSetting {
    private int distance           = 25;
    private int maxSwipeUntilFound = 5;
}
