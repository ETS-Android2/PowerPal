package com.example.powerpal;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WeatherEvaluationsTest {
    @Test
    public void tempFeelsLikeTEST() {
        WeatherEvaluations x = new WeatherEvaluations();
        assertEquals(90, x.tempFeelsLike(82,8));
    }

    @Test
    public void tempThresholdAlertTEST() {
        WeatherEvaluations x = new WeatherEvaluations();
        assertTrue(x.tempThresholdAlert(81, 77));
    }

    @Test
    public void rainYesOrNoTEST() {
        WeatherEvaluations x = new WeatherEvaluations();
        assertSame("yes", x.rainYesOrNo(85, 10, 69));
    }
}
