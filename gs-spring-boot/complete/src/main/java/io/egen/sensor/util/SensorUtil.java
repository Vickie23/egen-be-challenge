package io.egen.sensor.util;

/**
 * Created by Subbu on 5/29/16.
 */
public class SensorUtil {

    public static int computePercent(int value, int percentage) {
        return (int) (value * (percentage / 100.0f));
    }
}
