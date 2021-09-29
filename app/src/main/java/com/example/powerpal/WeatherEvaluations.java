package com.example.powerpal;

public class WeatherEvaluations {
    public int tempFeelsLike(int tempActual, int humidity) {
        return tempActual + humidity;
    }

    public boolean tempThresholdAlert(int x, int y) {
        return x > y;
    }

    public String rainYesOrNo(int airPressure, int humidity, int rain) {
        return (airPressure + humidity) >= rain ? "yes" : "no";
    }
}
