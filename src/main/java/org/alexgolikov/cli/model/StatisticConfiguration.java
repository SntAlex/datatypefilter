package org.alexgolikov.cli.model;

public class StatisticConfiguration {
    private final Boolean fullData;
    private final Boolean statisticEnabled;

    public StatisticConfiguration() {
        statisticEnabled = false;
        fullData = false;
    }

    public StatisticConfiguration(Boolean fullData) {
        this.statisticEnabled = true;
        this.fullData = fullData;
    }

    public Boolean isStatisticEnabled() {
        return statisticEnabled;
    }

    public Boolean isFullData() {
        return fullData;
    }
}
