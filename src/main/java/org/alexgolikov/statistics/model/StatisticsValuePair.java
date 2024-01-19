package org.alexgolikov.statistics.model;

public class StatisticsValuePair<T> {
    private final T value;
    private final String stringValue;

    public StatisticsValuePair(T value, String stringValue) {
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
