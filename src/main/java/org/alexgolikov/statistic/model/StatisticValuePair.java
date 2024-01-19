package org.alexgolikov.statistic.model;

public class StatisticValuePair<T> {
    private final T value;
    private final String stringValue;

    public StatisticValuePair(T value, String stringValue) {
        this.value = value;
        this.stringValue = stringValue;
    }

    public T getValue() {
        return value;
    }

    public String getStringValue() {
        return stringValue;
    }
}
