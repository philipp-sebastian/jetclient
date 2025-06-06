package dev.jetclient.utils;

public class DelayCalculator {

    public int calculateDelay(float sliderValue, int maxDelay, int variance) {
        return (int) ((1 - sliderValue) * maxDelay) + (int) ((Math.random()) * variance);
    }
}
