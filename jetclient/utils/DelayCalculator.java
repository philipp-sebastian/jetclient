package dev.jetclient.utils;

public class DelayCalculator {

    public static int calculateDelay(float sliderValue, int maxDelay, int variance) {
        return (int) ((1 - sliderValue) * maxDelay) + (int) ((Math.random()) * variance);
    }
}
