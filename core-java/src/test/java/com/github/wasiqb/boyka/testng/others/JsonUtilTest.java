package com.github.wasiqb.boyka.testng.others;

import static com.google.common.truth.Truth.assertThat;

import com.github.wasiqb.boyka.utils.JsonUtil;
import org.testng.annotations.Test;

/**
 * @author Faisal Khatri
 * @since 8/22/2022
 */
public class JsonUtilTest {
    /**
     * This method verifies the output of JSON util toString() method.
     */
    @Test
    public void testToString () {
        assertThat (JsonUtil.toString ("Created")).isEqualTo ("Created");
    }
}
